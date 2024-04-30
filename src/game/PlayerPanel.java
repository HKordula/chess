package game;

import javax.swing.*;
import java.awt.*;

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

    public PlayerPanel(Player player) {
        this.player = player;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Font font = new Font("Arial", Font.BOLD, 30);

        playerImage = new ImageIcon(getClass().getResource(player.getColor().equals("White") ? "/images/Light/Pawn.png" : "/images/Dark/Pawn.png"));
        imageLabel = new JLabel(playerImage);
        playerName = new JLabel( player.getName());
        playerName.setFont(font);
        gameBalance = new JLabel( player.getBalance());
        gameBalance.setFont(font);
        timeLabel = new JLabel("10:00");
        timeLabel.setFont(font);

        JPanel infoPanel = new JPanel(new FlowLayout());
        infoPanel.add(imageLabel);
        infoPanel.add(playerName);
        infoPanel.add(gameBalance);
        infoPanel.add(timeLabel);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        button1 = new JButton("Button 1");
        button2 = new JButton("Button 2");
        button3 = new JButton("Button 3");
        button4 = new JButton("Button 4");

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);

        add(infoPanel);
        add(buttonPanel);
    }
}