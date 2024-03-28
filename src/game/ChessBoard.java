package game;

import org.w3c.dom.css.RGBColor;

import java.awt.*;

public class ChessBoard {
    final int COLUMNS = 8;
    final int ROWS = 8;
    final private Square[][] squares = new Square[ROWS][COLUMNS];
    final private int startX, startY;

    public ChessBoard(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
    }

    public void draw(Graphics2D board) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                int x = startX + j * Square.SQUARE_SIZE;
                int y = startY + i * Square.SQUARE_SIZE;
                if ((i + j) % 2 == 0) {
                    squares[i][j] = new Square(x, y, Color.WHITE, null);
                } else {
                    squares[i][j] = new Square(x, y, Color.BLACK, null);
                }
                squares[i][j].draw(board);
            }
        }
    }
}
