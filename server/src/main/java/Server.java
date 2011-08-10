import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by IntelliJ IDEA.
 * User: ringo
 * Date: 8/10/11
 * Time: 11:09 PM
 */
public class Server {

    final String address;
    final int port;

    public Server(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket server = new ServerSocket(port, 0, InetAddress.getByName(address));
        Socket conn = server.accept();
        InputStream is = conn.getInputStream();
        System.out.print(is.read());
        server.close();
    }

    public static void main(String[] args) throws IOException {
        new Server("localhost", 8080).start();
    }
}
