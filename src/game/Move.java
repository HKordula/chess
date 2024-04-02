package game;

import pieces.Piece;

public class Move {
    int preCol, preRow;
    int postCol,postRow;
    Piece piece;
    Piece capture;

    public Move(Board board, Piece piece, int postCol, int postRow) {
        this.preCol = piece.col;
        this.preRow = piece.row;
        this.postCol = postCol;
        this.postRow = postRow;

        this.piece = piece;
        this.capture = board.getPiece(postCol,postRow);
    }
}
