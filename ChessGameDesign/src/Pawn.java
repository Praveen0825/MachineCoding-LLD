import java.util.List;

public class Pawn extends Piece{
    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public List<Move> getValidMoves(Board board, Cell currentCell) {
        return null;
    }
}
