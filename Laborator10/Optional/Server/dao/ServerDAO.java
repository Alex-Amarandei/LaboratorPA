package optional.dao;

import optional.ClientThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class ServerDAO {
    public static String request;
    public static List<ClientThread> clientThreads = new ArrayList<>();

    public static void createSocket(int PORT) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            serverSocket.setSoTimeout(1000);
            Socket socket = null;
            System.out.println("Waiting for client...");

            while (true) {
                try {
                    socket = serverSocket.accept();
                } catch (SocketTimeoutException e) {
                    if (request != null) {
                        clientThreads.forEach(thread -> thread.setRequest(request));
                        if (request.equalsIgnoreCase("stop"))
                            break;
                        request = null;
                    }
                }

                if (socket != null) {
                    ClientThread clientThread = new ClientThread(socket);
                    clientThreads.add(clientThread);
                    clientThread.start();
                    socket = null;
                }
            }
        } catch (IOException e) {
            System.err.println("[Server Error] " + e.getMessage());
        }
    }
}
