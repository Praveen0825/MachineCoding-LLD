package entity;

public class Board {
    private char[][] board;
    private int moveCount;
    private int size;

    public Board(int size) {
        this.size = size;
        board = new char[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                board[i][j] = '_';
    }

    public boolean placeMove(int row,int col, char symbol){
        if (row < 0 || row >= size || col < 0 || col >= size)
            return false;
        if (board[row][col] != ' ')
            return false;

        board[row][col] = symbol;
        moveCount++;
        return true;
    }

    void displayBoard(){
        for(int i=0;i<size;i++){
            for (int j = 0; j < size; j++) {
                System.out.print(" " + board[i][j] + " ");
                if (j != size - 1) System.out.print("|");
            }
            System.out.println();
            if (i != size - 1)
                System.out.println("---+---+---");
        }
    }

    public boolean isFull(){
        return moveCount==size*size;
    }

    public boolean hasWinner(char symbol){
        int cnt=0;
        for(int row=0;row<size;row++) {
            cnt=0;
            for (int col = 0; col < size; col++) {
                if (board[row][col] != symbol) {
                    break;
                }
                cnt++;
            }

            return cnt==size;
        }
        for(int col=0;col<size;col++) {
            cnt=0;
            for (int row = 0; row < size; col++) {
                if (board[row][col] != symbol) {
                    break;
                }
                cnt++;
            }

            return cnt==size;
        }
        cnt=0;
        for (int i = 0; i < size; i++) {
            if (board[i][i] != symbol) {
                break;
            }
            cnt++;

        }
        if(cnt==size)
            return true;
        cnt=0;
        for (int i = 0; i < size; i++) {
            if (board[i][size - 1 - i] != symbol) {
                break;
            }
            cnt++;

        }
        return cnt==size;

    }


}
