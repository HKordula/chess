package game;

import javax.swing.*;
import java.awt.*;


public class Game extends JPanel {
    public static final int WIDTH = 1400;
    public static final int HEIGHT = 900;
    Board board;
    Mouse mouse;
    JPanel boardPanel;

    public Game() {
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Color.darkGray);
        board = new Board(50,50);
        mouse = new Mouse(board, this);

        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        board.draw(g2d);

    }

}
