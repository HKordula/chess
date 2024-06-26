package game;

import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Board {
    final int COLUMNS = 8;
    final int ROWS = 8;
    private final Square[][] squares = new Square[ROWS][COLUMNS];
    public final int startX;
    public final int startY;
    public static final ArrayList<Piece> pieces = new ArrayList<>();
    Piece selectedPiece;
    public int enPassantTarget = -1;
    final Game game;
    public final Check check = new Check(this);
    boolean isGameOver = false;
    String message;
    private JLabel messageLabel;
    private JButton button1,button2;

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

    private void updateGameState() {
        Piece king = findKing(game.currentPlayer.isWhite());
        if(check.isGameOver(king)) {
            if(check.isKingChecked(new Move(this, king, king.col, king.row))) {
                message =game.currentPlayer.isWhite() ? "Black wins by Checkmate!" : "White wins by Checkmate!";
                if (game.currentPlayer.isWhite()) {
                    game.playerWhite.losses++;
                    game.playerBlack.wins++;
                } else {
                    game.playerBlack.losses++;
                    game.playerWhite.wins++;
                }
            } else {
                message ="Draw by Stalemate!";
                game.playerWhite.draws++;
                game.playerBlack.draws++;
            }
            isGameOver = true;
        } else if (insufficientMaterial(true) && insufficientMaterial(false)) {
            message="Draw by insufficient material!";
            game.playerWhite.draws++;
            game.playerBlack.draws++;
            isGameOver = true;
        }
        if(isGameOver) {
            displayGameOverPanel();
        }
    }

    public void displayGameOverPanel() {
        messageLabel = new JLabel(message);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 30));
        messageLabel.setBounds(startX + COLUMNS * Square.SQUARE_SIZE + (Square.SQUARE_SIZE / 2), startY + 300, 550, 100);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setVisible(true);

        button1 = new JButton("New Game");
        button1.setBounds(startX + COLUMNS * Square.SQUARE_SIZE + (Square.SQUARE_SIZE / 2) + 103, startY + 410, 120, 80);
        button1.setFocusPainted(false);
        button1.setForeground(Color.WHITE);
        button1.setBackground(Color.DARK_GRAY);
        button1.setFont(new Font("Garamond", Font.BOLD, 20));
        button1.setBorder(null);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.resetGame();
                button1.setVisible(false);
                button2.setVisible(false);
                messageLabel.setVisible(false);
            }
        });

        button2 = new JButton("Rematch");
        button2.setBounds(startX + COLUMNS * Square.SQUARE_SIZE + (Square.SQUARE_SIZE / 2) + 326, startY + 410, 120, 80);
        button2.setFocusPainted(false);
        button2.setForeground(Color.WHITE);
        button2.setBackground(Color.DARK_GRAY);
        button2.setFont(new Font("Garamond", Font.BOLD, 20));
        button2.setBorder(null);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button1.setVisible(false);
                button2.setVisible(false);
                messageLabel.setVisible(false);

                isGameOver = false;
                game.cancelDrawRequest();
                pieces.clear();
                setPieces();
                enPassantTarget = -1;
                game.currentPlayer = game.playerWhite;

                game.revalidate();
                game.repaint();
            }
        });

        game.setLayout(null);

        game.add(messageLabel);
        game.add(button1);
        game.add(button2);

        game.playerPanel[0].updateBalanceDisplay();
        game.playerPanel[1].updateBalanceDisplay();

        game.revalidate();
        game.repaint();
    }

    private boolean insufficientMaterial(boolean isWhite) {
        int count = 0;
        boolean hasBishopOrKnight = false;
        for (Piece piece : pieces) {
            if (piece.isWhite() == isWhite) {
                count++;
                if (piece instanceof Bishop || piece instanceof Knight) {
                    hasBishopOrKnight = true;
                }
            }
        }
        return count == 1 || (count == 2 && hasBishopOrKnight);
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
        updateGameState();
        game.cancelDrawRequest();
    }

    private void kingMove(Move move) {
        if(Math.abs(move.piece.col - move.postCol) >= 2) {
            Piece rook, king;
            if(move.piece.col < move.postCol) {
                rook = getPiece(7,move.piece.row);
                king = getPiece(move.piece.col, move.piece.row);
                rook.col = 5;
                king.col = 6;
            } else {
                rook = getPiece(0,move.piece.row);
                king = getPiece(move.piece.col, move.piece.row);
                rook.col = 3;
                king.col = 2;
            }
            rook.x = rook.col * Square.SQUARE_SIZE;
            king.x = king.col * Square.SQUARE_SIZE;
            king.y = king.row * Square.SQUARE_SIZE;
        } else {
            move.piece.col = move.postCol;
            move.piece.row = move.postRow;
            move.piece.x = move.postCol * Square.SQUARE_SIZE;
            move.piece.y = move.postRow * Square.SQUARE_SIZE;
            capture(move.capture);
        }
        move.piece.isFirstMove = false;
        game.switchTurn();
    }

    public void pawnMove(Move move) {
        int direction = move.piece.isWhite() ? 1 : -1;

        if(getTileNum(move.postCol, move.postRow) == enPassantTarget) {
            move.capture = getPiece(move.postCol, move.postRow + direction);
        }
        if(Math.abs(move.piece.row -move.postRow) == 2) {
            enPassantTarget = getTileNum(move.postCol, move.postRow + direction);
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

    public boolean isValidMove(Move move) {
        if((!move.piece.isWhite() && game.currentPlayer == game.playerWhite) || (move.piece.isWhite() && game.currentPlayer == game.playerBlack))
            return false;
        if(sameTeam(move.piece, move.capture) && !isCastlingMove(move.piece, move.capture))
            return false;
        if (move.postCol < 0 || move.postCol >7 || move.postRow < 0 || move.postRow >7)
            return  false;
        if (!move.piece.canMove(move.postCol, move.postRow))
            return false;
        if (move.piece.moveCollision(move.postCol, move.postRow))
            return false;
        if(check.isKingChecked(move))
            return false;
        if(isGameOver)
            return false;
        return true;
    }

    public boolean sameTeam(Piece p1, Piece p2) {
        if (p1 == null || p2 == null) {
            return false;
        }
        return p1.isWhite() == p2.isWhite();
    }

    public boolean isCastlingMove(Piece p1, Piece p2) {
        return (p1 instanceof King && p2 instanceof Rook || p1 instanceof Rook && p2 instanceof King) && p1.isFirstMove && p2.isFirstMove;
    }

    public Piece findKing(boolean isWhite) {
        for(Piece piece : pieces) {
            if(isWhite == piece.isWhite() && piece instanceof King) {
                return piece;
            }
        }
        return null;
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
                Piece whiteKing = findKing(true);
                Piece blackKing = findKing(false);
                if ((whiteKing != null && whiteKing.col == j && whiteKing.row == i && check.isKingChecked(new Move(this, whiteKing, j, i))) ||
                        (blackKing != null && blackKing.col == j && blackKing.row == i && check.isKingChecked(new Move(this, blackKing, j, i)))) {
                    squares[i][j] = new Square(x, y, Color.RED);
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