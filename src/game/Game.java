package game;

import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Game extends JPanel implements Runnable {
    public static final int WIDTH = 1400;
    public static final int HEIGHT = 900;
    final int FPS = 60;
    Thread gameThread;
    ChessBoard board;
    public static ArrayList<Piece> pieces = new ArrayList<>();
    public static ArrayList<Piece> simPieces = new ArrayList<>();

    public Game() {
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Color.darkGray);
        board = new ChessBoard(50,50);
        setPieces();
    }

    public void launchGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    private void update() {

    }
    public void paint(Graphics g) {
        super.paintComponent(g);
        board.draw((Graphics2D) g);
        for(Piece p : pieces) {
            p.draw((Graphics2D) g);
        }
    }

    public void setPieces() {
        //WHITE
        for(int i = 0 ; i <8; i++) {
            pieces.add(new Pawn(true,i,6));
        }
        pieces.add(new Rook(true,0,7));
        pieces.add(new Knight(true,1,7));
        pieces.add(new Bishop(true,2,7));
        pieces.add(new Queen(true,3,7));
        pieces.add(new King(true,4,7));
        pieces.add(new Bishop(true,5,7));
        pieces.add(new Knight(true,6,7));
        pieces.add(new Rook(true,7,7));

        //BLACK
        for(int i = 0 ; i <8; i++) {
            pieces.add(new Pawn(false,i,1));
        }
        pieces.add(new Rook(false,0,0));
        pieces.add(new Knight(false,1,0));
        pieces.add(new Bishop(false,2,0));
        pieces.add(new Queen(false,3,0));
        pieces.add(new King(false,4,0));
        pieces.add(new Bishop(false,5,0));
        pieces.add(new Knight(false,6,0));
        pieces.add(new Rook(false,7,0));
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
