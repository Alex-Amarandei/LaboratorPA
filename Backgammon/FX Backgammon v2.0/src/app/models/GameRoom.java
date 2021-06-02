package app.models;

import app.enums.RoomType;

public class GameRoom {
    private Player player1;
    private Player player2;
    private RoomType roomType;
    private boolean playing = false;
    private int number;

    public GameRoom(RoomType roomType, int number) {
        this.roomType = roomType;
        this.number = number;
    }

    public GameRoom(RoomType roomType, Player player1, int number) {
        this.roomType = roomType;
        this.player1 = player1;
        this.number = number;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
}
