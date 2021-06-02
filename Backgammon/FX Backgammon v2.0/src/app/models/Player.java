package app.models;

import java.net.Socket;
import java.security.SecureRandom;

public class Player {
    private final Socket socket;
    private final String username;

    public Player(Socket socket, String username) {
        this.socket = socket;
        this.username = username;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getUsername() {
        return username;
    }

    public int[] roll() {
        return (new SecureRandom()).ints(2, 1, 7).toArray();
    }

//    public boolean move(Board board, Piece piece) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        String move = in.readLine();
//        String[] moves = move.split("&");
//        int from = Integer.parseInt(moves[0]);
//        int to = Integer.parseInt(moves[1]);
//
//        if (moveIsPossible(board, piece, from, to) && pieceExists(board, piece, from)) {
//            if (isSpotAvailable(board, piece, to)) {
//                board.getSpots().get(from).remove(0);
//                board.getSpots().get(to).add(piece);
//
//                return true;
//            } else if (isSinglePiece(board, piece, to)) {
//                board.getSpots().get(from).remove(0);
//                board.getSpots().get(to).remove(0);
//                board.getSpots().get(to).add(piece);
//
//                if (piece.equals(Piece.WHITE))
//                    board.setBlackOut(board.getBlackOut() + 1);
//                else
//                    board.setWhiteOut(board.getWhiteOut() + 1);
//
//                return true;
//            }
//        }
//
//        return false;
//    }
}
