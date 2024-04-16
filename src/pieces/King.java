package pieces;

import game.Board;

public class King extends Piece{
    public King(Board board, boolean isWhite, int col , int row) {
        super(board, isWhite, col , row);
        if(isWhite()) {
            image=getImage("/images/Light/King");
        }
        else {
            image=getImage("/images/Dark/King");
        }
    }

    public boolean canMove(int col, int row) {
        return Math.abs((col - this.col)*(row-this.row)) == 1 || Math.abs(col - this.col)+Math.abs(row-this.row) == 1 || canCastle(col, row);
    }

    private boolean canCastle(int col, int row) {
        if(this.row == row ) {
            if( col == 6 || col == 7) {
                Piece rook = board.getPiece(7,row);
                if(rook != null && rook.isFirstMove && isFirstMove) {
                    return board.getPiece(5,row) == null && board.getPiece(6,row) == null;
                    // dodać warunek szachowania
                }
            } else if (col == 2 || col == 1 || col == 0) {
                Piece rook = board.getPiece(0,row);
                if(rook != null && rook.isFirstMove && isFirstMove) {
                    return board.getPiece(1,row) == null && board.getPiece(2,row) == null && board.getPiece(3,row) == null;
                    // dodać warunek szachowania
                }
            }
        }
        return false;
    }


}
