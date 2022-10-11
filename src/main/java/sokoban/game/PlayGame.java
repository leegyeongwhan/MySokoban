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
import java.util.ArrayList;
import java.util.List;

public class PlayGame {
    private Position playerLocation;
    private UserCommand command;
    private char[][] chMap;
    private int[][] intMap;
    private final Stage stage;

    public PlayGame(Stage stage) {
        this.chMap = stage.getChMap();
        this.intMap = stage.getIntMap();
        this.playerLocation = new Position(stage.getPlayerLocation());
        this.stage = stage;
        this.chMap = stage.getChMap();
        this.intMap = stage.getIntMap();
    }


    public void gameSet(Stage stage) throws IOException {
        while (true) {
            startMessageAndCommandLine();
        }
    }

    private void startMessageAndCommandLine() throws IOException {
        String line = getMessage();
        String[] command = getCommand(line);
        commandValid(command);
    }

    private void commandValid(String[] command) throws IOException {
        List<UserCommand> userCommandList = new ArrayList<>();
        for (String s : command) {
            String str = s.toUpperCase();
            for (UserCommand value : UserCommand.values()) {
                if (str.equals(String.valueOf(value))) {
                    executeStage(value, str);
                }
            }
        }
    }

    private void executeStage(UserCommand userCommand, String str) throws IOException {
        System.out.println();
        System.out.println("명령어 : " + userCommand);
        System.out.println(str.toLowerCase() + " : " + userCommand.getMessage());
        StageWriter writer = new CmdStageWriterImpl();

        if (!isMoveable(playerLocation, userCommand.getPoint())) {
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

    private boolean isMoveable(Position playerLocation, Point point) {
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
