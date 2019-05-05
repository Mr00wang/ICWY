package Client.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import Client.Model.Config;
import net.sf.json.JSONObject;

/**
 * 通讯服务 与服务器保持连接状态 1.更新好友在线状态 5秒钟一次请求 2.登录验证 3.退出账户
 *
 * @author 凯哥
 *
 */
public class NetService implements Runnable {

    private NetService() {
    }

    // 对象
    private static NetService netService = new NetService();

    public static NetService getNetService() {
        return netService;
    }

    // 这里准备与服务器保存长时间通讯
    public void run() {

        try {

            byte[] bytes = new byte[1024 * 10];
            int len = 0;
            // 好友在线的时时更新
			/*while (run) {
				output.write("U0002".getBytes());
				output.flush();
				input.read();
				output.write(Config.haoyou_liebiao_data.getBytes());
				output.flush();
				len = input.read(bytes);
				String online = new String(bytes, 0, len);
				System.out.println("在线账户:" + online);
				try {
					if (!Config.haoyou_online.equals(online)) {
						Config.haoyou_online = online;
						Config.haoyouListJPanel.haoyouOnline();
					}
				} catch (Exception e) {
				}
				Config.haoyou_online = online;

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
			}*/

        } catch (Exception e) {
            run = false;
        }

    }

    private Socket socket = null;
    private InputStream input = null;
    private OutputStream output = null;
    private Thread thread = null;
    private boolean run = false;

    public JSONObject login() throws UnknownHostException, IOException {
        socket = new Socket(Config.IP, Config.LOGIN_PORT);
        input = socket.getInputStream();
        output = socket.getOutputStream();
        String json_str = "{\"id\":\"" + Config.id + "\",\"password\":\"" + Config.password + "\"}";

        // 开始与服务传递消息
        output.write(json_str.getBytes());
        output.flush();

        // 等待服务器回执消息
        byte[] bytes = new byte[1024];
        int len = input.read(bytes);

        json_str = new String(bytes, 0, len);
        JSONObject json = JSONObject.fromObject(json_str);

        // 如果是0 就是登录成功!
        if (json.getInt("state") == 0) {
            // 开启持续的网络连接服务

            if (thread != null) {
                // 询问线程是否还活着
                if (thread.getState() == Thread.State.RUNNABLE) {
                    run = false;// 终止线程运行
                    try {
                        thread.stop();
                    } catch (Exception e) {
                    }
                }
            }

            //////////////////////////////////////////// 好友信息获得
            output.write("U0001".getBytes());
            output.flush();
            bytes = new byte[1024 * 10];
            len = input.read(bytes);
            String jsonstr = new String(bytes, 0, len);
            ////////////////////////////////////////////
            // 解析好友列表
            Config.jiexi_haoyou_json_data(jsonstr);
            System.out.println("好友资料:" + Config.haoyou_json_data);

            //////////////////////////////////////////// 个人资料获得
            output.write("U0003".getBytes());
            output.flush();
            len = input.read(bytes);
            Config.geren_json_data = new String(bytes, 0, len);
            System.out.println("个人资料:" + Config.geren_json_data);
            /////////////////////////////////////////////

            /////////////////////////////////////////////启动UDP服务器
            Config.datagramSocket_client=new DatagramSocket();

            //启动心跳包
            //new MessageRegService(Config.datagramSocket_client);

            //启动消息服务
            //new MessageService(Config.datagramSocket_client);

            /////////////////////////////////////////////

            // 重新开线程与服务器保持通讯
            thread = new Thread(this);
            run = true;
            thread.start();

        }

        return json;
    }

}
