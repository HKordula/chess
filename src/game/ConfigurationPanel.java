package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ConfigurationPanel extends JPanel {
    private final JTextField[] playerName = new JTextField[2];
    private final JComboBox<String>[] playerColor = new JComboBox[2];

    public ConfigurationPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        setBackground(new Color(235,232,210));

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Font font = new Font("Arial", Font.BOLD, 18);

        JPanel player1Panel = new JPanel(new GridLayout(1, 2,15,40));
        player1Panel.setBackground(new Color(235,232,210));

        JLabel label1 = new JLabel("Name:");
        label1.setFont(font);
        player1Panel.add(label1);

        playerName[0] = new JTextField();
        playerName[0].setPreferredSize(new Dimension(120,40));
        playerName[0].setFont(font);
        player1Panel.add(playerName[0]);

        JLabel label2 = new JLabel("Color:");
        label2.setFont(font);
        player1Panel.add(label2);

        playerColor[0] = new JComboBox<>(new String[]{"White", "Black", "Random"});
        playerColor[0].setFont(font);
        playerColor[0].setBackground(Color.white);
        player1Panel.add(playerColor[0]);
        add(player1Panel);

        JPanel player2Panel = new JPanel(new GridLayout(1, 2,15,40));
        player2Panel.setBackground(new Color(235,232,210));

        JLabel label3 = new JLabel("Name:");
        label3.setFont(font);
        player2Panel.add(label3);

        playerName[1] = new JTextField();
        playerName[1].setPreferredSize(new Dimension(120,40));
        playerName[1].setFont(font);
        player2Panel.add(playerName[1]);

        JLabel label4 = new JLabel("Color:");
        label4.setFont(font);
        player2Panel.add(label4);

        playerColor[1] = new JComboBox<>(new String[]{"White", "Black", "Random"});
        playerColor[1].setSelectedItem("Black");
        playerColor[1].setFont(font);
        playerColor[1].setBackground(Color.white);
        player2Panel.add(playerColor[1]);
        add(player2Panel);

        playerColor[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedColor = (String) playerColor[0].getSelectedItem();
                switch (selectedColor) {
                    case "White":
                        playerColor[1].setSelectedItem("Black");
                        break;
                    case "Black":
                        playerColor[1].setSelectedItem("White");
                        break;
                    case "Random":
                        playerColor[1].setSelectedItem("Random");
                        break;
                }
            }
        });

        playerColor[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedColor = (String) playerColor[1].getSelectedItem();
                switch (selectedColor) {
                    case "White":
                        playerColor[0].setSelectedItem("Black");
                        break;
                    case "Black":
                        playerColor[0].setSelectedItem("White");
                        break;
                    case "Random":
                        playerColor[0].setSelectedItem("Random");
                        break;
                }
            }
        });
    }

    public String getPlayerName(String player) {
        String playerName;
        if(player.equals("Player1")) {
            playerName = this.playerName[0].getText();
            if(playerName.isEmpty()) {
                playerName = "Player 1";
            }
        } else {
            playerName = this.playerName[1].getText();
            if(playerName.isEmpty()) {
                playerName = "Player 2";
            }
        }
        return playerName;
    }

    public String getPlayerColor(String player) {
        String selectedColor;
        if(player.equals("Player1")) {
            selectedColor = (String) playerColor[0].getSelectedItem();
            if(selectedColor.equals("Random")) {
                selectedColor = new Random().nextBoolean() ? "White" : "Black";
                playerColor[1].setSelectedItem(selectedColor.equals("White") ? "Black" : "White");
            }
        } else {
            selectedColor = (String) playerColor[1].getSelectedItem();
            if(selectedColor.equals("Random")) {
                selectedColor = playerColor[0].getSelectedItem().equals("White") ? "Black" : "White";
            }
        }
        return selectedColor;
    }
}