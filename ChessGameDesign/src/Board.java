public class Board {
    private Cell[][] cells = new Cell[8][8];

    public Board() {
        for (int r = 0; r < 8; r++)
            for (int c = 0; c < 8; c++)
                cells[r][c] = new Cell(r, c);
    }

    public void setupInitialBoard() {
        // Add Pawns
        for (int c = 0; c < 8; c++) {
            cells[1][c].setPiece(new Pawn(false));
            cells[6][c].setPiece(new Pawn(true));
        }
        // Add Rooks, Knights, Bishops, Queen, King for both sides
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public void movePiece(Move move) {
        move.getEnd().setPiece(move.getMovedPiece());
        move.getStart().setPiece(null);
    }
}