package pieces;

import game.Board;

public class Knight extends Piece {
    public Knight(Board board, boolean isWhite, int col , int row) {
        super(board, isWhite, col , row);
        if(isWhite()) {
            image=getImage("/images/Light/Knight");
        }
        else {
            image=getImage("/images/Dark/Knight");
        }
    }
}
