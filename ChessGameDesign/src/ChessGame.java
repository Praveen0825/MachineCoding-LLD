import java.util.ArrayList;
import java.util.List;

public class ChessGame {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentTurn;
    private List<Move> moveHistory;
    private GameStatus status;

    public ChessGame() {
        this.board = new Board();
        this.player1 = new Player(true);
        this.player2 = new Player(false);
        this.currentTurn = player1;
        this.status = GameStatus.ONGOING;
        this.moveHistory = new ArrayList<>();
        board.setupInitialBoard();
    }

    public boolean makeMove(Move move) {
        if (!MoveValidator.isValidMove(board, move, currentTurn)) return false;

        board.movePiece(move);
        moveHistory.add(move);

        updateGameStatus();
        switchTurn();
        return true;
    }

    private void switchTurn() {
        currentTurn = (currentTurn == player1) ? player2 : player1;
    }

    private void updateGameStatus() {
        // check for checkmate, stalemate, draw
    }

}
