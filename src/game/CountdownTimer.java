package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountdownTimer {
    private long timeRemaining;
    private Timer timer;

    public CountdownTimer(long timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public void start() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decrementTime();
            }
        });
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    private void decrementTime() {
        timeRemaining -= 1000;
        if (timeRemaining < 0) {
            timer.stop();
            timeRemaining = 0;
        }
    }

    public long getTimeRemaining() {
        return timeRemaining;
    }
}