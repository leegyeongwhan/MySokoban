package sokoban.Data;

import java.util.Arrays;
import java.util.Optional;

public enum UserCommand {
    W(new Point(-1, 0), "위로 이동합니다."), // 위
    A(new Point(0, -1), "왼쪽으로 이동합니다."), // 왼쪽
    S(new Point(1, 0), "아래쪽으로 이동합니다."),  // 아래
    D(new Point(0, 1), "오른쪽으로 이동합니다."),  // 오른쪽
    Q(new Point(0, 0), "프로그램을 종료합니다"),  // 프로그램 종료
    R(new Point(0, 0), "스테이지를 초기화 합니다.");  // 스테이지 초기화

    UserCommand(Point point, String message) {
        this.point = point;
        this.message = message;
    }

    public static Optional<UserCommand> findUserCommand(String command) {
        try {
            for (UserCommand playerCommand : UserCommand.values()) {
                if (command.equals(playerCommand.name())) {
                    return Optional.of(playerCommand);
                }
            }
        } catch (Exception e) {
            new IllegalStateException("일치하는 커멘드 값이 없습니다");
        }
        return Optional.empty();
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
