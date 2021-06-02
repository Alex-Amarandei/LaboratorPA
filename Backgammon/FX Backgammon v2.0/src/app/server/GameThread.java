package app.server;

import app.enums.Piece;
import app.models.Board;
import app.models.GameRoom;
import app.models.Player;

import java.io.*;
import java.util.Arrays;

import static app.enums.Piece.BLACK;
import static app.enums.Piece.WHITE;
import static app.enums.RoomType.SINGLE;

public class GameThread implements Runnable {
    private final GameRoom gameRoom;
    private final Board board;

    public GameThread(GameRoom gameRoom) {
        this.gameRoom = gameRoom;
        this.board = new Board();
    }

    @Override
    public void run() {
        try {
            if (gameRoom.getRoomType().equals(SINGLE)) singlePlayerGame();
            else multiplayerGame();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void singlePlayerGame() throws IOException {
        sendUIData(WHITE,
                new PrintWriter(gameRoom.getPlayer1().getSocket()
                        .getOutputStream(),
                        true));
    }

    private void multiplayerGame() throws IOException {
        Player white = gameRoom.getPlayer1();
        BufferedReader fromWhite = new BufferedReader(new InputStreamReader(white.getSocket().getInputStream()));
        PrintWriter toWhite = new PrintWriter(new OutputStreamWriter(white.getSocket().getOutputStream()), true);
        sendUIData(WHITE, toWhite);

        Player black = gameRoom.getPlayer2();
        BufferedReader fromBlack = new BufferedReader(new InputStreamReader(black.getSocket().getInputStream()));
        PrintWriter toBlack = new PrintWriter(new OutputStreamWriter(black.getSocket().getOutputStream()), true);
        sendUIData(BLACK, toBlack);

        while (board.getWhiteOut() != 15 && board.getBlackOut() != 15) {
            toWhite.println(board);
            toBlack.println(board);

            switch (board.getTurn()) {
                case WHITE -> playTurn(white, fromWhite, toWhite, WHITE, toBlack);
                case BLACK -> playTurn(black, fromBlack, toBlack, BLACK, toWhite);
            }
        }
    }

    private void playTurn(Player player, BufferedReader fromPlayer, PrintWriter toPlayer, Piece turn, PrintWriter toObserver) {
        try {
            fromPlayer.readLine();
            board.setDice(player.roll());
            board.updateMoves();

            while(Arrays.stream(board.getMoves()).filter(m -> m == -1).count() < 4) {
                toPlayer.println(board);
                toObserver.println(board);
                String playerMove = fromPlayer.readLine();

                if(playerMove.equals("ROLL"))
                    playerMove = fromPlayer.readLine();

                System.out.println(playerMove);
                if(board.move(turn, playerMove)) {
                    for (int i = 0; i < 4; i++) {
                        if (board.getMoves()[i] == board.getCurrentMove()) {
                            board.getMoves()[i] = -1;
                            break;
                        }
                    }
                } else toPlayer.println("INVALID");
            }

            board.setTurn(turn.equals(WHITE) ? BLACK : WHITE);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void sendUIData(Piece playerType, PrintWriter toPlayer) {
        StringBuilder gameData = new StringBuilder(gameRoom.getPlayer1().getUsername());
        gameData.append("&").append(gameRoom.getPlayer2().getUsername());
        gameData.append("&").append(gameRoom.getNumber());
        gameData.append("&").append(playerType.toString());

        toPlayer.println(gameData);
    }
}
