package game;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable {
    public static final int WIDTH = 1400;
    public static final int HEIGHT = 900;
    final int FPS = 60;
    Thread gameThread;

    public Game() {
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Color.darkGray);
    }

    public void launchGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    private void update() {

    }
    public void paint(Graphics g) {
        super.paintComponent(g);
        ChessBoard board = new ChessBoard(50,50);
        board.draw((Graphics2D) g);
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }
}
