package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: ringo
 * Date: 8/22/11
 * Time: 10:35 PM
 */
public class Request implements Runnable {
    private final Socket socket;
    private final Collection<Socket> connectedSockets;

    public Request(Socket socket, Collection<Socket> connectedSockets) {
        this.socket = socket;
        this.connectedSockets = connectedSockets;
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            byte[] bytes = new byte[1];
            while (is.read(bytes) > 0) {
                broadcast(bytes);
            }
            connectedSockets.remove(socket);
            socket.close();
        } catch (IOException e) {
        }
    }

    void broadcast(byte[] line) throws IOException {
        for (Socket conn : connectedSockets) {
            OutputStream os = conn.getOutputStream();
            if (conn != socket) {
                os.write(line);
            }
        }
    }
}

