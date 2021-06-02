package app.server;

import app.Server;
import app.enums.RoomType;
import app.models.GameRoom;

import static java.lang.Thread.sleep;

public class GameRoomSupervisor implements Runnable {
    @Override
    public void run() {
        while (true) {
            for (GameRoom room : Server.getGameRooms()) {
                if (!room.isPlaying()) {
                    if (room.getRoomType().equals(RoomType.READY) ||
                            room.getRoomType().equals(RoomType.SINGLE)) {
                        Server.getPool().execute(new GameThread(room));
                        room.setPlaying(true);
                        System.out.println("Starting game for Game Room #" + room.getNumber());
                    }
                }
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
