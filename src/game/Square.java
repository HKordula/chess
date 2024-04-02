package game;

import java.awt.*;
import pieces.Piece;
public class Square {
    private final int row, column;
    private final Color color;
    public static final int SQUARE_SIZE =100;
    public Square (int row, int column, Color color){
        this.row=row;
        this.column=column;
        this.color = color;
    }
    public void draw(Graphics2D square) {
        square.setColor(color);
        square.fillRect(row, column, SQUARE_SIZE, SQUARE_SIZE);
    }
}
