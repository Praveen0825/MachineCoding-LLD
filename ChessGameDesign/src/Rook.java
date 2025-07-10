import java.util.List;

public class Rook extends Piece{
    public Rook(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public List<Move> getValidMoves(Board board, Cell currentCell) {
        return null;
    }
}
