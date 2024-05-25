package pieces;

import game.Board;

public class Queen extends Piece{

    public Queen(Board board, boolean isWhite, int col, int row) {
        super(board, isWhite, col , row);
        if(isWhite()) {
            image=getImage("/images/Light/Queen");
        } else {
            image=getImage("/images/Dark/Queen");
        }
    }

    public boolean canMove(int col, int row) {
        return Math.abs(col - this.col) == Math.abs(row -this.row) ||  (col == this.col || row ==this.row);
    }

    public boolean moveCollision(int col, int row) {
        if ((col == this.col || row == this.row)) {
            // UP
            if (this.col > col)
                for (int c = this.col - 1; c > col; c--)
                    if (board.getPiece(c, this.row) != null)
                        return true;
            // DOWN
            if (this.col < col)
                for (int c = this.col + 1; c < col; c++)
                    if (board.getPiece(c, this.row) != null)
                        return true;
            // LEFT
            if (this.row > row)
                for (int r = this.row - 1; r > row; r--)
                    if (board.getPiece(this.col, r) != null)
                        return true;
            // RIGHT
            if (this.row < row)
                for (int r = this.row + 1; r < row; r++)
                    if (board.getPiece(this.col, r) != null)
                        return true;
        } else {
            // LEFT
            if(this.col > col) {
                // UP
                if (this.row > row)
                    for (int i = 1 ; i < Math.abs(this.col - col); i++)
                        if (board.getPiece(this.col - i, this.row - i) != null)
                            return true;
                // DOWN
                if (this.row < row)
                    for (int i = 1 ; i < Math.abs(this.col - col); i++)
                        if (board.getPiece(this.col - i, this.row + i) != null)
                            return true;
            }
            // RIGHT
            if(this.col < col) {
                // UP
                if (this.row > row)
                    for (int i = 1 ; i < Math.abs(this.col - col); i++)
                        if (board.getPiece(this.col + i, this.row - i) != null)
                            return true;
                // DOWN
                if (this.row < row)
                    for (int i = 1 ; i < Math.abs(this.col - col); i++)
                        if (board.getPiece(this.col + i, this.row + i) != null)
                            return true;
            }
        }
        return false;
    }
}