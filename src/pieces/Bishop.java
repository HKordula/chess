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
}
