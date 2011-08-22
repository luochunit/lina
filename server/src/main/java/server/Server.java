package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: ringo
 * Date: 8/10/11
 * Time: 11:09 PM
 */
public class Server {

    final String address;
    final int port;
    private ServerSocket server;
    HashSet<Socket> connectedSocket = new HashSet<Socket>();


    public Server(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public void start() throws IOException {
        server = new ServerSocket(port, 0, InetAddress.getByName(address));
        while (true) {
            Socket conn = server.accept();
            connectedSocket.add(conn);
            new Thread(new Request(conn, connectedSocket)).start();

        }
    }

    public void stop() throws IOException {
        server.close();
    }

    public static void main(String[] args) throws IOException {
        new Server("192.168.1.71", 8080).start();
    }
}
