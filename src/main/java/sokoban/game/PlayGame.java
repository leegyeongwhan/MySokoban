package sokoban.game;

import sokoban.Data.Point;
import sokoban.Data.Position;
import sokoban.Data.UserCommand;
import sokoban.Wirtter.CmdStageWriterImpl;
import sokoban.Wirtter.StageWriter;
import sokoban.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PlayGame {
    private Position playerLocation;
    private UserCommand command;
    private char[][] chMap;
    private int[][] intMap;
    private String[] commands;
    private final Stage stage;

    public PlayGame(Stage stage) {
        this.chMap = stage.getChMap();
        this.intMap = stage.getIntMap();
        this.playerLocation = new Position(stage.getPlayerLocation());
        this.stage = stage;
    }


    public void gameSet(Stage stage) throws IOException {
        startMessageAndCommandLine();
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

        if (!isMoveable()) {
            return;
        }
        writer.writeStageMap(movePlayer(userCommand));
    }

    private boolean isMoveable() {
        return true;
    }

    private Stage movePlayer(UserCommand userCommand) throws IOException {
        Point point = userCommand.getPoint();

        int nx = playerLocation.getRaw() + point.getRaw();
        int ny = playerLocation.getCal() + point.getCal();

        playerLocation.setPlayerRaw(nx);
        playerLocation.setPlayerCal(ny);

        return stage;
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
