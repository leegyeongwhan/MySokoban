package sokoban.Data;

import java.util.Arrays;

public enum Sign {
    EMPTY(' ', -1),
    WALL('#', 0),
    HALL('O', 1),
    BALL('o', 2),
    PLAYER('P', 3),
    STAGE_DIV('=', 4),
    BALL_IN_HALL('0', 5);

    private char sign;
    public int value;

    Sign(char sign, int value) {
        this.sign = sign;
        this.value = value;
    }

    public static int chageCharToInt(char ch) {
        for (Sign sign : Sign.values()) {
            if (sign.getSign() == ch) {
                return sign.getValue();
            }
        }
        return 0;
    }

    public char getSign() {
        return sign;
    }

    public int getValue() {
        return value;
    }
}
