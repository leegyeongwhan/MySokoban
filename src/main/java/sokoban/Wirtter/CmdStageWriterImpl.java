package sokoban.Wirtter;

import sokoban.stage.Stage;

import java.io.IOException;
import java.util.List;

public class CmdStageWriterImpl implements StageWriter {


    @Override
    public void writeStageAllMap(List<Stage> maps) throws IOException {

        for (Stage stage : maps) {
            AllPrintData(stage);
        }
    }

    @Override
    public void writeStageMap(Stage stage) throws IOException {
        System.out.println("Stage" + stage.getStageNumber());
        stage.printMap();
    }

    private static void AllPrintData(Stage stage) {
        System.out.println("Stage" + stage.getStageNumber());
        stage.printMap();
        System.out.println("가로 크기 : " + stage.getMapSize().getRaw());
        System.out.println("세로 크기 : " + stage.getMapSize().getCal());
        System.out.println("구멍의 수 : " + stage.getHallCount());
        System.out.println(" 공의 수 : " + stage.getBallCount());
        int playerX = stage.getPlayerLocation().getRaw() + 1;
        int playerY = stage.getPlayerLocation().getCal() + 1;
        System.out.println("플레이어 위치 (" + playerX + "," + playerY + ")");
        System.out.println();
    }
}
