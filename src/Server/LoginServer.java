package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginServer implements Runnable{

    private Socket socket = null;

    public LoginServer(Socket socket) {
        this.socket = socket;
    }

    // 线程方法
    public void run() {


    }

    public static void openServer() throws Exception {
        // 线程池
        ExecutorService execute = Executors.newFixedThreadPool(2000);
        // 开启了 TCP 4001 端口 用于登录业务
        ServerSocket server = new ServerSocket(4001);
        // 死循环的目的是可以无限服务
        while (true) {
            Socket socket = server.accept();
            socket.setSoTimeout(10000);
            execute.execute(new LoginServer(socket));
        }

    }

}
