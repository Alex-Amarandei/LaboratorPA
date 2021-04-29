import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private final Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String request;

    public ClientThread(Socket socket) {
        this.socket = socket;

        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
        }
        catch(IOException e) {
            System.err.println("[Client Thread Error] " + e.getMessage());
        }
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
                if(request != null) {
                    if(request.equalsIgnoreCase("stop")) {
                        out.println("Server stopped");
                        ServerSocketManager.request = request;
                        in.close();
                        out.close();
                        socket.close();
                        ServerSocketManager.clientThreads.remove(this);
                        break;
                    } else {
                        System.out.println("Server received the request: " + request);
                        out.println("Server received the request: " + request);
                    }
                    request = null;
                } else {
                    String input = in.readLine();
                    if(input.equalsIgnoreCase("exit")) {
                        ServerSocketManager.clientThreads.remove(this);
                        socket.close();
                        break;
                    } else {
                        ServerSocketManager.request = input;
                    }
                }
            }
            catch (Exception e) {
                System.err.println("[Client Thread Error] " + e.getMessage());
            }
        }
    }
}
