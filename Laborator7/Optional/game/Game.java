/**
 * @author Alex Amarandei
 */

package optional.game;

import optional.player.Player;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Game {
    private static List<Player> players;
    private volatile static Board board;
    private static int tokenMaxIndex, currentTurn = 0;

    public static void setup(List<Player> players) {
        Game.players = players;
        Game.tokenMaxIndex = (int) (Math.random() * 5) + 2;
        Game.board = new Board(tokenMaxIndex);
    }

    public static void start() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(players.size());
        for(Player player : players)
            executorService.execute(player);

        Timekeeper timekeeper = new Timekeeper();
        timekeeper.setDaemon(true);
        timekeeper.start();

        executorService.shutdownNow();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

        printResults();
    }

    private static void printResults() {
        players.forEach(p -> System.out.println(p.getName() + " has got " + p.getScore() + " points."));

        players.sort(Comparator.comparing(Player::getScore).reversed());

        System.out.println("The winner is " + players.get(0).getName() + " with score " + players.get(0).getScore());
        System.out.println("Scoring is based on the longest path obtained from the tokens.");
    }

    public static Board getBoard() {
        return board;
    }

    public static int getPlayersSize() {
        return players.size();
    }

    public static int getCurrentTurn() {
        return currentTurn;
    }

    public static void nextTurn() {
        Game.currentTurn = (currentTurn + 1) % getPlayersSize();
    }
}
