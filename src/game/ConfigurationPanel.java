package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ConfigurationPanel extends JPanel {

    public ConfigurationPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Font font = new Font("Arial", Font.BOLD, 18); // Create a Font object

        JPanel player1Panel = new JPanel(new GridLayout(1, 2));
        JLabel label1 = new JLabel("Player name:");
        label1.setFont(font);
        player1Panel.add(label1);
        player1Panel.add(new JTextField());
        JLabel label2 = new JLabel("Color:");
        label2.setFont(font);
        label2.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        player1Panel.add(label2);
        JComboBox<String> player1Color = new JComboBox<>(new String[]{"White", "Black", "Random"});
        player1Panel.add(player1Color);
        add(player1Panel);

        JPanel player2Panel = new JPanel(new GridLayout(1, 2));
        JLabel label3 = new JLabel("Player name:");
        label3.setFont(font);
        player2Panel.add(label3);
        player2Panel.add(new JTextField(5));
        JLabel label4 = new JLabel("Color:");
        label4.setFont(font);
        label4.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        player2Panel.add(label4);
        JComboBox<String> player2Color = new JComboBox<>(new String[]{"White", "Black", "Random"});
        player2Color.setSelectedItem("Black");
        player2Panel.add(player2Color);
        add(player2Panel);

        JPanel timePanel = new JPanel(new GridLayout(1, 3));
        JLabel label5 = new JLabel("Time:");
        label5.setFont(font);
        timePanel.add(label5);
        JCheckBox timeCheckBox = new JCheckBox();
        timePanel.add(timeCheckBox);
        JLabel label6 = new JLabel("Hours:");
        label6.setFont(font);
        timePanel.add(label6);
        JSpinner hoursSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 24, 1));
        hoursSpinner.setEnabled(false);
        timePanel.add(hoursSpinner);
        JLabel label7 = new JLabel("Minutes:");
        label7.setFont(font);
        timePanel.add(label7);
        JSpinner minutesSpinner = new JSpinner(new SpinnerNumberModel(10, 0, 59, 1));
        minutesSpinner.setEnabled(false);
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
}