package pieces;

public class Rook extends Piece {
    public Rook(boolean isWhite , int col ,int row) {
        super(isWhite, col , row);
        if(isWhite()) {
            image=getImage("/images/Light/Rook");
        }
        else {
            image=getImage("/images/Dark/Rook");
        }
    }
}
