package sokoban;


import sokoban.Data.Stage;
import sokoban.Reader.CmdStageReaderImpl;
import sokoban.Reader.StageReader;

import java.util.List;

/**
 * 예시
 * Stage 1
 * <p>
 * #####
 * #OoP#
 * #####
 * 가로 크기 :
 * 세로 크기 :
 * 구멍의 수 :
 * 공의 수 :
 * 플레이어 위치
 */
public class Main {
    public static void main(String[] args) {

        try {
            StageReader reader = new CmdStageReaderImpl();
            List<Stage> maps = reader.readAllGameMap();

            for (Stage stage : maps) {
                AllPrintData(stage);
            }

        } catch (Exception e) {
            System.out.println("오류발생");
        }
    }

    private static void AllPrintData(Stage stage) {
        System.out.println("Stage" + stage.getStageNumber());
        stage.printMap();
        System.out.println("가로 크기 : " + stage.getMapSize().getX());
        System.out.println("세로 크기 : " + stage.getMapSize().getY());
        System.out.println("구멍의 수 : " + stage.getHallCount());
        System.out.println(" 공의 수 : " + stage.getBallCount());
        int playerX = stage.getPlayerLocation().getX() + 1;
        int playerY = stage.getPlayerLocation().getY() + 1;
        System.out.println("플레이어 위치 (" + playerX + "," + playerY + ")");
        System.out.println();
    }
}
