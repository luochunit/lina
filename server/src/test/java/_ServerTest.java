import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by IntelliJ IDEA.
 * User: ringo
 * Date: 8/10/11
 * Time: 10:58 PM
 */
public class _ServerTest {

    @Test
    public void start() throws IOException, InterruptedException {
        PrintStream newOut = mock(PrintStream.class);
        System.setOut(newOut);
        final Server server = new Server("localhost", 8089);
        Thread serverThresd = new Thread() {
            public void run() {
                try {
                    server.start();
                } catch (IOException e) {
                }
            }
        };
        serverThresd.start();
        Socket client = new Socket();
        client.connect(new InetSocketAddress("localhost", 8089));
        OutputStream out = client.getOutputStream();
        out.write(1);
        verify(newOut).print(1);
        server.stop();
        serverThresd.join();
    }

    @Test
    public void chat() throws IOException, InterruptedException {
        PrintStream newOut = mock(PrintStream.class);
        System.setOut(newOut);
        final Server server = new Server("localhost", 8090);
        Thread serverThresd = new Thread() {
            public void run() {
                try {
                    server.start();
                } catch (IOException e) {
                }
            }
        };
        serverThresd.start();
        Socket clientA = new Socket();
        clientA.connect(new InetSocketAddress("localhost", 8090));
        OutputStream outA = clientA.getOutputStream();
        outA.write(1);
        verify(newOut).print(1);
//
//        Thread.sleep(1000);
//        Socket clientB = new Socket();
//        clientB.connect(new InetSocketAddress("localhost", 8090));
//        OutputStream outB = clientB.getOutputStream();
//        outB.write(2);
//        verify(newOut).print(2);
        server.stop();
        serverThresd.join();
    }
}
