package game;

public class Player {
    String name;
    String color;
    int wins, draws, loses = 0;
    private CountdownTimer countdownTimer;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getBalance() {
        return wins + "-" + draws + "-" + loses;
    }

    public void startTimer() {
        if (countdownTimer != null) {
            countdownTimer.start();
        }
    }

    public void stopTimer() {
        if (countdownTimer != null) {
            countdownTimer.stop();
        }
    }

    public void setCountdownTimer(CountdownTimer countdownTimer) {
        this.countdownTimer = countdownTimer;
    }

    public CountdownTimer getCountdownTimer() {
        return countdownTimer;
    }

    public boolean isWhite() {
        if (color.equals("White"))
        {
            return true;
        }
        return false;
    }
}
