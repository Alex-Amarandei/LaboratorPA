/**
 * @author Alex Amarandei
 */

package optional.player;

import optional.game.Game;
import optional.game.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Player implements Runnable {
    private String name;
    private volatile List<Token> tokens = new ArrayList<>();

    private static int playerCount;
    private int playerNumber;
    Scanner scanner = new Scanner(System.in);

    public Player(String name) {
        Thread.currentThread().setName(name);
        this.name = name;
        this.playerNumber = playerCount;
        Player.playerCount++;
    }

    @Override
    public void run() {
        while(Game.getBoard().getSize() > 0) {
            synchronized (Game.getBoard()) {
                while (playerNumber != Game.getCurrentTurn()) {
                    try {
                        Game.getBoard().wait();
                    } catch (InterruptedException ignored) {}
                }

                System.out.println("Tokens on the board: " + Game.getBoard());
                Token token = pickToken(scanner);
                if(token != null) {
                    System.out.println(getName() + " took the " + token + " token.");
                    System.out.println();
                    tokens.add(token);
                }

                Game.nextTurn();
                Game.getBoard().notifyAll();
            }
        }
    }

    public abstract Token pickToken(Scanner scanner);

    public int getScore() {
        int maxLength = 0, length;
        boolean changeMade;
        List<Token> selected = new ArrayList<>();

        for(Token start : tokens) {
            selected.add(start); length = 1; changeMade = true;

            while(changeMade) {
                changeMade = false;

                for(Token t : tokens) {
                    if(!selected.contains(t)) {
                        if(selected.get(selected.size() - 1).getSecond() == t.getFirst() &&
                                selected.stream().noneMatch(tok -> tok.getFirst() == t.getSecond())) {
                            selected.add(t);
                            length++; changeMade = true;
                            break;
                        }
                    }
                }

                maxLength = Math.max(maxLength, length);
            }

            selected.clear();
        }

        return maxLength;
    }

    public String getName() {
        return name;
    }
}
