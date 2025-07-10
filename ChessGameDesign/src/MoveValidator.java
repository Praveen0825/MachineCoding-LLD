import java.util.List;

public class MoveValidator {
    public static boolean isValidMove(Board board, Move move, Player player) {
        Piece piece = move.getMovedPiece();
        if (piece == null || piece.isWhite() != player.isWhite()) return false;

        List<Move> validMoves = piece.getValidMoves(board, move.getStart());
        return validMoves.contains(move);
    }
}
