package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class PlayerPanel extends JPanel {
    Player player;
    private CountdownTimer countdownTimer;
    private JLabel playerName;
    private JLabel gameBalance;
    private JLabel timeLabel;
    private JLabel imageLabel;
    private ImageIcon playerImage;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private ConfigurationPanel configurationPanel;

    public PlayerPanel(Player player, ConfigurationPanel configurationPanel, boolean startTimer) {
        this.player = player;
        this.configurationPanel = configurationPanel;

        Font font = new Font("Arial", Font.BOLD, 30);

        setLayout(null);

        playerImage = new ImageIcon(getClass().getResource(player.getColor().equals("White") ? "/images/Light/Pawn.png" : "/images/Dark/Pawn.png"));
        imageLabel = new JLabel(playerImage);
        imageLabel.setBounds(20, 0, 100, 100);

        playerName = new JLabel( player.getName());
        playerName.setFont(font);
        playerName.setForeground(new Color(235,232,210));
        playerName.setBounds(100, 0, 100, 100);

        gameBalance = new JLabel( player.getBalance());
        gameBalance.setFont(font);
        gameBalance.setForeground(new Color(235,232,210));
        gameBalance.setBounds(300, 0, 100, 100);

        timeLabel = new JLabel();
        timeLabel.setFont(font);
        timeLabel.setForeground(new Color(235,232,210));
        timeLabel.setBounds(400, 0, 100, 100);

        int hours = (Integer) configurationPanel.hoursSpinner.getValue();
        int minutes = (Integer) configurationPanel.minutesSpinner.getValue();
        long timeRemaining = (hours * 60 + minutes) * 60 * 1000;
        if (configurationPanel.timeCheckBox.isSelected()) {
            countdownTimer = new CountdownTimer(timeRemaining);
            if (startTimer) {
                countdownTimer.start();
            }
        }

        ActionListener timerActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (countdownTimer != null) {
                    long offset = TimeZone.getDefault().getRawOffset();
                    Date date = new Date(countdownTimer.getTimeRemaining() - offset);
                    SimpleDateFormat formatter;
                    if (countdownTimer.getTimeRemaining() >= 3600000) {
                        formatter = new SimpleDateFormat("HH:mm:ss");
                    } else {
                        formatter = new SimpleDateFormat("mm:ss");
                    }
                    String time = formatter.format(date);
                    timeLabel.setText(time);
                }
            }
        };

        Timer timer = new Timer(1000, timerActionListener);
        timer.start();

        timerActionListener.actionPerformed(null);

        add(imageLabel);
        add(playerName);
        add(gameBalance);
        add(timeLabel);

        button1 = new JButton("Surrender");
        button1.setBounds(20, 110, 100, 80);
        button1.setFocusPainted(false);
        button1.setForeground(Color.WHITE);
        button1.setBackground(Color.DARK_GRAY);
        button1.setFont(new Font("Garamond", Font.BOLD, 15));
        button1.setBorder(null);

        button2 = new JButton("Draw");
        button2.setBounds(140, 110, 100, 80);
        button2.setFocusPainted(false);
        button2.setForeground(Color.WHITE);
        button2.setBackground(Color.DARK_GRAY);
        button2.setFont(new Font("Garamond", Font.BOLD, 15));
        button2.setBorder(null);


        button3 = new JButton("New Game");
        button3.setBounds(260, 110, 100, 80);
        button3.setFocusPainted(false);
        button3.setForeground(Color.WHITE);
        button3.setBackground(Color.DARK_GRAY);
        button3.setFont(new Font("Garamond", Font.BOLD, 15));
        button3.setBorder(null);

        button4 = new JButton("Rematch");
        button4.setBounds(380, 110, 100, 80);
        button4.setFocusPainted(false);
        button4.setForeground(Color.WHITE);
        button4.setBackground(Color.DARK_GRAY);
        button4.setFont(new Font("Garamond", Font.BOLD, 15));
        button4.setBorder(null);

        add(button1);
        add(button2);
        add(button3);
        add(button4);

    }
    public void startTimer() {
        if (countdownTimer != null) {
            countdownTimer.start();
        }
    }

    public void stopTimer() {
        if (countdownTimer != null) {
            countdownTimer.stop();
        }
    }
}