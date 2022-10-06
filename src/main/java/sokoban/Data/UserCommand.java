package sokoban.Data;

import java.util.Arrays;

public enum UserCommand {
    W(new Point(-1, 0)), // 위
    A(new Point(0, -1)), // 왼쪽
    S(new Point(1, 0)),  // 아래
    D(new Point(0, 1)),  // 오른쪽
    Q(new Point(0, 0));  // 프로그램 종료

    //플레이어 이동
    private Point direction;

    UserCommand(Point point) {
        this.direction = point;
    }

    public Point getDirection() {
        return direction;
    }
}
