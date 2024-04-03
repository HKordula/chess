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
//return col == this.col || row == this.row;
    public boolean canMove(int col, int row) {
        return Math.abs((col - this.col)*(row-this.row)) == 1 || Math.abs(col - this.col)+Math.abs(row-this.row) == 1;
    }


}
