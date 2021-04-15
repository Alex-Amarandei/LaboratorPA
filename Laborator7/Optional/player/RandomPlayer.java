/**
 * @author Alex Amarandei
 */

package optional.player;

import optional.game.Game;
import optional.game.Token;

import java.util.Random;
import java.util.Scanner;

public class RandomPlayer extends Player {
    private Random random = new Random();

    public RandomPlayer(String name) {
        super(name);
    }

    @Override
    public Token pickToken(Scanner scanner) {
        int number, first, second;

        if(Game.getBoard().getSize() == 0) return null;

        number = random.nextInt(Game.getBoard().getSize());
        first = Game.getBoard().getTokens().get(number).getFirst();
        second = Game.getBoard().getTokens().get(number).getSecond();

        return Game.getBoard().extractToken(first, second);
    }
}
