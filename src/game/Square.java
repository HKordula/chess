package game;

import java.awt.*;
import pieces.Piece;
public class Square {
    private final int row, column;
    private final Color color;
    public static final int SQUARE_SIZE =100;
    private Piece piece;
    public Square (int row, int column, Color color, Piece piece){
        this.row=row;
        this.column=column;
        this.color = color;
        this.piece = piece;

    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public void draw(Graphics2D square) {
        square.setColor(color);
        square.fillRect(row, column, SQUARE_SIZE, SQUARE_SIZE);
    }
}
