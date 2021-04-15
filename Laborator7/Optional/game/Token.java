/**
 * @author Alex Amarandei
 */

package optional.game;

import java.util.Objects;

public class Token {
    private int first, second;
    private int value;

    public Token(int first, int second, int value) {
        this.first = first;
        this.second = second;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "(" + first +
                ", " + second +
                ", " + value +
                ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token)) return false;
        Token token = (Token) o;
        return getFirst() == token.getFirst() && getSecond() == token.getSecond() && getValue() == token.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirst(), getSecond(), getValue());
    }
}
