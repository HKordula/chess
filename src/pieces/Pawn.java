package pieces;

import game.Board;

public class Pawn extends Piece {

    public Pawn(Board board, boolean isWhite, int col, int row) {
        super(board,isWhite,col,row);
        if(isWhite()) {
            image=getImage("/images/Light/Pawn");
        } else {
            image=getImage("/images/Dark/Pawn");
        }
    }

    public boolean canMove(int col, int row) {
        int direction = isWhite() ? 1 : -1;
        if(this.col == col && row == this.row - direction && board.getPiece(col,row) == null)
            return true;
        if(isFirstMove && this.col == col && row == this.row - direction * 2 && board.getPiece(col,row) == null && board.getPiece(col, row + direction) ==null)
            return true;
        if(col == this.col - 1 && row == this.row - direction && board.getPiece(col,row) != null)
            return true;
        if(col == this.col + 1 && row == this.row - direction && board.getPiece(col,row) != null)
            return true;
        if(board.getTileNum(col, row) == board.enPassantTarget && col == this.col - 1 && row == this.row - direction && board.getPiece(col, row + direction) != null){
            return true;
        }
        if(board.getTileNum(col, row) == board.enPassantTarget && col == this.col + 1 && row == this.row - direction && board.getPiece(col, row + direction) != null){
            return true;
        }
        return false;
    }
}