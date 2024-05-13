package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;

public class ConfigurationPanel extends JPanel {
    private JTextField player1Name;
    private JComboBox<String> player1Color;
    private JTextField player2Name;
    private JComboBox<String> player2Color;
    JCheckBox timeCheckBox;
    JSpinner hoursSpinner;
    JSpinner minutesSpinner;

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

        player1Name = new JTextField();
        player1Name.setPreferredSize(new Dimension(120,40));
        player1Name.setFont(font);
        player1Panel.add(player1Name);

        JLabel label2 = new JLabel("Color:");
        label2.setFont(font);
        player1Panel.add(label2);

        player1Color = new JComboBox<>(new String[]{"White", "Black", "Random"});
        player1Color.setFont(font);
        player1Color.setBackground(Color.white);
        player1Panel.add(player1Color);
        add(player1Panel);

        JPanel player2Panel = new JPanel(new GridLayout(1, 2,15,40));
        player2Panel.setBackground(new Color(235,232,210));

        JLabel label3 = new JLabel("Name:");
        label3.setFont(font);
        player2Panel.add(label3);

        player2Name = new JTextField();
        player2Name.setPreferredSize(new Dimension(120,40));
        player2Name.setFont(font);
        player2Panel.add(player2Name);

        JLabel label4 = new JLabel("Color:");
        label4.setFont(font);
        player2Panel.add(label4);

        player2Color = new JComboBox<>(new String[]{"White", "Black", "Random"});
        player2Color.setSelectedItem("Black");
        player2Color.setFont(font);
        player2Color.setBackground(Color.white);
        player2Panel.add(player2Color);
        add(player2Panel);

        JPanel timePanel = new JPanel(new GridLayout(1, 2,14,40));
        timePanel.setBackground(new Color(235,232,210));

        JLabel label5 = new JLabel("Time:");
        label5.setFont(font);
        timePanel.add(label5);

        timeCheckBox = new JCheckBox();
        timeCheckBox.setPreferredSize(new Dimension(60,40));
        timeCheckBox.setFont(font);
        timeCheckBox.setBackground(new Color(235,232,210));
        timePanel.add(timeCheckBox);

        JLabel label6 = new JLabel("Hours:");
        label6.setFont(font);
        timePanel.add(label6);

        hoursSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 24, 1));
        hoursSpinner.setEnabled(false);
        hoursSpinner.setFont(font);
        timePanel.add(hoursSpinner);

        JLabel label7 = new JLabel("Minutes:");
        label7.setFont(font);
        timePanel.add(label7);

        minutesSpinner = new JSpinner(new SpinnerNumberModel(10, 0, 59, 1));
        minutesSpinner.setEnabled(false);
        minutesSpinner.setFont(font);
        timePanel.add(minutesSpinner);

        add(timePanel);

        timeCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                boolean selected = e.getStateChange() == ItemEvent.SELECTED;
                hoursSpinner.setEnabled(selected);
                minutesSpinner.setEnabled(selected);
            }
        });

        player1Color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedColor = (String) player1Color.getSelectedItem();
                switch (selectedColor) {
                    case "White":
                        player2Color.setSelectedItem("Black");
                        break;
                    case "Black":
                        player2Color.setSelectedItem("White");
                        break;
                    case "Random":
                        player2Color.setSelectedItem("Random");
                        break;
                }
            }
        });

        player2Color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedColor = (String) player2Color.getSelectedItem();
                switch (selectedColor) {
                    case "White":
                        player1Color.setSelectedItem("Black");
                        break;
                    case "Black":
                        player1Color.setSelectedItem("White");
                        break;
                    case "Random":
                        player1Color.setSelectedItem("Random");
                        break;
                }
            }
        });
    }

    public String getPlayerName(String player) {
        String playerName;
        if(player.equals("Player1")) {
            playerName = player1Name.getText();
            if(playerName.isEmpty()) {
                playerName = "Player 1";
            }
        } else {
            playerName = player2Name.getText();
            if(playerName.isEmpty()) {
                playerName = "Player 2";
            }
        }
        return playerName;
    }

    public String getPlayerColor(String player) {
        String selectedColor;
        if(player.equals("Player1")) {
            selectedColor = (String) player1Color.getSelectedItem();
            if(selectedColor.equals("Random")) {
                selectedColor = new Random().nextBoolean() ? "White" : "Black";
                player2Color.setSelectedItem(selectedColor.equals("White") ? "Black" : "White");
            }
        } else {
            selectedColor = (String) player2Color.getSelectedItem();
            if(selectedColor.equals("Random")) {
                selectedColor = player1Color.getSelectedItem().equals("White") ? "Black" : "White";
            }
        }
        return selectedColor;
    }
}