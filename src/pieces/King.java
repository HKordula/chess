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
}
