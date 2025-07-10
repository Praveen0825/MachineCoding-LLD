import java.util.List;


public abstract class Piece {
    protected boolean isWhite;

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public abstract List<Move> getValidMoves(Board board, Cell currentCell);
}

