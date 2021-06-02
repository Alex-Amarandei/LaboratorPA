package app;

import app.models.GameRoom;
import app.server.ClientThread;
import app.server.GameRoomSupervisor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 42180;
    private static final GameRoomSupervisor supervisor = new GameRoomSupervisor();
    private static final List<GameRoom> gameRooms = new ArrayList<>();
    private static final List<ClientThread> clients = new ArrayList<>();
    private static final ExecutorService pool = Executors.newFixedThreadPool(9);


    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PORT);
        pool.execute(supervisor);

        while (true) {
            System.out.println("[SERVER] Waiting for clients...");
            Socket client = listener.accept();
            System.out.println("[SERVER] Connected to client!");

            ClientThread clientThread = new ClientThread(client);
            clients.add(clientThread);

            pool.execute(clientThread);
        }
    }

    public static int getPORT() {
        return PORT;
    }

    public static List<GameRoom> getGameRooms() {
        return gameRooms;
    }

    public static List<ClientThread> getClients() {
        return clients;
    }

    public static ExecutorService getPool() {
        return pool;
    }
}
