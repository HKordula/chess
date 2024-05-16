package game;

public class Player {
    private final String name;
    private final String color;
    int wins, draws, losses = 0;

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
        return wins + "-" + draws + "-" + losses;
    }

    public boolean isWhite() {
        return color.equals("White");
    }
}