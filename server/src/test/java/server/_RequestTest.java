package server;

import org.junit.Test;
import org.mockito.Matchers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashSet;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * Created by IntelliJ IDEA.
 * User: ringo
 * Date: 8/22/11
 * Time: 10:37 PM
 */
public class _RequestTest {
    @Test
    public void Broadcast() throws IOException {
        Socket socket = mock(Socket.class);
        Socket connectedSocket = mock(Socket.class);
        OutputStream os = mock(OutputStream.class);
        OutputStream connectedOS = mock(OutputStream.class);
        when(socket.getOutputStream()).thenReturn(os);
        when(connectedSocket.getOutputStream()).thenReturn(connectedOS);
        HashSet<Socket> connectedSockets = new HashSet<Socket>();
        connectedSockets.add(socket);
        connectedSockets.add(connectedSocket);
        Request request = new Request(socket, connectedSockets);
        request.broadcast("Hello World!".getBytes());
        verify(connectedOS).write("Hello World!".getBytes());
        verify(os, never()).write((byte[]) anyObject());
    }

}
