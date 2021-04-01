package compulsory;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(6);
        game.getPlayers().add(new Player("Player 1"));
        game.getPlayers().add(new Player("Player 2"));
        game.getPlayers().add(new Player("Player 3"));

        game.getPlayers().stream().parallel().forEach(player -> player.setGame(game));
        game.play();
    }
}
