package game;

import java.awt.*;

public class Square {
    private final int row, column;
    private final Color color;
    public static final int SQUARE_SIZE =100;
    //private Piece
    public Square (int row, int column, Color color){
        this.row=row;
        this.column=column;
        this.color = color;

    }

    public void draw(Graphics2D board) {
        board.setColor(color);
        board.fillRect(row, column, SQUARE_SIZE, SQUARE_SIZE);
    }
}
