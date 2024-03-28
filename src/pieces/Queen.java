package pieces;

public class Queen extends Piece{
    public Queen(boolean isWhite, int col, int row) {
        super(isWhite, col , row);
        if(isWhite()) {
            image=getImage("/images/Light/Queen");
        }
        else {
            image=getImage("/images/Dark/Queen");
        }
    }
}
