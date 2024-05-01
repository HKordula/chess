package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel {
    public static final int WIDTH = 1500;
    public static final int HEIGHT = 900;
    Board board;
    Mouse mouse;
    PromotionPanel promotionPanel;
    ConfigurationPanel configurationPanel;
    PlayerPanel playerPanel;
    PlayerPanel playerPanel2;
    Player playerWhite;
    Player playerBlack;
    Player currentPlayer;

    public Game() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.darkGray);
        setLayout(null);
        board = new Board(50, 50, this);
        mouse = new Mouse(board, this);

        JButton playButton = createPlayButton();
        add(playButton);

        promotionPanel = new PromotionPanel(board, this);
        promotionPanel.setVisible(false);
        add(promotionPanel);

        configurationPanel = new ConfigurationPanel();
        configurationPanel.setBounds(board.startX + board.COLUMNS * Square.SQUARE_SIZE + (Square.SQUARE_SIZE / 2), board.startY, 550, 180);
        configurationPanel.setVisible(true);
        add(configurationPanel);

        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
    }

    public void displayPromotionPanel(Move move) {
        String color = move.piece.isWhite() ? "Light" : "Dark";
        promotionPanel.setMove(move, color);
        promotionPanel.setVisible(true);
    }

    private JButton createPlayButton() {
        JButton playButton = new JButton("Play");
        playButton.setFocusPainted(false);
        playButton.setForeground(Color.WHITE);
        playButton.setBackground(Color.DARK_GRAY);
        playButton.setFont(new Font("Garamond", Font.BOLD, 60));
        playButton.setBorder(null);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerNameWhite = configurationPanel.getPlayerName("Player1");
                String playerColorWhite = configurationPanel.getPlayerColor("Player1");
                playerWhite = new Player(playerNameWhite, playerColorWhite);
                System.out.println(playerWhite.name+ " " + playerWhite.color);

                String playerNameBlack = configurationPanel.getPlayerName("Player2");
                String playerColorBlack = configurationPanel.getPlayerColor("Player2");
                playerBlack = new Player(playerNameBlack, playerColorBlack);
                System.out.println(playerBlack.name+ " " + playerBlack.color);

                playerPanel = new PlayerPanel(playerWhite, configurationPanel, true);
                playerPanel.setBounds(board.startX + board.COLUMNS * Square.SQUARE_SIZE + (Square.SQUARE_SIZE / 2), board.startY + 600, 550, 200);
                playerPanel.setVisible(true);
                add(playerPanel);
                playerPanel2 = new PlayerPanel(playerBlack, configurationPanel, false);
                playerPanel2.setBounds(board.startX + board.COLUMNS * Square.SQUARE_SIZE + (Square.SQUARE_SIZE / 2), board.startY , 550, 200);
                playerPanel2.setVisible(true);
                add(playerPanel2);

                currentPlayer = playerWhite;
                configurationPanel.setVisible(false);
                playButton.setVisible(false);
            }

        });
        playButton.setBounds(board.startX + board.COLUMNS * Square.SQUARE_SIZE + (Square.SQUARE_SIZE / 2) + 75, board.startY + 650, 400, 100);
        add(playButton);
        return playButton;
    }

    public void switchTurn() {
        if(currentPlayer == playerWhite) {
            playerPanel.stopTimer();
            currentPlayer = playerBlack;
            playerPanel2.startTimer();
        } else {
            playerPanel2.stopTimer();
            currentPlayer = playerWhite;
            playerPanel.startTimer();
        }
    }

    public void startGame() {
        if (currentPlayer == playerWhite) {
            playerPanel.startTimer();
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