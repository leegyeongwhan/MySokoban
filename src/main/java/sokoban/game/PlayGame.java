package sokoban.game;

import sokoban.Data.Point;
import sokoban.Data.Position;
import sokoban.Data.Sign;
import sokoban.Data.UserCommand;
import sokoban.Wirtter.CmdStageWriterImpl;
import sokoban.Wirtter.StageWriter;
import sokoban.game.message.GameMessage;
import sokoban.stage.Stage;

import java.io.IOException;

public class PlayGame {
    private Position playerLocation;
    private UserCommand command;
    private char[][] playMap;
    private int[][] intMap;
    private final Stage stage;
    private int ballInHallCnt;
    private StageWriter writer = new CmdStageWriterImpl();
    private GameMessage message;

    public PlayGame(Stage stage) {
        this.stage = stage;  // stage 객체는 따 보관해두고 stage를 r로 초기화 해야하기때문에 init게임을 따로만든다로
        initGame(stage);
    }

    private void initGame(Stage stage) {
        this.playMap = stage.stageClone();
        this.intMap = stage.getIntMap();
        this.playerLocation = new Position(stage.getPlayerLocation());
        this.message = new GameMessage(writer, stage, playMap, intMap);
    }

    public void GameStart() throws IOException {
        while (true) {
            String[] commands = message.startMessageAndCommandLine();
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
        System.out.println("파킹굳");
        System.out.println("명령어 : " + command.toLowerCase());

        UserCommand.findUserCommand(command)
                .ifPresentOrElse(this::moveProcess, this.message::printWarning);
    }


    private void moveProcess(UserCommand userCommand) {
        Point nextPoint = getPlayerNextLocation(userCommand.getPoint());
        char nextSign = playMap[nextPoint.getRaw()][nextPoint.getCal()]; //다음좌표가 볼이면
        boolean isball = (nextSign == Sign.BALL.getSign());

        if (isball) { // 볼을 움직일수 있으면 공과 플레이는 같이움직인다 다음 좌표가 볼이면 그다음 좌표도 생각핸다
            moveBallandPlayer(nextPoint, userCommand);
            return;
        } else {
            if (!isPlayerMoveable(nextPoint)) {
                message.printWarning();
                return;
            }
            movePlayer(userCommand);
            return;
        }
    }

    private void moveBallandPlayer(Point nextPoint, UserCommand userCommand) {
        int nx = nextPoint.getRaw() + userCommand.getPoint().getRaw();
        int ny = nextPoint.getCal() + userCommand.getPoint().getCal();
        int playerCal = playerLocation.getPlayerCal();
        int playerRaw = playerLocation.getPlayerRaw();
        char originSign = Sign.EMPTY.getSign();
        this.playMap[playerRaw][playerCal] = originSign;

        if (Sign.EMPTY.getSign() == playMap[nx][ny]) {//다음 좌표가 밀수있는 공간이어야 함
            this.playMap[nextPoint.getRaw()][nextPoint.getCal()] = Sign.PLAYER.getSign();
            this.playMap[nx][ny] = Sign.BALL.getSign();
        } else if (Sign.HALL.getSign() == playMap[nx][ny]) {
            this.playMap[nextPoint.getRaw()][nextPoint.getCal()] = Sign.PLAYER.getSign();
            this.playMap[nx][ny] = Sign.HALL.getSign();
            ballInHallCnt++;
        }
        getChangePoint(nextPoint.getRaw(), nextPoint.getCal());
    }

    private void getChangePoint(int nx, int ny) {
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

    private void moveBallPosition(Point nextPoint, Point point) {
        int nx = nextPoint.getRaw() + point.getRaw();
        int ny = nextPoint.getCal() + point.getCal();

        char originSign = stage.getBsllLocationSignValue(nextPoint); //가져온게볼이면
        if (Sign.BALL.getSign() == originSign) {
            originSign = Sign.PLAYER.getSign();
        }

        this.playMap[nx][ny] = Sign.BALL.getSign();
        this.playMap[nextPoint.getRaw()][nextPoint.getCal()] = originSign;
        getChangePoint(nx, ny);
    }


    private boolean isRest(String command) {
        if (command.equalsIgnoreCase(UserCommand.R.toString())) {
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

        char originSign = stage.getPlayerLocationSignValue(new Position(playerRaw, playerCal));
        if (Sign.PLAYER.getSign() == originSign) {
            originSign = Sign.EMPTY.getSign();
        }

        this.playMap[nx][ny] = Sign.PLAYER.getSign();
        this.playMap[playerRaw][playerCal] = originSign;
        getChangePoint(nx, ny);
    }

    public void reSet() throws IOException {
        System.out.println(UserCommand.R.getMessage());
        initGame(stage);
        writer.writeStageCharMap(playMap);
        writer.writeStageCharMap(stage.getChMap());
    }
}
