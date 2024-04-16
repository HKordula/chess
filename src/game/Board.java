package game;

import pieces.*;

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
    public int enPassantTarget = -1;
    Game game;

    public Board(int startX, int startY, Game game) {
        this.startX = startX;
        this.startY = startY;
        this.game = game;
        setPieces();
    }

    public int getTileNum(int col, int row) {
        return row * ROWS + col;
    }

    public void setPieces() {
        //WHITE
        for(int i = 0 ; i <8; i++) {
            pieces.add(new Pawn(this,true,i,6));
        }
        pieces.add(new Rook(this,true,0,7));
//        pieces.add(new Knight(this,true,1,7));
//        pieces.add(new Bishop(this,true,2,7));
//        pieces.add(new Queen(this,true,3,7));
        pieces.add(new King(this,true,4,7));
//        pieces.add(new Bishop(this,true,5,7));
//        pieces.add(new Knight(this,true,6,7));
        pieces.add(new Rook(this,true,7,7));

        //BLACK
        for(int i = 0 ; i <8; i++) {
            pieces.add(new Pawn(this,false,i,1));
        }
        pieces.add(new Rook(this,false,0,0));
//        pieces.add(new Knight(this,false,1,0));
//        pieces.add(new Bishop(this,false,2,0));
//        pieces.add(new Queen(this,false,3,0));
        pieces.add(new King(this,false,4,0));
//        pieces.add(new Bishop(this,false,5,0));
//        pieces.add(new Knight(this,false,6,0));
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
        if(move.piece instanceof Pawn) {
            pawnMove(move);
        } else if (move.piece instanceof  King) {
            kingMove(move);
        } else {
            move.piece.col = move.postCol;
            move.piece.row = move.postRow;
            move.piece.x = move.postCol * Square.SQUARE_SIZE;
            move.piece.y = move.postRow * Square.SQUARE_SIZE;

            move.piece.isFirstMove = false;

            capture(move.capture);
            game.switchTurn();
        }

    }
    private void kingMove(Move move) {
        if(Math.abs(move.piece.col - move.postCol) == 2) {
            Piece rook;
            if(move.piece.col < move.postCol) {
                rook = getPiece(7,move.piece.row);
                rook.col = 5;
            } else {
                rook = getPiece(0,move.piece.row);
                rook.col = 3;
            }
            rook.x = rook.col * Square.SQUARE_SIZE;
        }
        move.piece.col = move.postCol;
        move.piece.row = move.postRow;
        move.piece.x = move.postCol * Square.SQUARE_SIZE;
        move.piece.y = move.postRow * Square.SQUARE_SIZE;

        move.piece.isFirstMove = false;

        capture(move.capture);
        game.switchTurn();
    }

    public void pawnMove(Move move) {
        int colorIndex = move.piece.isWhite() ? 1 : -1;

        if(getTileNum(move.postCol, move.postRow) == enPassantTarget) {
            move.capture = getPiece(move.postCol, move.postRow + colorIndex);
        }
        if(Math.abs(move.piece.row -move.postRow) == 2) {
            enPassantTarget = getTileNum(move.postCol, move.postRow + colorIndex);
        } else {
            enPassantTarget = -1;
        }

        if(selectedPiece.isWhite() && move.postRow == 0 || !selectedPiece.isWhite() && move.postRow == 7) {
            Move promotionMove = new Move(this, selectedPiece, move.postCol, move.postRow);
            game.displayPromotionPanel(promotionMove);
        } else {
            game.switchTurn();
        }
        move.piece.col = move.postCol;
        move.piece.row = move.postRow;
        move.piece.x = move.postCol * Square.SQUARE_SIZE;
        move.piece.y = move.postRow * Square.SQUARE_SIZE;

        move.piece.isFirstMove = false;

        capture(move.capture);


    }

    public void promotePawn(Move move, String PieceType) {
        pieces.remove(move.piece);
        if(PieceType.equals("Queen")) {
            pieces.add(new Queen(this, move.piece.isWhite(), move.postCol, move.postRow));
        } else if (PieceType.equals("Rook")) {
            pieces.add(new Rook(this, move.piece.isWhite(), move.postCol, move.postRow));
        } else if (PieceType.equals("Bishop")) {
            pieces.add(new Bishop(this, move.piece.isWhite(), move.postCol, move.postRow));
        } else if (PieceType.equals("Knight")) {
            pieces.add(new Knight(this, move.piece.isWhite(), move.postCol, move.postRow));
        } else {
            pieces.add(new Queen(this, move.piece.isWhite(), move.postCol, move.postRow));
        }

    }

    public void capture(Piece piece) {
        pieces.remove(piece);
    }

    public  boolean isValidMove(Move move) {
        if((!move.piece.isWhite() && game.currentPlayer == game.playerWhite) || (move.piece.isWhite() && game.currentPlayer == game.playerBlack))
            return false;
        if(sameTeam(move.piece, move.capture))
            return false;
        if (move.postCol < 0 || move.postCol >7 || move.postRow < 0 || move.postRow >7)
            return  false;
        if (!move.piece.canMove(move.postCol, move.postRow))
            return false;
        if (move.piece.moveCollision(move.postCol, move.postRow))
            return false;
        if(game.promotionPanel.isVisible())
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
