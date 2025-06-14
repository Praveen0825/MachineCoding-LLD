import entity.Game;
import entity.Player;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("Player 1", 'X');
        Player player2 = new Player("Player 2", 'O');

        Game game = new Game(3,player1, player2);
        game.play();
        //System.out.println("Hello world!");
    }
}