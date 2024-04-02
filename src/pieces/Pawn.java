package pieces;

import game.Board;

public class Pawn extends Piece {
    public Pawn(Board board, boolean isWhite, int col, int row) {
        super(board,isWhite,col,row);
        if(isWhite()) {
            image=getImage("/images/Light/Pawn");
        }
        else {
            image=getImage("/images/Dark/Pawn");
        }
    }

}
