package pieces;

import game.Board;

public class Rook extends Piece {
    public Rook(Board board, boolean isWhite , int col , int row) {
        super(board, isWhite, col , row);
        if(isWhite()) {
            image=getImage("/images/Light/Rook");
        }
        else {
            image=getImage("/images/Dark/Rook");
        }
    }
}
