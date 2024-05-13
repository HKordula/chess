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
    private JLabel logoLabel;
    private JLabel titleLabel;


    public Game() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.darkGray);
        setLayout(null);
        board = new Board(50, 50, this);
        mouse = new Mouse(board, this);

        createLogoPanel();

        JButton playButton = createPlayButton();
        add(playButton);

        promotionPanel = new PromotionPanel(board, this);
        promotionPanel.setVisible(false);
        add(promotionPanel);

        configurationPanel = new ConfigurationPanel();
        configurationPanel.setBounds(board.startX + board.COLUMNS * Square.SQUARE_SIZE + (Square.SQUARE_SIZE / 2), board.startY + 300, 550, 180);
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

    private void createLogoPanel() {
        ImageIcon logoIcon = new ImageIcon(Main.class.getResource("/images/logo.png"));
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
                playerWhite.wins++;
                System.out.println(playerWhite.name+ " " + playerWhite.color);

                String playerNameBlack = configurationPanel.getPlayerName("Player2");
                String playerColorBlack = configurationPanel.getPlayerColor("Player2");
                playerBlack = new Player(playerNameBlack, playerColorBlack);
                System.out.println(playerBlack.name+ " " + playerBlack.color);

                playerPanel = new PlayerPanel(playerWhite, configurationPanel, true);
                playerPanel.setBackground(new Color(75,115,153));
                playerPanel.setVisible(true);

                playerPanel2 = new PlayerPanel(playerBlack, configurationPanel, false);
                playerPanel2.setBackground(new Color(75,115,153));
                playerPanel2.setVisible(true);

                if (playerColorWhite.equals("White")) {
                    playerPanel.setBounds(board.startX + board.COLUMNS * Square.SQUARE_SIZE + (Square.SQUARE_SIZE / 2), board.startY + 600, 550, 200);
                    playerPanel2.setBounds(board.startX + board.COLUMNS * Square.SQUARE_SIZE + (Square.SQUARE_SIZE / 2), board.startY , 550, 200);
                } else {
                    playerPanel.setBounds(board.startX + board.COLUMNS * Square.SQUARE_SIZE + (Square.SQUARE_SIZE / 2), board.startY , 550, 200);
                    playerPanel2.setBounds(board.startX + board.COLUMNS * Square.SQUARE_SIZE + (Square.SQUARE_SIZE / 2), board.startY + 600, 550, 200);
                }

                add(playerPanel);
                add(playerPanel2);


                revalidate();
                repaint();

                currentPlayer = playerWhite;
                configurationPanel.setVisible(false);
                logoLabel.setVisible(false);
                titleLabel.setVisible(false);
                playButton.setVisible(false);
            }

        });
        playButton.setBounds(board.startX + board.COLUMNS * Square.SQUARE_SIZE + (Square.SQUARE_SIZE / 2) + 75, board.startY + 650, 400, 100);
        add(playButton);
        return playButton;
    }

    public void switchTurn() {
        if(currentPlayer == playerWhite) {
            playerWhite.stopTimer();
            currentPlayer = playerBlack;
        } else {
            currentPlayer = playerWhite;
            playerBlack.stopTimer();
        }
        currentPlayer.startTimer();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        board.draw(g2d);

        g2d.setColor(new Color(235,232,210));
        g2d.fillRect(board.startX + board.COLUMNS * Square.SQUARE_SIZE + (Square.SQUARE_SIZE / 2), board.startY, 550, 800);
    }
}