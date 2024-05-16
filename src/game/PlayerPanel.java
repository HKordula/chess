package game;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerPanel extends JPanel {
    private final Player player;
    final Board board;
    private final JLabel gameBalance;
    public JButton[] button = new JButton[4];

    public PlayerPanel(Player player, Board board, Game game) {
        this.player = player;
        this.board = board;

        Font font = new Font("Arial", Font.BOLD, 30);

        setLayout(null);

        ImageIcon playerImage = new ImageIcon(getClass().getResource(player.getColor().equals("White") ? "/images/Light/Pawn.png" : "/images/Dark/Pawn.png"));
        JLabel imageLabel = new JLabel(playerImage);
        imageLabel.setBounds(30, 0, 100, 100);

        JLabel playerName = new JLabel(player.getName());
        playerName.setFont(font);
        playerName.setForeground(new Color(235, 232, 210));
        playerName.setBounds(120, 10, 180, 100);

        gameBalance = new JLabel(player.getBalance());
        gameBalance.setFont(font);
        gameBalance.setForeground(new Color(235, 232, 210));
        gameBalance.setBounds(430, 10, 100, 100);

        add(imageLabel);
        add(playerName);
        add(gameBalance);

        button[0] = new JButton("Surrender");
        button[0].setBounds(20, 110, 120, 80);

        button[1] = new JButton("Draw");
        button[1].setBounds(150, 110, 120, 80);

        button[2] = new JButton("YES");
        button[2].setBounds(280, 110, 120, 80);

        button[3] = new JButton("NO");
        button[3].setBounds(410, 110, 120, 80);

        for (JButton btn : button) {
            btn.setFocusPainted(false);
            btn.setForeground(Color.WHITE);
            btn.setBackground(Color.DARK_GRAY);
            btn.setFont(new Font("Garamond", Font.BOLD, 20));
            btn.setBorder(null);
        }

        add(button[0]);
        add(button[1]);
        add(button[2]);
        add(button[3]);

        button[0].addActionListener(new ActionListener() {
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
                    game.playerPanel[0].gameBalance.setText(game.playerWhite.getBalance());
                    game.playerPanel[1].gameBalance.setText(game.playerBlack.getBalance());

                    board.displayGameOverPanel();
                }
            }
        });

        button[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!board.isGameOver) {
                    game.drawRequested = true;
                    game.requestingPlayer = player;

                    if (player == game.playerWhite) {
                        game.playerPanel[1].button[2].setBorder(new LineBorder(Color.GREEN, 2));
                        game.playerPanel[1].button[3].setBorder(new LineBorder(Color.RED, 2));
                    } else if (player == game.playerBlack) {
                        game.playerPanel[0].button[2].setBorder(new LineBorder(Color.GREEN, 2));
                        game.playerPanel[0].button[3].setBorder(new LineBorder(Color.RED, 2));
                    }
                }
            }
        });

        button[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!board.isGameOver && game.drawRequested && game.requestingPlayer != player) {
                    board.isGameOver = true;
                    game.playerWhite.draws++;
                    game.playerBlack.draws++;
                    game.playerPanel[0].gameBalance.setText(game.playerWhite.getBalance());
                    game.playerPanel[1].gameBalance.setText(game.playerBlack.getBalance());
                    board.message = "Draw by decission!";
                    board.displayGameOverPanel();
                }
            }
        });

        button[3].addActionListener(new ActionListener() {
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