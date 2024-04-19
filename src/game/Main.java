package game;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("Chess");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

//        ImageIcon img = new ImageIcon(Main.class.getResource("/images/logo.png"));
//        window.setIconImage(img.getImage());

        Game game = new Game();
        window.add(game);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}