package pieces;

import game.Board;

public class Queen extends Piece{
    public Queen(Board board, boolean isWhite, int col, int row) {
        super(board, isWhite, col , row);
        if(isWhite()) {
            image=getImage("/images/Light/Queen");
        }
        else {
            image=getImage("/images/Dark/Queen");
        }
    }
}
