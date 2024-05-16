package game;

import pieces.Piece;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {
    private final Game game;
    private final Board board;

    public Mouse(Board board, Game game) {
        this.board = board;
        this.game = game;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int col = (e.getX() - 50) / Square.SQUARE_SIZE;
        int row = (e.getY() - 50) / Square.SQUARE_SIZE;
        Piece PiecePos = board.getPiece(col,row);
        if(PiecePos != null) {
            board.selectedPiece = PiecePos;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(board.selectedPiece != null) {
            board.selectedPiece.x = e.getX() - Square.SQUARE_SIZE /2 -50;
            board.selectedPiece.y = e.getY() - Square.SQUARE_SIZE /2 -50;
            game.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int col = (e.getX() - 50) / Square.SQUARE_SIZE;
        int row = (e.getY() - 50) / Square.SQUARE_SIZE;
        if(board.selectedPiece != null) {
            Move move = new Move(board, board.selectedPiece, col, row);
            if ( board.isValidMove(move)) {
                board.makeMove(move);
            } else {
                board.selectedPiece.x = board.selectedPiece.col * Square.SQUARE_SIZE;
                board.selectedPiece.y = board.selectedPiece.row * Square.SQUARE_SIZE;
            }
        }
        board.selectedPiece = null;
        game.repaint();
    }
}