package game;

import javax.swing.*;
import java.awt.*;

public class ConfigurationPanel extends JPanel {

    public ConfigurationPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Font font = new Font("Arial", Font.BOLD, 18); // Create a Font object

        JPanel player1Panel = new JPanel(new GridLayout(1, 2));
        JLabel label1 = new JLabel("Player name:");
        label1.setFont(font); // Set the font
        player1Panel.add(label1);
        player1Panel.add(new JTextField(10));
        JLabel label2 = new JLabel("Color:");
        label2.setFont(font); // Set the font
        player1Panel.add(label2);
        player1Panel.add(new JComboBox<>(new String[]{"White", "Black", "Random"}));
        add(player1Panel);

        JPanel player2Panel = new JPanel(new GridLayout(1, 2));
        JLabel label3 = new JLabel("Player name:");
        label3.setFont(font); // Set the font
        player2Panel.add(label3);
        player2Panel.add(new JTextField(10));
        JLabel label4 = new JLabel("Color:");
        label4.setFont(font); // Set the font
        player2Panel.add(label4);
        player2Panel.add(new JComboBox<>(new String[]{"White", "Black", "Random"}));
        add(player2Panel);

        JPanel timePanel = new JPanel(new GridLayout(1, 3));
        JLabel label5 = new JLabel("Time:");
        label5.setFont(font); // Set the font
        timePanel.add(label5);
        timePanel.add(new JCheckBox());
        JLabel label6 = new JLabel("Hours:");
        label6.setFont(font); // Set the font
        timePanel.add(label6);
        timePanel.add(new JSpinner(new SpinnerNumberModel(0, 0, 24, 1)));
        JLabel label7 = new JLabel("Minutes:");
        label7.setFont(font); // Set the font
        timePanel.add(label7);
        timePanel.add(new JSpinner(new SpinnerNumberModel(10, 0, 59, 1)));
        add(timePanel);
    }
}