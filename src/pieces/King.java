package pieces;

public class King extends Piece{
    public King(boolean isWhite, int col , int row) {
        super(isWhite, col , row);
        if(isWhite()) {
            image=getImage("/images/Light/King");
        }
        else {
            image=getImage("/images/Dark/King");
        }
    }
}
