package compulsory;

import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable {
    private final String name;
    private List<Token> tokens;
    private Game game;

    public Player(String name) {
        this.name = name;
        this.tokens = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (!takeToken(this.getName())) {
                    break;
                }
            } catch (NullPointerException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean takeToken(String name) throws NullPointerException, InterruptedException {
        if (!game.getTokens().isEmpty()) {
            int number = (int) (Math.random() * (game.getTokens().size() - 1));
            try {
                tokens.add(game.getTokens().get(number));
                game.removeToken(number, name);
                Thread.sleep(15);
            } catch (IndexOutOfBoundsException e) {
                return false;
            }
            return true;
        }
        return false;
    }

    public void printScore() {
        int sum = tokens.stream().mapToInt(Token::getValue).sum();

        StringBuilder stringBuilder = new StringBuilder(this.name);
        stringBuilder.append(" has a total score of ");
        stringBuilder.append(sum);
        stringBuilder.append(" points.");

        System.out.println(stringBuilder.toString());
    }

    public void printTokenNumber() {
        StringBuilder stringBuilder = new StringBuilder(this.name);
        stringBuilder.append(" has a total of ");
        stringBuilder.append(tokens.size());
        stringBuilder.append(" tokens.");

        System.out.println(stringBuilder.toString());
    }
}
