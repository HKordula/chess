package game;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {
    public static final int WIDTH = 1500;
    public static final int HEIGHT = 900;
    Board board;
    Mouse mouse;
    PromotionPanel promotionPanel;
    Player playerWhite;
    Player playerBlack;
    Player currentPlayer;

    public Game() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.darkGray);
        setLayout(null);
        board = new Board(50, 50, this);
        mouse = new Mouse(board, this);

        playerWhite = new Player("Hubert","White");
        playerBlack = new Player("Player2","Black");
        currentPlayer = playerWhite;

        promotionPanel = new PromotionPanel(board, this);
        promotionPanel.setVisible(false);
        add(promotionPanel);

        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
    }

    public void displayPromotionPanel(Move move) {
        String color = move.piece.isWhite() ? "Light" : "Dark";
        promotionPanel.setMove(move, color);
        promotionPanel.setVisible(true);
    }

    public void switchTurn() {
        if(currentPlayer == playerWhite) {
            currentPlayer = playerBlack;
        } else {
            currentPlayer = playerWhite;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        board.draw(g2d);

        g2d.setColor(Color.WHITE);
        g2d.fillRect(board.startX + board.COLUMNS * Square.SQUARE_SIZE + (Square.SQUARE_SIZE / 2), board.startY, 550, 800);

        g2d.setColor(Color.GREEN);
        g2d.fillRect(board.startX + board.COLUMNS * Square.SQUARE_SIZE + (Square.SQUARE_SIZE / 2) + 75, board.startY + 350, 400, 100);

    }
}