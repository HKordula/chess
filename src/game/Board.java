package game;

import pieces.*;

import java.awt.*;
import java.util.ArrayList;

public class Board {
    final int COLUMNS = 8;
    final int ROWS = 8;
    final private Square[][] squares = new Square[ROWS][COLUMNS];
    final private int startX, startY;
    public static ArrayList<Piece> pieces = new ArrayList<>();


    public Board(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
    }

    public void setPieces() {
        //WHITE
        for(int i = 0 ; i <8; i++) {
            pieces.add(new Pawn(this,true,i,6));
        }
        pieces.add(new Rook(this,true,0,7));
        pieces.add(new Knight(this,true,1,7));
        pieces.add(new Bishop(this,true,2,7));
        pieces.add(new Queen(this,true,3,7));
        pieces.add(new King(this,true,4,7));
        pieces.add(new Bishop(this,true,5,7));
        pieces.add(new Knight(this,true,6,7));
        pieces.add(new Rook(this,true,7,7));

        //BLACK
        for(int i = 0 ; i <8; i++) {
            pieces.add(new Pawn(this,false,i,1));
        }
        pieces.add(new Rook(this,false,0,0));
        pieces.add(new Knight(this,false,1,0));
        pieces.add(new Bishop(this,false,2,0));
        pieces.add(new Queen(this,false,3,0));
        pieces.add(new King(this,false,4,0));
        pieces.add(new Bishop(this,false,5,0));
        pieces.add(new Knight(this,false,6,0));
        pieces.add(new Rook(this,false,7,0));
    }

    public void draw(Graphics2D board) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                int x = startX + j * Square.SQUARE_SIZE;
                int y = startY + i * Square.SQUARE_SIZE;
                if ((i + j) % 2 == 0) {
                    squares[i][j] = new Square(x, y, new Color(235,232,210));
                } else {
                    squares[i][j] = new Square(x, y, new Color(75,115,153));
                }
                squares[i][j].draw(board);
            }
        }
        for(Piece p : pieces) {
            p.draw(board);
        }
    }
}
