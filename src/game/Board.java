package game;

import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board {
    final int COLUMNS = 8;
    final int ROWS = 8;
    final private Square[][] squares = new Square[ROWS][COLUMNS];
    public final int startX;
    public final int startY;
    public static ArrayList<Piece> pieces = new ArrayList<>();

    public Piece selectedPiece;


    public Board(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
        setPieces();
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

    public Piece getPiece(int col, int row) {
        for( Piece piece : pieces) {
            if(piece.col == col && piece.row == row) {
                return piece;
            }
        }
        return null;
    }

    public void makeMove(Move move) {
        move.piece.col = move.postCol;
        move.piece.row = move.postRow;
        move.piece.x = move.postCol * Square.SQUARE_SIZE;
        move.piece.y = move.postRow * Square.SQUARE_SIZE;

        move.piece.isFirstMove = false;

        capture(move);
    }
    public void capture(Move move) {
        pieces.remove(move.capture);
    }

    public  boolean isValidMove(Move move) {
        if(sameTeam(move.piece, move.capture))
            return false;
        if (move.postCol < 0 || move.postCol >7 || move.postRow < 0 || move.postRow >7)
            return  false;
        if (!move.piece.canMove(move.postCol, move.postRow))
            return false;
        if (move.piece.moveCollision(move.postCol, move.postRow))
            return false;

        return true;
    }
    public boolean sameTeam(Piece p1, Piece p2) {
        if (p1 == null || p2 == null) {
            return false;
        }
        return p1.isWhite() == p2.isWhite();
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

        if (selectedPiece != null) {
            for(int i = 0 ; i < ROWS; i++) {
                for (int j = 0 ; j < COLUMNS ; j++) {
                    if(isValidMove(new Move(this,selectedPiece,i,j))) {
                        board.setColor(new Color(54, 183, 54, 179));
                        board.fillRect(i * Square.SQUARE_SIZE +50, j*Square.SQUARE_SIZE +50,Square.SQUARE_SIZE,Square.SQUARE_SIZE);
                    }
                }
            }
        }

        ArrayList<Piece> piecesCopy = new ArrayList<>(pieces);
        for(Piece p : piecesCopy) {
            if(p != selectedPiece) {
                p.draw(board);
            }
            if(selectedPiece != null) {
                selectedPiece.draw(board);
            }

        }
    }
}
