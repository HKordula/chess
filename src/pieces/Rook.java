package pieces;

import game.Board;

public class Rook extends Piece {

    public Rook(Board board, boolean isWhite, int col, int row) {
        super(board, isWhite, col, row);
        if (isWhite()) {
            image = getImage("/images/Light/Rook");
        } else {
            image = getImage("/images/Dark/Rook");
        }
    }

    public boolean canMove(int col, int row) {
        return col == this.col || row == this.row;
    }

    public boolean moveCollision(int col, int row) {
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
        return false;
    }
}