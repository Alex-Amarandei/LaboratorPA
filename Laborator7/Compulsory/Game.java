package compulsory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Game {
    private List<Player> players;
    private List<Token> tokens;

    public Game(int number) {
        this.players = new ArrayList<>();
        this.tokens = new ArrayList<>();

        IntStream.range(0, number)
                .forEach(i -> IntStream.range(0, number)
                        .forEach(j -> {
                            if (i != j)
                                tokens.add(new Token(new Pair(i, j)));
                        }));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    synchronized final void removeToken(int index, String name) throws NullPointerException {
        System.out.println("The " + tokens.get(index) + " token was picked by " + name);
        tokens.remove(index);
    }

    public void play() {
        System.out.println("Tokens: ");
        tokens.forEach(t -> System.out.print(t + " "));
        System.out.println("\n");

        ExecutorService executorService = Executors.newFixedThreadPool(players.size());
        players.forEach(executorService::execute);

        try {
            executorService.shutdown();
            executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        players.forEach(Player::printScore);
        System.out.println();
        players.forEach(Player::printTokenNumber);
    }
}
