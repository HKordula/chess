package pieces;

import game.Board;

public class Bishop extends Piece {
    public Bishop(Board board, boolean isWhite, int col, int row) {
        super(board, isWhite, col, row);
        if(isWhite()) {
            image=getImage("/images/Light/Bishop");
        }
        else {
            image=getImage("/images/Dark/Bishop");
        }
    }
    public boolean canMove(int col, int row) {
        return Math.abs(col - this.col) == Math.abs(row -this.row);
    }
    public boolean moveCollision(int col, int row) {

        if (this.col > col && this.row > row)
            for (int i = 1 ; i < Math.abs(this.col - col); i++)
                if (board.getPiece(this.col - i, this.row - i) != null)
                    return true;

        if (this.col < col && this.row > row)
            for (int i = 1 ; i < Math.abs(this.col - col); i++)
                if (board.getPiece(this.col + i, this.row - i) != null)
                    return true;

        if (this.col > col && this.row < row)
            for (int i = 1 ; i < Math.abs(this.col - col); i++)
                if (board.getPiece(this.col - i, this.row + i) != null)
                    return true;

        if (this.col < col && this.row < row)
            for (int i = 1 ; i < Math.abs(this.col - col); i++)
                if (board.getPiece(this.col + i, this.row + i) != null)
                    return true;

        return false;
    }
}
