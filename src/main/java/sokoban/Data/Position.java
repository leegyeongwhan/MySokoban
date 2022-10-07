package sokoban.Data;

public class Position extends Point {
    int playerRaw;
    int playerCal;

    public Position(int playerRaw, int playerCal) {
        super(playerRaw, playerCal);
    }

    public Position(Point point) {
        super(point.getRaw(), point.getCal());
        this.playerRaw = super.getRaw();
        this.playerCal = super.getCal();
    }

}
