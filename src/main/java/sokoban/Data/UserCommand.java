package sokoban.Data;

public enum UserCommand {
    W(new Point(-1, 0), "위로 이동합니다."), // 위
    A(new Point(0, -1), "왼쪽으로 이동합니다."), // 왼쪽
    S(new Point(1, 0), "아래쪽으로 이동합니다."),  // 아래
    D(new Point(0, 1), "오른쪽으로 이동합니다."),  // 오른쪽
    Q(new Point(0, 0), "프로그램을 종료합니다");  // 프로그램 종료

    UserCommand(Point point, String message) {
        this.point = point;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    //플레이어 이동
    private Point point;
    private String message;

    public Point getPoint() {
        return point;
    }
}
