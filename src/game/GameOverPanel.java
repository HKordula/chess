package game;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel {
    JButton button1, button2;
    public GameOverPanel() {
        button1 = new JButton("New Game");
        button1.setBounds(20, 110, 120, 80);
        button1.setFocusPainted(false);
        button1.setForeground(Color.WHITE);
        button1.setBackground(Color.DARK_GRAY);
        button1.setFont(new Font("Garamond", Font.BOLD, 20));
        button1.setBorder(null);

        button2 = new JButton("Rematch");
        button2.setBounds(150, 110, 120, 80);
        button2.setFocusPainted(false);
        button2.setForeground(Color.WHITE);
        button2.setBackground(Color.DARK_GRAY);
        button2.setFont(new Font("Garamond", Font.BOLD, 20));
        button2.setBorder(null);
    }




}
