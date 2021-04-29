import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static final String address = "127.0.0.1";
    public static final int PORT = 2111;

    public static void main(String[] args) {
        try(Socket socket = new Socket(address, PORT)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner scanner = new Scanner(System.in);

            while (true) {
                String request = scanner.nextLine();
                out.println(request);
                
                if(request.equals("exit")) {
                    System.out.println("Exiting client...");
                    break;
                }

                String response = in.readLine();

                System.out.println("Response received: " + response);
                if(response.equalsIgnoreCase("Server stopped")) break;
            }
        } catch(IOException e) {
            System.err.println("[Client Error] " + e.getMessage());
        }
    }
}
