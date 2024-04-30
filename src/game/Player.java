package game;

public class Player {
    String name;
    String color;
    int wins, draws, loses = 0;

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

    public  String getBalance() {
        return wins + "-" + draws + "-" + loses;
    }
}
