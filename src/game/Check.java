package game;

import pieces.*;

public class Check {
    private final Board board;

    public Check(Board board) {
        this.board = board;
    }

    public boolean isKingChecked(Move move) {
        Piece king = board.findKing(move.piece.isWhite());
        assert king != null;

        int kingCol = king.col;
        int kingRow = king.row;

        if(board.selectedPiece != null && board.selectedPiece instanceof King) {
            kingCol = move.postCol;
            kingRow = move.postRow;
        }

        return  rookChecked(move.postCol, move.postRow, king, kingCol, kingRow,0 ,1) ||
                rookChecked(move.postCol, move.postRow, king, kingCol, kingRow,1 ,0) ||
                rookChecked(move.postCol, move.postRow, king, kingCol, kingRow,0,-1) ||
                rookChecked(move.postCol, move.postRow, king, kingCol, kingRow,-1,0) ||

                bishopChecked(move.postCol, move.postRow, king, kingCol, kingRow,-1,-1) ||
                bishopChecked(move.postCol, move.postRow, king, kingCol, kingRow,1,-1) ||
                bishopChecked(move.postCol, move.postRow, king, kingCol, kingRow,1,1) ||
                bishopChecked(move.postCol, move.postRow, king, kingCol, kingRow,-1,1) ||

                knightChecked(move.postCol, move.postRow, king, kingCol, kingRow )||
                pawnChecked (move.postCol, move.postRow, king, kingCol, kingRow) ||
                kingChecked(king, kingCol, kingRow);
    }

    private boolean rookChecked(int col, int row, Piece king, int kingCol, int kingRow, int colVal, int rowVal) {
        for ( int i = 1 ; i < 8 ; i++) {
            if(kingCol + (i * colVal) == col && kingRow + (i * rowVal) == row) {
                break;
            }
            Piece piece = board.getPiece(kingCol + (i * colVal) ,kingRow + (i * rowVal));
            if(piece != null && piece != board.selectedPiece) {
                if(!board.sameTeam(piece, king) && (piece instanceof Rook || piece instanceof Queen)) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    private boolean bishopChecked(int col, int row, Piece king, int kingCol, int kingRow, int colVal, int rowVal) {
        for ( int i = 1 ; i < 8 ; i++) {
            if(kingCol - (i * colVal) == col && kingRow - (i * rowVal) == row) {
                break;
            }
            Piece piece = board.getPiece(kingCol - (i * colVal) ,kingRow - (i * rowVal));
            if(piece != null && piece != board.selectedPiece) {
                if(!board.sameTeam(piece, king) && (piece instanceof Bishop || piece instanceof Queen)) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    private boolean knightChecked(int col, int row, Piece king, int kingCol, int kingRow) {
        return  checkKnight(board.getPiece(kingCol - 1 ,kingRow - 2), king, col, row) ||
                checkKnight(board.getPiece(kingCol + 1 ,kingRow - 2), king, col, row) ||
                checkKnight(board.getPiece(kingCol + 2 ,kingRow - 1), king, col, row) ||
                checkKnight(board.getPiece(kingCol + 2 ,kingRow + 1), king, col, row) ||
                checkKnight(board.getPiece(kingCol + 1 ,kingRow + 2), king, col, row) ||
                checkKnight(board.getPiece(kingCol - 1 ,kingRow + 2), king, col, row) ||
                checkKnight(board.getPiece(kingCol - 2 ,kingRow + 1), king, col, row) ||
                checkKnight(board.getPiece(kingCol - 2 ,kingRow - 1), king, col, row);
    }

    private boolean checkKnight(Piece p, Piece k, int col, int row) {
        return p != null && !board.sameTeam(p, k) && p instanceof Knight && !(p.col == col && p.row == row);
    }

    private boolean kingChecked(Piece king, int kingCol, int kingRow) {
        return  checkKing(board.getPiece(kingCol - 1, kingRow - 1), king) ||
                checkKing(board.getPiece(kingCol + 1, kingRow - 1), king) ||
                checkKing(board.getPiece(kingCol, kingRow - 1), king) ||
                checkKing(board.getPiece(kingCol - 1, kingRow), king) ||
                checkKing(board.getPiece(kingCol + 1, kingRow), king) ||
                checkKing(board.getPiece(kingCol - 1, kingRow + 1), king) ||
                checkKing(board.getPiece(kingCol + 1, kingRow + 1), king) ||
                checkKing(board.getPiece(kingCol, kingRow + 1), king);
    }

    private boolean checkKing(Piece p, Piece k) {
        return p != null && !board.sameTeam(p, k) && p instanceof King ;
    }

    private boolean pawnChecked(int col, int row, Piece king, int kingCol, int kingRow) {
        int colorVal = king.isWhite() ? -1 : 1;
        return  checkPawn(board.getPiece(kingCol + 1, kingRow + colorVal), king,  col, row) ||
                checkPawn(board.getPiece(kingCol - 1, kingRow + colorVal), king,  col, row);
    }

    private boolean checkPawn(Piece p, Piece k, int col, int row) {
        return p != null && !board.sameTeam(p, k) && p instanceof Pawn && !(p.col == col && p.row == row);
    }

    public boolean isGameOver(Piece king) {
        for(Piece piece : Board.pieces) {
            if(board.sameTeam(piece, king)) {
                board.selectedPiece = piece == king ? king : null;
                for(int row = 0; row < board.ROWS ; row++) {
                    for(int col = 0; col < board.ROWS ; col++) {
                        Move move = new Move(board, piece, col, row);
                        if(board.isValidMove(move)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}