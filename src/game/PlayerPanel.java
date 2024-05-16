package game;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerPanel extends JPanel {
    Player player;
    Board board;
    private JLabel playerName;
    private JLabel gameBalance;
    private JLabel timeLabel;
    private JLabel imageLabel;
    private ImageIcon playerImage;
    public JButton button1;
    public JButton button2;
    public JButton button3;
    public JButton button4;

    public PlayerPanel(Player player, Board board, Game game, ConfigurationPanel configurationPanel) {
        this.player = player;
        this.board = board;


        Font font = new Font("Arial", Font.BOLD, 30);

        setLayout(null);

        playerImage = new ImageIcon(getClass().getResource(player.getColor().equals("White") ? "/images/Light/Pawn.png" : "/images/Dark/Pawn.png"));
        imageLabel = new JLabel(playerImage);
        imageLabel.setBounds(30, 0, 100, 100);

        playerName = new JLabel(player.getName());
        playerName.setFont(font);
        playerName.setForeground(new Color(235, 232, 210));
        playerName.setBounds(120, 10, 180, 100);

        gameBalance = new JLabel(player.getBalance());
        gameBalance.setFont(font);
        gameBalance.setForeground(new Color(235, 232, 210));
        gameBalance.setBounds(430, 10, 100, 100);

        timeLabel = new JLabel();
        timeLabel.setFont(font);
        timeLabel.setForeground(new Color(235, 232, 210));
        timeLabel.setBounds(430, 10, 100, 100);

        add(imageLabel);
        add(playerName);
        add(gameBalance);
        add(timeLabel);

        button1 = new JButton("Surrender");
        button1.setBounds(20, 110, 120, 80);
        button1.setFocusPainted(false);
        button1.setForeground(Color.WHITE);
        button1.setBackground(Color.DARK_GRAY);
        button1.setFont(new Font("Garamond", Font.BOLD, 20));
        button1.setBorder(null);

        button2 = new JButton("Draw");
        button2.setBounds(150, 110, 120, 80);
        button2.setFocusPainted(false);
        button2.setForeground(Color.WHITE);
        button2.setBackground(Color.DARK_GRAY);
        button2.setFont(new Font("Garamond", Font.BOLD, 20));
        button2.setBorder(null);

        button3 = new JButton("YES");
        button3.setBounds(280, 110, 120, 80);
        button3.setFocusPainted(false);
        button3.setForeground(Color.WHITE);
        button3.setBackground(Color.DARK_GRAY);
        button3.setFont(new Font("Garamond", Font.BOLD, 20));
        button3.setBorder(null);

        button4 = new JButton("NO");
        button4.setBounds(410, 110, 120, 80);
        button4.setFocusPainted(false);
        button4.setForeground(Color.WHITE);
        button4.setBackground(Color.DARK_GRAY);
        button4.setFont(new Font("Garamond", Font.BOLD, 20));
        button4.setBorder(null);

        add(button1);
        add(button2);
        add(button3);
        add(button4);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!board.isGameOver) {
                    board.isGameOver = true;

                    if (player.isWhite()) {
                        game.playerWhite.losses++;
                        game.playerBlack.wins++;
                        board.message = "Black wins by surrendering!";
                    } else {
                        game.playerBlack.losses++;
                        game.playerWhite.wins++;
                        board.message = "White wins by surrendering!";
                    }
                    game.playerPanel.gameBalance.setText(game.playerWhite.getBalance());
                    game.playerPanel2.gameBalance.setText(game.playerBlack.getBalance());

                    board.displayGameOverPanel();
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!board.isGameOver) {
                    game.drawRequested = true;
                    game.requestingPlayer = player;

                    if (player == game.playerWhite) {
                        game.playerPanel2.button3.setBorder(new LineBorder(Color.GREEN, 2));
                        game.playerPanel2.button4.setBorder(new LineBorder(Color.RED, 2));
                    } else if (player == game.playerBlack) {
                        game.playerPanel.button3.setBorder(new LineBorder(Color.GREEN, 2));
                        game.playerPanel.button4.setBorder(new LineBorder(Color.RED, 2));
                    }
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!board.isGameOver && game.drawRequested && game.requestingPlayer != player) {
                    board.isGameOver = true;
                    game.playerWhite.draws++;
                    game.playerBlack.draws++;
                    game.playerPanel.gameBalance.setText(game.playerWhite.getBalance());
                    game.playerPanel2.gameBalance.setText(game.playerBlack.getBalance());
                    board.message = "Draw by decission!";
                    board.displayGameOverPanel();
                }
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!board.isGameOver && game.drawRequested && game.requestingPlayer != player) {
                    game.cancelDrawRequest();
                }
            }
        });
    }

    public void updateBalanceDisplay() {
        gameBalance.setText(player.getBalance());
    }
}