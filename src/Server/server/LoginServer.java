package Server.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Server.db.PasswordException;
import Server.db.StateException;
import Server.db.UserService;
import Server.db.UsernameNotFoundException;
import Server.server.UserOnlineList;
import Server.db.*;
import com.sun.corba.se.spi.activation.Server;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 登录服务器 主要是负责登录
 *
 * @author 凯哥
 *
 */
public class LoginServer implements Runnable {

    private Socket socket = null;

    public LoginServer(Socket socket) {
        this.socket = socket;
    }

    // 线程方法
    public void run() {
        String uid = null;
        // 登录操作
        InputStream in = null;
        OutputStream out = null;
        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();

            // 等待客户端信息
            byte[] bytes = new byte[1024];
            int len = in.read(bytes);
            String json_str = new String(bytes, 0, len);
            //////////////////////////////////////////// {“username”:”13800012461”,”password”:”123456”}
            JSONObject json = JSONObject.fromObject(json_str);// 解析
            String username = json.getString("username");
            String password = json.getString("password");

            try {


                    uid = new UserService().loginForUid(username, password);
                    // 登记登录信息
                    UserOnlineList.getUserOnlineList().regOnline(uid, socket, null, username);

                out.write("{\"state\":0,\"msg\":\"登录成功!\"}".getBytes());
                out.flush();
                // 陆陆续续的接收客户端发送的指令要求
                while (true) {
                    bytes = new byte[2048];
                    len = in.read(bytes);
                    String command = new String(bytes, 0, len);
                    if (command.equals("U0001")) { // 更新好友列表

                        //Vector<Server.db.UserInfo> userinfos = new UserService().getHaoyouliebiao(uid);
                        //out.write(JSONArray.fromObject(userinfos).toString().getBytes());
                        out.flush();

                    } else if (command.equals("U0002")) {// 更新好友在线
                        out.write(1);
                        out.flush();
                        // 获得好友的列表编号
                        len = in.read(bytes);// 1324564,12346546,123456456,2346546,2456456,1237489,137687
                        String str = new String(bytes, 0, len);
                        String[] ids = str.split(",");

                        StringBuffer stringBuffer = new StringBuffer();
                        for (String string : ids) {
                            if (UserOnlineList.getUserOnlineList().isUserOnline(string)) {
                                stringBuffer.append(string);
                                stringBuffer.append(",");
                            }
                        }
                        if (stringBuffer.length() == 0) {
                            // 没有好友在线
                            out.write("notFound".getBytes());
                            out.flush();
                        } else {
                            // 回执好友在线列表
                            out.write(stringBuffer.toString().getBytes());
                            out.flush();
                        }

                    } else if (command.equals("U0003")) { // 更新个人资料

//                        UserInfo userinfo = new UserService().getUserinfo(uid);
//                        out.write(JSONObject.fromObject(userinfo).toString().getBytes());
//                        out.flush();

                    } else if (command.equals("E0001")) {// 修改个人资料

                    } else if (command.equals("EXIT")) {// 退出用户登录
                        UserOnlineList.getUserOnlineList().logout(uid);
                        return;
                    }

                }

            } catch (UsernameNotFoundException e) {
                out.write("{\"state\":2,\"msg\":\"账户名错误!\"}".getBytes());
                out.flush();
                return;
            } catch (PasswordException e) {
                out.write("{\"state\":1,\"msg\":\"用户密码错误!\"}".getBytes());
                out.flush();
                return;
            } catch (StateException e) {
                out.write("{\"state\":3,\"msg\":\"账户锁定!\"}".getBytes());
                out.flush();
                return;
            } catch (SQLException e) {
                out.write("{\"state\":4,\"msg\":\"未知错误!\"}".getBytes());
                out.flush();
                return;
            }

        } catch (Exception e) {

        } finally {
            // 结束后把连接关闭
            try {
                // 如果遇到突然关闭或者是关闭的话 我们需要在列表里去除此账户
                UserOnlineList.getUserOnlineList().logout(uid);
                in.close();
                out.close();
                socket.close();
            } catch (Exception e2) {
            }
        }

    }

    public static void openServer() throws Exception {
        // 线程池
        ExecutorService execute = Executors.newFixedThreadPool(2000);
        // 开启了 TCP 4001 端口 用于登录业务
        ServerSocket server = new ServerSocket(4004);
        // 死循环的目的是可以无限服务
        while (true) {
            Socket socket = server.accept();
            socket.setSoTimeout(10000);
            execute.execute(new LoginServer(socket));
        }

    }

   /* public static void main(String[] args) {
        try {
            openServer();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/

}
