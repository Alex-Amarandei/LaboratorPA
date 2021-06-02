package app.server;

import app.Server;
import app.models.GameRoom;
import app.models.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;

import static app.enums.RoomType.*;

public class ClientThread implements Runnable {
    private final Socket client;
    private final BufferedReader fromClient;

    public ClientThread(Socket client) throws IOException {
        this.client = client;
        this.fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    @Override
    public void run() {
        try {
            String[] request = fromClient.readLine().split("&");
            Player player = new Player(client, request[0]);
            List<GameRoom> gameRooms = Server.getGameRooms();

            synchronized (Server.getGameRooms()) {
                if (request[1].equalsIgnoreCase("singleplayer")) {
                    gameRooms.add(new GameRoom(SINGLE, player, gameRooms.size() + 1));
                } else {
                    GameRoom gameRoom = Server.getGameRooms().stream().filter(room -> room.getRoomType().equals(MULTIPLAYER)).findFirst().orElse(null);

                    if (gameRoom == null) {
                        gameRooms.add(new GameRoom(MULTIPLAYER, player, gameRooms.size() + 1));
                    } else {
                        gameRoom.setPlayer2(player);
                        gameRoom.setRoomType(READY);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
