package app.models;

import app.enums.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static app.enums.Piece.BLACK;
import static app.enums.Piece.WHITE;

public class Board {
    private List<ArrayList<Piece>> spots;
    private Piece turn;
    private int[] dice;
    private int whiteTaken;
    private int blackTaken;
    private int whiteOut;
    private int blackOut;
    private int[] moves;
    private int currentMove;


    public Board() {
        prepareSpots();
        placePieces();
        initialize();
    }

    private void prepareSpots() {
        spots = new ArrayList<>();
        for (int i = 0; i < 24; i++) spots.add(new ArrayList<>());
    }

    private void placePieces() {
        for (int i = 0; i < 2; i++) {
            spots.get(0).add(WHITE);
            spots.get(23).add(BLACK);
        }

        for (int i = 0; i < 3; i++) {
            spots.get(16).add(WHITE);
            spots.get(7).add(BLACK);
        }

        for (int i = 0; i < 5; i++) {
            spots.get(11).add(WHITE);
            spots.get(18).add(WHITE);

            spots.get(5).add(BLACK);
            spots.get(12).add(BLACK);
        }
    }

    private void initialize() {
        turn = WHITE;
        dice = IntStream.of(6, 6).toArray();
        whiteTaken = 0;
        blackTaken = 0;
        whiteOut = 0;
        blackOut = 0;
        moves = IntStream.of(-1, -1, -1, -1).toArray();
        currentMove = -1;
    }

    public void updateMoves() {
        if (dice[0] == dice[1]) {
            Arrays.stream(moves).forEach(m -> m = dice[0]);
        } else {
            moves[0] = dice[0];
            moves[1] = dice[1];
            moves[2] = -1;
            moves[3] = -1;
        }
    }

    public List<ArrayList<Piece>> getSpots() {
        return spots;
    }

    public void setSpots(List<ArrayList<Piece>> spots) {
        this.spots = spots;
    }

    public Piece getTurn() {
        return turn;
    }

    public void setTurn(Piece turn) {
        this.turn = turn;
    }

    public int[] getDice() {
        return dice;
    }

    public void setDice(int[] dice) {
        this.dice = dice;
    }

    public int getWhiteTaken() {
        return whiteTaken;
    }

    public void setWhiteTaken(int whiteTaken) {
        this.whiteTaken = whiteTaken;
    }

    public int getBlackTaken() {
        return blackTaken;
    }

    public void setBlackTaken(int blackTaken) {
        this.blackTaken = blackTaken;
    }

    public int getWhiteOut() {
        return whiteOut;
    }

    public void setWhiteOut(int whiteOut) {
        this.whiteOut = whiteOut;
    }

    public int getBlackOut() {
        return blackOut;
    }

    public void setBlackOut(int blackOut) {
        this.blackOut = blackOut;
    }

    public int[] getMoves() {
        return moves;
    }

    public void setMoves(int[] moves) {
        this.moves = moves;
    }

    public int getCurrentMove() {
        return currentMove;
    }

    public void setCurrentMove(int currentMove) {
        this.currentMove = currentMove;
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder(turn.toString());
        board.append(":");

        for (int i = 0; i < 24; i++) {
            if (spots.get(i).isEmpty()) board.append("x");
            else {
                if (spots.get(i).get(0).equals(WHITE)) board.append("w");
                else board.append("b");
                board.append(spots.get(i).size());
            }
            board.append("&");
        }
        board.deleteCharAt(board.length() - 1).append(":");

        board.append(whiteTaken).append(":");
        board.append(blackTaken).append(":");

        board.append(whiteOut).append(":");
        board.append(blackOut).append(":");

        board.append(dice[0]).append("&").append(dice[1]);

        return board.toString();
    }

    public boolean move(Piece piece, String move) {
        String[] moves = move.split("&");
        int from = Integer.parseInt(moves[0]);
        int to = Integer.parseInt(moves[1]);

        if (moveIsPossible(piece, from, to) && pieceExists(piece, from)) {
            if (isSpotAvailable(piece, to)) {
                spots.get(from).remove(0);
                spots.get(to).add(piece);

                return true;
            } else if (isSinglePiece(piece, to)) {
                spots.get(from).remove(0);
                spots.get(to).remove(0);
                spots.get(to).add(piece);

                if (piece.equals(WHITE)) blackTaken++;
                else whiteTaken++;

                return true;
            }
        }

        return false;
    }

    public boolean moveIsPossible(Piece piece, int from, int to) {
        if (piece.equals(WHITE)) {
            if (from > to)
                return false;
            for (int i = 0; i < 4; i++)
                if (moves[i] == to - from) {
                    currentMove = to - from;
                    return true;
                }
            return false;
        }

        if (from < to)
            return false;
        for (int i = 0; i < 4; i++)
            if (moves[i] == from - to) {
                currentMove = from - to;
                return true;
            }
        return false;
    }

    public boolean pieceExists(Piece piece, int index) {
        return spots.get(index).contains(piece);
    }

    public boolean isSpotAvailable(Piece piece, int index) {
        return (spots.get(index).contains(piece) || spots.get(index).isEmpty());
    }

    public boolean isSinglePiece(Piece piece, int index) {
        if (piece.equals(WHITE))
            return (spots.get(index).contains(BLACK) && spots.get(index).size() == 1);
        return (spots.get(index).contains(WHITE) && spots.get(index).size() == 1);
    }
}
