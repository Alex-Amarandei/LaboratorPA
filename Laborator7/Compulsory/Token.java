package compulsory;

import java.util.Objects;

public class Token implements Comparable<Token> {
    private final Pair pair;
    private final Integer value;

    public Token(Pair pair) {
        this.pair = pair;
        this.value = (int) (Math.random() * 100);
    }

    public Pair getPair() {
        return pair;
    }

    @Override
    public int compareTo(Token t) {
        return this.pair.getFirst().compareTo(t.pair.getFirst());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token)) return false;
        Token token = (Token) o;
        return getPair().equals(token.getPair());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPair());
    }

    @Override
    public String toString() {
        return "(" + pair + ", " + value + ")";
    }

    public int getValue() {
        return value;
    }
}
