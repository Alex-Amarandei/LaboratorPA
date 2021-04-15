/**
 * @author Alex Amarandei
 */

package optional;

import optional.game.Game;
import optional.player.RandomPlayer;
import optional.player.Player;
import optional.player.ManualPlayer;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        Player p1 = new ManualPlayer("User");
        Player p2 = new RandomPlayer("COM");
        Game.setup(Arrays.asList(p1, p2));
        Game.start();
    }
}
