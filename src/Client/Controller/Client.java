package Client.Controller;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;



public class Client {
    private int port = 4003;
    private String Sever_address = "127.0.0.1"; //服务器主机ip
    private Socket socket;

    //实例化， 建立连接
    public Client(){
        try {
            socket = new Socket(Sever_address, port);
        } catch(UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "服务器端未开启");
        }catch(IOException e) {
            JOptionPane.showMessageDialog(null, "服务器端未开启");
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    //向服务端发送数据
    public void sendData(CommandTranser cmd) {
        ObjectOutputStream oos = null; //主要的作用是用于写入对象信息与读取对象信息。 对象信息一旦写到文件上那么对象的信息就可以做到持久化了
        try {
            if(socket == null) {
                return;
            }
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(cmd);
        } catch(UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "服务器端未开启");
        }catch(IOException e) {
            JOptionPane.showMessageDialog(null, "服务器端未开启");
        }
    }

    //接受服务端发送的消息
    public CommandTranser getData() {
        ObjectInputStream ois = null;
        CommandTranser cmd = null;
        if(socket == null) {
            return null;
        }
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            cmd = (CommandTranser) ois.readObject();
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }

        /*
         * 这里有用吗？-----------------------------------------------------------------------------------应该没用只是在测使输出
         */
//		if("message".equals(cmd.getCmd())) {
//			System.out.println((String)cmd.getData());
//		}

        return cmd;
    }

}
