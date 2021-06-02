package app.enums;

public enum Piece {
    WHITE, BLACK;

    @Override
    public String toString() {
        if(this.equals(WHITE)) return "WHITE";
        return "BLACK";
    }
}

