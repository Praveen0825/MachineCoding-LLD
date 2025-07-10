public class Move {
    private Cell start, end;
    private Piece movedPiece;
    private Piece capturedPiece;

    public Move(Cell start, Cell end) {
        this.start = start;
        this.end = end;
        this.movedPiece = start.getPiece();
        this.capturedPiece = end.getPiece();
    }

    public Cell getStart() {
        return start;
    }

    public void setStart(Cell start) {
        this.start = start;
    }

    public Cell getEnd() {
        return end;
    }

    public void setEnd(Cell end) {
        this.end = end;
    }

    public void setMovedPiece(Piece movedPiece) {
        this.movedPiece = movedPiece;
    }

    public Piece getCapturedPiece() {
        return capturedPiece;
    }

    public void setCapturedPiece(Piece capturedPiece) {
        this.capturedPiece = capturedPiece;
    }

    public Piece getMovedPiece() {
        return movedPiece;
    }
}
