package sokoban.game.message;

import sokoban.Data.UserCommand;
import sokoban.Wirtter.CmdStageWriterImpl;
import sokoban.Wirtter.StageWriter;
import sokoban.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GameMessage {
    private StageWriter writer;
    private Stage stage;
    private char[][] playMap;
    private int[][] intMap;

    public GameMessage(StageWriter writer, Stage stage, char[][] playMap, int[][] intMap) {
        this.writer = writer;
        this.stage = stage;
        this.playMap = playMap;
        this.intMap = intMap;
    }

    public String getMessage() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        System.out.println();
        System.out.println("SOKOBAN >" + line);
        System.out.println();
        return line;
    }

    public String[] getCommand(String line) {
        String[] commands = line.split("");
        return commands;
    }

    public void printWarning() {
        try {
            System.out.println("(경고!) 해당 명령을 수행할 수 없습니다!!");
            writer.writeStageCharMap(playMap);
        } catch (Exception e) {
            throw new IllegalStateException("경고 메세지 출력 중 문제가 발생하였습니다.");
        }
    }

    public boolean commandValid(String[] command) throws IOException {
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

    public String[] startMessageAndCommandLine() throws IOException {
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
}
