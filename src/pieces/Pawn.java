package pieces;
public class Pawn extends Piece {
    public Pawn(boolean isWhite, int col, int row) {
        super(isWhite,col,row);
        if(isWhite()) {
            image=getImage("/images/Light/Pawn");
        }
        else {
            image=getImage("/images/Dark/Pawn");
        }
    }

}
