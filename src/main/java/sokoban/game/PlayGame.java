package sokoban.game;

import sokoban.Data.Point;
import sokoban.Data.Position;
import sokoban.Data.Sign;
import sokoban.Data.UserCommand;
import sokoban.Wirtter.CmdStageWriterImpl;
import sokoban.Wirtter.StageWriter;
import sokoban.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PlayGame {
    private Position playerLocation;
    private UserCommand command;
    private char[][] playMap;
    private int[][] intMap;
    private final Stage stage;
    private int ballInHallCnt;
    private StageWriter writer = new CmdStageWriterImpl();

    public PlayGame(Stage stage) {
        this.stage = stage;  // stage 객체는 따 보관해두고 stage를 r로 초기화 해야하기때문에 init게임을 따로만든다로
        initGame(stage);
    }

    private void initGame(Stage stage) {
        this.playMap = stage.stageClone();
        this.intMap = stage.getIntMap();
        this.playerLocation = new Position(stage.getPlayerLocation());

    }

    public void GameStart() throws IOException {
        while (true) {
            String[] commands = startMessageAndCommandLine();
            for (String command : commands) {
                if (isRest(command)) {
                    reSet();
                    continue;
                }
                excuteCommand(command);
            }
        }
    }

    private void excuteCommand(String command) throws IOException {
        command = command.toUpperCase();
        //    Optional<UserCommand> userCommand = UserCommand.findUserCommand(command); //command에 맞는 usercommand
        System.out.println();
        System.out.println("명령어 : " + command.toLowerCase());

        UserCommand.findUserCommand(command)
                .ifPresentOrElse(this::moveProcess, this::printWarning);
    }

    private void printWarning() {
        try {
            System.out.println("(경고!) 해당 명령을 수행할 수 없습니다!!");
            writer.writeStageCharMap(playMap);
        } catch (Exception e) {
            throw new IllegalStateException("경고 메세지 출력 중 문제가 발생하였습니다.");
        }

    }

    private void moveProcess(UserCommand userCommand) {
        Point nextPoint = getPlayerNextLocation(userCommand.getPoint());
        char nextSign = playMap[nextPoint.getRaw()][nextPoint.getCal()];
        boolean isball = (nextSign == Sign.BALL.getSign());

        if (isball) { // 볼을 움직일수 있으면 공과 플레이는 같이움직인다
            moveBallandPlayer(nextPoint, userCommand);
        } else {
            if (!isPlayerMoveable(nextPoint)) {
                printWarning();
                return;
            }
            movePlayer(userCommand);
        }

        if (!isBallMoveable(nextPoint, userCommand.getPoint())) {
            printWarning();
            return;
        }
        moveBall(nextPoint, userCommand);

    }

    private void moveBallandPlayer(Point nextPoint, UserCommand userCommand) {
        int nx = nextPoint.getRaw() + userCommand.getPoint().getRaw();
        int ny = nextPoint.getCal() + userCommand.getPoint().getCal();

        char originSign = stage.getBsllLocationSignValue(nextPoint); //가져온게볼이면
        if (Sign.BALL.getSign() == originSign) {
            originSign = Sign.PLAYER.getSign();
        }

        this.playMap[nx][ny] = Sign.BALL.getSign();
        this.playMap[nextPoint.getRaw()][nextPoint.getCal()] = originSign;
        this.playerLocation.setPlayerRaw(nx);
        this.playerLocation.setPlayerCal(ny);
    }


    private Point getPlayerNextLocation(Point point) {
        int playerRaw = playerLocation.getPlayerRaw() + point.getRaw();
        int playerCal = playerLocation.getPlayerCal() + point.getCal();

        return new Point(playerRaw, playerCal);
    }

    private void moveBall(Point nextPoint, UserCommand userCommand) {
        try {
            Point point = userCommand.getPoint();
            moveBallPosition(nextPoint, point);
            writer.writeStageCharMap(playMap);
        } catch (Exception e) {
            throw new IllegalStateException("볼을 움직이는데 문제가 생겼습니다");
        }
    }

    private void moveBallPosition(Point nextPoint, Point point) {
        int nx = nextPoint.getRaw() + point.getRaw();
        int ny = nextPoint.getCal() + point.getCal();

        char originSign = stage.getBsllLocationSignValue(nextPoint); //가져온게볼이면
        if (Sign.BALL.getSign() == originSign) {
            originSign = Sign.PLAYER.getSign();
        }

        this.playMap[nx][ny] = Sign.BALL.getSign();
        this.playMap[nextPoint.getRaw()][nextPoint.getCal()] = originSign;
        this.playerLocation.setPlayerRaw(nx);
        this.playerLocation.setPlayerCal(ny);
    }

    private boolean isBallMoveable(Point nextPoint, Point point) { // 세가지 경우 구멍인경우,빈공간인경우 , 벽인경우
        int nx = nextPoint.getRaw() + point.getRaw();
        int ny = nextPoint.getCal() + point.getCal();

        if (ny > playMap[nx].length || nx > playMap.length || nx < 0 || ny < 0) {
            return false;
        }

        if (playMap[nx][ny] == Sign.HALL.getSign()) {
            return true;
        }

        if (playMap[nx][ny] == Sign.EMPTY.getSign()) {
            return true;
        }
        return true;
    }

    private void reSet() throws IOException {
        System.out.println(UserCommand.R.getMessage());
        initGame(stage);
        writer.writeStageCharMap(playMap);
        writer.writeStageCharMap(stage.getChMap());
    }

    private boolean isRest(String command) {
        if (command.equalsIgnoreCase(UserCommand.R.toString())) {
            return true;
        }
        return false;
    }

    private String[] startMessageAndCommandLine() throws IOException {
        String line = getMessage();
        String[] command = getCommand(line);
        try {
            if (!commandValid(command)) {
                System.out.println(Arrays.toString(command));
                System.out.println("입력오류");
            }
        } catch (Exception e) {
            new IllegalStateException("커맨드라인 오류");
        }
        return command;
    }

    private boolean commandValid(String[] command) throws IOException {
        int cnt = 0;
        for (String s : command) {
            String str = s.toUpperCase();
            for (UserCommand value : UserCommand.values()) {
                if (str.equals(String.valueOf(value))) {
                    cnt++;
                }
            }
        }
        if (cnt == command.length) {
            return true;
        }
        return false;
    }


    private boolean isPlayerMoveable(Point nextPoint) {
        int nx = nextPoint.getRaw();
        int ny = nextPoint.getCal();

        if (ny > playMap[nx].length || nx > playMap.length || nx < 0 || ny < 0) {
            return false;
        }
        if (Sign.EMPTY.getSign() != playMap[nx][ny]) {
            return false;
        }
        return true;
    }

    private void movePlayer(UserCommand userCommand) {

        try {
            Point point = userCommand.getPoint();
            movePlayerPosition(playerLocation, point);
            System.out.println(userCommand.name() + ": " + userCommand.getMessage());
            writer.writeStageCharMap(playMap);
        } catch (Exception e) {
            throw new IllegalStateException("플레이어를 움직이는 도중 문제가 발생하였습니다.[" + userCommand.name() + "]");
        }

    }

    private void movePlayerPosition(Position playerLocation, Point point) {
        int playerRaw = playerLocation.getPlayerRaw();
        int playerCal = playerLocation.getPlayerCal();

        int nx = playerRaw + point.getRaw();
        int ny = playerCal + point.getCal();

        char originSign = stage.getPlayerLocationSignValue(playerLocation);
        if (Sign.PLAYER.getSign() == originSign) {
            originSign = Sign.EMPTY.getSign();
        }

        this.playMap[nx][ny] = Sign.PLAYER.getSign();
        this.playMap[playerRaw][playerCal] = originSign;
        this.playerLocation.setPlayerRaw(nx);
        this.playerLocation.setPlayerCal(ny);
    }

    private String getMessage() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        System.out.println();
        System.out.println("SOKOBAN >" + line);
        System.out.println();
        return line;
    }

    private String[] getCommand(String line) {
        String[] commands = line.split("");
        return commands;
    }
}
