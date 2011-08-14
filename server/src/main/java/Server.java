import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;

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
            new Request(conn, connectedSocket).start();

        }
    }

    static class Request extends Thread {
        private final Socket socket;
        private final HashSet<Socket> connectedSockets;

        public Request(Socket socket, HashSet<Socket> connectedSockets) {
            this.socket = socket;
            this.connectedSockets = connectedSockets;
        }

        public void run() {
            try {
                Scanner is = new Scanner(socket.getInputStream());
                String line;
                while ((line = is.nextLine()).equals("logout")) {
                    System.out.println(line);
                    for (Socket conn : connectedSockets) {
                        PrintWriter pw = new PrintWriter(conn.getOutputStream());
                        if (conn != socket) {
                            pw.write(line);
                            pw.write("\n");
                            pw.flush();
                        }
                    }
                }
                connectedSockets.remove(socket);
                socket.close();
            } catch (IOException e) {
            }
        }
    }

    public void stop() throws IOException {
        server.close();
    }

    public static void main(String[] args) throws IOException {
        new Server("localhost", 8080).start();
    }
}
