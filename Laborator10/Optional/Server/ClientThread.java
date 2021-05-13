package optional;

import optional.dao.LoginDAO;
import optional.dao.RelationDAO;
import optional.dao.ServerDAO;
import optional.dao.UserDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class ClientThread extends Thread {
    private final Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String request;
    private UserDAO userDAO;
    private RelationDAO relationDAO;
    private LoginDAO loginDAO;
    private String username;

    public ClientThread(Socket socket) {
        this.socket = socket;
        this.userDAO = new UserDAO();
        this.relationDAO = new RelationDAO();
        this.loginDAO = new LoginDAO();

        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println("[Client Thread Error] " + e.getMessage());
        }
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                if (request != null) {
                    System.out.println("Received: " + request);
                    out.println("Received: " + request);

                    if (request.equalsIgnoreCase("stop")) {
                        out.println("Server stopped");
                        ServerDAO.request = request;
                        in.close();
                        out.close();
                        socket.close();
                        ServerDAO.clientThreads.remove(this);
                        break;
                    } else if (request.toLowerCase().contains("register")) {
                        String[] parameters = request.split(" ");
                        userDAO.create(parameters[1], parameters[2], parameters[3]);
                    } else if (request.toLowerCase().contains("login")) {
                        String[] parameters = request.split(" ");
                        loginDAO.log(parameters[1]);
                        username = parameters[1];
                    } else if (request.toLowerCase().contains("friend")) {
                        String[] parameters = request.split(" ");
                        Arrays.stream(parameters).skip(1).forEach(friend -> relationDAO.friend(username, friend));
                    } else if (request.toLowerCase().contains("sendall")) {
                        String[] parameters = request.split(" ");
                        relationDAO.sendAll(username, parameters[1]);
                    } else if (request.toLowerCase().contains("send")) {
                        String[] parameters = request.split(" ");
                        relationDAO.sendMessage(username, parameters[1], parameters[2]);
                    } else if (request.equalsIgnoreCase("read")) {
                        System.out.println("sunt aici lol");
                        userDAO.read(username);
                    } else System.out.println("Unrecognized request");

                    request = null;
                } else {
                    String input = in.readLine();
                    if(input == null) {
                        System.out.println("Closing thread...");
                        ServerDAO.clientThreads.remove(this);
                        socket.close();
                        break;
                    }
                    if (input.equalsIgnoreCase("exit")) {
                        ServerDAO.clientThreads.remove(this);
                        socket.close();
                        break;
                    } else {
                        ServerDAO.request = input;
                    }
                }
            } catch (Exception e) {
                System.err.println("[Client Thread Error] " + e.getMessage());
            }
        }
    }
}
