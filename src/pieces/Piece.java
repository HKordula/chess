package pieces;

import game.Board;
import game.Square;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class Piece {
    private final boolean isWhite;
    public BufferedImage image;
    public int x,y;
    public int col,row,preCol,preRow;
    Board board;


    public Piece(Board board, boolean isWhite, int col, int row) {
        this.board=board;
        this.isWhite = isWhite;
        this.col = col;
        this.row = row;
        x =getX(col);
        y = getY(row);
        preCol=col;
        preRow=row;
    }

    public int getX(int col) {
        return col * Square.SQUARE_SIZE;
    }
    public int getY(int row) {
        return row * Square.SQUARE_SIZE;
    }
    public BufferedImage getImage(String path) {
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + ".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  image;
    }
    public boolean isWhite() {
        return isWhite;
    }
    public void draw(Graphics2D g) {
        g.drawImage(image,x+50,y+50,Square.SQUARE_SIZE,Square.SQUARE_SIZE,null);
    }
}
