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
    private char[][] chMap;
    private int[][] intMap;
    private final Stage stage;
    private int ballInHallCnt;
    private StageWriter writer = new CmdStageWriterImpl();
    ;

    public PlayGame(Stage stage) {
        this.stage = stage;  // stage 객체는 따 보관해두고 stage를 r로 초기화 해야하기때문에 init게임을 따로만든다로
        initGame(stage);
    }

    private void initGame(Stage stage) {
        this.chMap = stage.getChMap();
        this.intMap = stage.getIntMap();
        this.playerLocation = new Position(stage.getPlayerLocation());
        this.chMap = stage.getChMap();
        this.intMap = stage.getIntMap();
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

    private void excuteCommand(String command) {

    }

    private void reSet() throws IOException {
        System.out.println(UserCommand.R.getMessage());
        initGame(stage);
        writer.writeStageCharMap(chMap);
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

    private void executeStage(UserCommand userCommand, String str) throws IOException {
        System.out.println();
        System.out.println("명령어 : " + userCommand);
        System.out.println(str.toLowerCase() + " : " + userCommand.getMessage());

        if (!isPlayerMoveable(playerLocation, userCommand.getPoint())) {
            try {
                writer.writeStageCharMap(chMap);
                System.out.println("(경고!) 해당 명령을 수행할 수 없습니다!!");
            } catch (Exception e) {
                throw new IllegalStateException("경고 메세지 출력 중 문제가 발생하였습니다.");
            }
            return;
        }
        movePlayer(userCommand);
        writer.writeStageCharMap(chMap);
    }

    private boolean isPlayerMoveable(Position playerLocation, Point point) {
        int playerRaw = playerLocation.getPlayerRaw();
        int playerCal = playerLocation.getPlayerCal();

        int nx = playerRaw + point.getRaw();
        int ny = playerCal + point.getCal();

        if (ny > chMap[nx].length || nx > chMap.length || nx < 0 || ny < 0) {
            return false;
        }
        if (Sign.EMPTY.getSign() != chMap[nx][ny]) {
            return false;
        }
        return true;
    }

    private void movePlayer(UserCommand userCommand) throws IOException {
        int playerRaw = playerLocation.getPlayerRaw();
        int playerCal = playerLocation.getPlayerCal();
        Point point = userCommand.getPoint();
        int nx = playerRaw + point.getRaw();
        int ny = playerCal + point.getCal();

        char originSign = stage.getPlayerLocationSignValue(playerLocation);
        if (Sign.PLAYER.getSign() == originSign) {
            originSign = Sign.EMPTY.getSign();
        }

        chMap[nx][ny] = Sign.PLAYER.getSign();
        chMap[playerRaw][playerCal] = originSign;
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
