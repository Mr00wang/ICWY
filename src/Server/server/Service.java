package Server.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author
 * @version
 */
public class Service {
    public void startService() {
        try {
            ServerSocket ss = new ServerSocket(4003);
            Socket socket = null;

            while(true) {
                socket = ss.accept();
                ServerThread thread = new ServerThread(socket);
                thread.start();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
