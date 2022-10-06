package sokoban.Data;

public class Point {
    private int raw;
    private int cal;

    public Point(int raw, int cal) {
        this.raw = raw;
        this.cal = cal;
    }

    public int getRaw() {
        return raw;
    }

    public int getCal() {
        return cal;
    }
}
