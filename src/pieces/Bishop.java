package pieces;

public class Bishop extends Piece {
    public Bishop(boolean isWhite, int col, int row) {
        super(isWhite, col, row);
        if(isWhite()) {
            image=getImage("/images/Light/Bishop");
        }
        else {
            image=getImage("/images/Dark/Bishop");
        }
    }
}
