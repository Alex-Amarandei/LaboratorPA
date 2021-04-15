/**
 * @author Alex Amarandei
 */

package optional.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Board {
    private volatile List<Token> tokens = Collections.synchronizedList(new ArrayList<>());

    public Board(int tokenMaxIndex) {
        IntStream.range(1, tokenMaxIndex)
                .forEach(i -> IntStream.rangeClosed(1, tokenMaxIndex)
                        .forEach(j -> {
                            if (i != j)
                                tokens.add(new Token(i, j, (int) (Math.random() * 20)));
                        }));
    }

    public synchronized int getSize() {
        return tokens.size();
    }

    public synchronized Token extractToken(int first, int second) {
        Token pickedToken = null;
        for(Token token : tokens)
            if(token.getFirst() == first && token.getSecond() == second) {
                pickedToken = token;
                tokens.remove(token);
                break;
            }
        return pickedToken;
    }

    @Override
    public synchronized String toString() {
        return tokens.toString();
    }

    public List<Token> getTokens() {
        return tokens;
    }
}
