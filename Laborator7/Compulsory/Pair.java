package compulsory;

import java.util.Objects;

public class Pair {
    private final Integer first;
    private final Integer last;

    public Pair(int first, int last) {
        this.first = first;
        this.last = last;
    }

    public Integer getFirst() {
        return first;
    }

    public Integer getLast() {
        return last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair pair = (Pair) o;
        return getFirst().equals(pair.getFirst()) && getLast().equals(pair.getLast());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirst(), getLast());
    }

    @Override
    public String toString() {
        return first + ", " + last;
    }
}
