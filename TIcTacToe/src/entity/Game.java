package entity;

import java.util.Scanner;

public class Game {
    private final Player player1;
    private final Player player2;
    private final Board board;
    private Player currentPlayer;

    public Game(int size,Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board(size);
        this.currentPlayer = player1;
    }

    public void play() {
        board.displayBoard();

        while (!board.isFull()) {
            System.out.println(currentPlayer.getName() + "'s turn.");
            int row = getValidInput("Enter row: ");
            int col = getValidInput("Enter column: ");

            try {
                board.placeMove(row, col, currentPlayer.getSymbol());
                if(board.hasWinner(currentPlayer.getSymbol()))
                {
                    System.out.println(currentPlayer.getName() + " wins!");
                }
                board.displayBoard();
                switchPlayer();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("It's a draw!");
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    private int getValidInput(String message) {
        Scanner scanner = new Scanner(System.in);
        int input;

        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= 0 && input <= 2) {
                    return input;
                }
            } else {
                scanner.next();
            }
            System.out.println("Invalid input! Please enter a number between 0 and 2.");
        }
    }
}
