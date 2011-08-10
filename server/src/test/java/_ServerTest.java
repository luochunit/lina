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
    public void start() throws IOException {
        PrintStream newOut = mock(PrintStream.class);
        System.setOut(newOut);
        new Thread() {
            public void run() {
                Server server = new Server("localhost", 8080);
                try {
                    server.start();
                } catch (IOException e) {
                }
            }
        }.start();
        Socket client = new Socket();
        client.connect(new InetSocketAddress("localhost", 8080));
        OutputStream out = client.getOutputStream();
        out.write(1);
        verify(newOut).print(1);

    }
}
