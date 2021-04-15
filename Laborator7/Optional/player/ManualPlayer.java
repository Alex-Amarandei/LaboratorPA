/**
 * @author Alex Amarandei
 */

package optional.player;

import optional.game.Game;
import optional.game.Token;

import java.util.Scanner;

public class ManualPlayer extends Player {
    public ManualPlayer(String name) {
        super(name);
    }

    @Override
    public Token pickToken(Scanner scanner) {
        Token token = null;
        int first, second;
        do {
            if(Game.getBoard().getSize() <= 0) break;
            System.out.print(getName()  + ", it's your turn. Choose a token by typing the first and second values: ");
            first = scanner.nextInt();
            second = scanner.nextInt();
            token = Game.getBoard().extractToken(first, second);
            if (token == null)
                System.out.println("The (" + first + ", " + second + ") does not exist.");
        } while (token == null);

        return token;
    }
}
