package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Game extends JPanel {
    private static final int WIDTH = 1500;
    private static final int HEIGHT = 900;
    private final Board board;
    private final PromotionPanel promotionPanel;
    private final ConfigurationPanel configurationPanel;
    PlayerPanel[] playerPanel = new PlayerPanel[2];
    Player playerWhite, playerBlack, currentPlayer;
    Player requestingPlayer = null;
    private JLabel logoLabel, titleLabel;
    private JButton playButton;
    boolean drawRequested = false;

    public Game() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.darkGray);
        setLayout(null);
        board = new Board(50, 50, this);
        Mouse mouse = new Mouse(board, this);

        createLogoPanel();
        createPlayButton();

        promotionPanel = new PromotionPanel(board, this);
        promotionPanel.setVisible(false);
        add(promotionPanel);

        configurationPanel = new ConfigurationPanel();
        configurationPanel.setBounds(board.startX + board.COLUMNS * Square.SQUARE_SIZE + (Square.SQUARE_SIZE / 2), board.startY + 333, 550, 180);
        add(configurationPanel);

        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
    }

    public void displayPromotionPanel(Move move) {
        String color = move.piece.isWhite() ? "Light" : "Dark";
        promotionPanel.setMove(move, color);
        promotionPanel.setVisible(true);
    }

    private void createLogoPanel() {
        ImageIcon logoIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/images/logo.png")));
        Image logoImage = logoIcon.getImage();
        Image resizedLogoImage = logoImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(resizedLogoImage);

        logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(board.startX + board.COLUMNS * Square.SQUARE_SIZE + (Square.SQUARE_SIZE / 2) + 125, 100, 100, 100);

        titleLabel = new JLabel("Chess");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 60));
        titleLabel.setBounds(board.startX + board.COLUMNS * Square.SQUARE_SIZE + (Square.SQUARE_SIZE / 2) + 225, 105, 400, 100);

        add(logoLabel);
        add(titleLabel);
    }

    private void createPlayButton() {
        playButton = new JButton("Play");
        playButton.setFocusPainted(false);
        playButton.setForeground(Color.WHITE);
        playButton.setBackground(Color.DARK_GRAY);
        playButton.setFont(new Font("Garamond", Font.BOLD, 60));
        playButton.setBorder(null);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String player1Color = configurationPanel.getPlayerColor("Player1");
                String player1Name = configurationPanel.getPlayerName("Player1");
                String player2Name = configurationPanel.getPlayerName("Player2");
                if(player1Color.equals("White")) {
                    playerWhite = new Player(player1Name, "White");
                    playerBlack = new Player(player2Name, "Black");
                } else {
                    playerWhite = new Player(player2Name, "White");
                    playerBlack = new Player(player1Name, "Black");
                }

                playerPanel[0] = new PlayerPanel(playerWhite, board, board.game);
                playerPanel[0].setBackground(new Color(75,115,153));
                playerPanel[0].setVisible(true);

                playerPanel[1] = new PlayerPanel(playerBlack, board, board.game);
                playerPanel[1].setBackground(new Color(75,115,153));
                playerPanel[1].setVisible(true);

                int playerPanelPosition = board.startX + board.COLUMNS * Square.SQUARE_SIZE + (Square.SQUARE_SIZE / 2);

                if (player1Color.equals("White")) {
                    playerPanel[0].setBounds(playerPanelPosition, board.startY + 600, 550, 200);
                    playerPanel[1].setBounds(playerPanelPosition, board.startY , 550, 200);
                } else {
                    playerPanel[0].setBounds(playerPanelPosition, board.startY + 600, 550, 200);
                    playerPanel[1].setBounds(playerPanelPosition, board.startY, 550, 200);
                }

                add(playerPanel[0]);
                add(playerPanel[1]);

                revalidate();
                repaint();

                board.isGameOver = false;
                currentPlayer = playerWhite;
                configurationPanel.setVisible(false);
                logoLabel.setVisible(false);
                titleLabel.setVisible(false);
                playButton.setVisible(false);
            }

        });
        playButton.setBounds(board.startX + board.COLUMNS * Square.SQUARE_SIZE + (Square.SQUARE_SIZE / 2) + 75, board.startY + 650, 400, 100);
        add(playButton);
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

        g2d.setColor(new Color(235,232,210));
        g2d.fillRect(board.startX + board.COLUMNS * Square.SQUARE_SIZE + (Square.SQUARE_SIZE / 2), board.startY, 550, 800);
    }

    public void cancelDrawRequest() {
        drawRequested = false;
        requestingPlayer = null;
        playerPanel[0].button[2].setBorder(null);
        playerPanel[0].button[3].setBorder(null);
        playerPanel[1].button[2].setBorder(null);
        playerPanel[1].button[3].setBorder(null);
    }

    public void resetGame() {
        Board.pieces.clear();
        board.setPieces();
        board.enPassantTarget = -1;

        playerWhite = new Player(configurationPanel.getPlayerName("Player1"), "White");
        playerBlack = new Player(configurationPanel.getPlayerName("Player2"), "Black");
        currentPlayer = playerWhite;

        this.remove(playerPanel[0]);
        this.remove(playerPanel[1]);
        playerPanel[0] = new PlayerPanel(playerWhite, board, this);
        playerPanel[1] = new PlayerPanel(playerBlack, board, this);
        this.add(playerPanel[0]);
        this.add(playerPanel[1]);

        configurationPanel.setVisible(true);
        logoLabel.setVisible(true);
        titleLabel.setVisible(true);

        cancelDrawRequest();

        playButton.setVisible(true);

        revalidate();
        repaint();
    }
}