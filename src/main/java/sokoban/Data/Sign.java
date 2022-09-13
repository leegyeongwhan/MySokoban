package sokoban.Data;

public enum Sign {
    EMPTY(' ', -1),
    WALL('#', 0),
    HALL('O', 1),
    BALL('o', 2),
    PLAYER('P', 3),
    STAGE_DIV('=', 4);

    private char sign;
    public int value;

    Sign(char sign, int value) {
        this.sign = sign;
        this.value = value;
    }


}
