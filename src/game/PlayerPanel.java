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

    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

    public PlayerPanel(Player player, ConfigurationPanel configurationPanel) {
        this.player = player;
        this.configurationPanel = configurationPanel;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Font font = new Font("Arial", Font.BOLD, 30);

        playerImage = new ImageIcon(getClass().getResource(player.getColor().equals("White") ? "/images/Light/Pawn.png" : "/images/Dark/Pawn.png"));
        imageLabel = new JLabel(playerImage);
        playerName = new JLabel( player.getName());
        playerName.setFont(font);
        gameBalance = new JLabel( player.getBalance());
        gameBalance.setFont(font);
        timeLabel = new JLabel();
        timeLabel.setFont(font);

        int hours = (Integer) configurationPanel.hoursSpinner.getValue();
        int minutes = (Integer) configurationPanel.minutesSpinner.getValue();
        long timeRemaining = (hours * 60 + minutes) * 60 * 1000;
        CountdownTimer countdownTimer = new CountdownTimer(timeRemaining);
        countdownTimer.start();

        ActionListener timerActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        };

        Timer timer = new Timer(1000, timerActionListener);
        timer.start();

        timerActionListener.actionPerformed(null);

        JPanel infoPanel = new JPanel(new FlowLayout());
        infoPanel.add(imageLabel);
        infoPanel.add(playerName);
        infoPanel.add(gameBalance);
        infoPanel.add(timeLabel);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        button1 = new JButton("Surrender");
        button2 = new JButton("Draw");
        button3 = new JButton("New Game");
        button4 = new JButton("Rematch");

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);

        add(infoPanel);
        add(buttonPanel);
    }
}