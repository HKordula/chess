package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Objects;

public class PromotionPanel extends JPanel {
    private final Board board;
    private Move move;
    private final Game game;

    public PromotionPanel(Board board, Game game) {
        this.board = board;
        this.game = game;
        setLayout(new GridLayout(1, 4));
        setBackground(new Color(75,115,153));
        setBounds(board.startX + board.COLUMNS * Square.SQUARE_SIZE + (Square.SQUARE_SIZE / 2) + 75, board.startY + 350, Square.SQUARE_SIZE * 4 , Square.SQUARE_SIZE); // Set the position and size of the panel
    }

    private void addPieceImage(String imagePath, String pieceType) {
        try {
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath))));
            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (move != null) {
                        board.promotePawn(move, pieceType);
                        game.repaint();
                        setVisible(false);
                        game.switchTurn();
                    }
                }
            });
            add(imageLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMove(Move move, String color) {
        this.move = move;
        removeAll();
        addPieceImage("/images/" + color + "/Queen.png", "Queen");
        addPieceImage("/images/" + color + "/Rook.png", "Rook");
        addPieceImage("/images/" + color + "/Bishop.png", "Bishop");
        addPieceImage("/images/" + color + "/Knight.png", "Knight");
    }
}