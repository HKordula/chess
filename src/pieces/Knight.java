package pieces;

public class Knight extends Piece {
    public Knight(boolean isWhite, int col , int row) {
        super(isWhite, col , row);
        if(isWhite()) {
            image=getImage("/images/Light/Knight");
        }
        else {
            image=getImage("/images/Dark/Knight");
        }
    }
}
