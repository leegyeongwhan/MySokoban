package sokoban.Data;

public class Position extends Point {
    int playerRaw;
    int playerCal;

    public Position(int raw, int cal, int playerRaw, int playerCal) {
        super(raw, cal);
        this.playerRaw = playerRaw;
        this.playerCal = playerCal;
    }
}
