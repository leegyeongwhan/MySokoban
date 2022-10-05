package sokoban;


import sokoban.Data.Stage;
import sokoban.Data.StaticData;
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
            List<Stage> map = reader.readAllMap();

            for (Stage stage : map) {
                AllPrintData(stage);
            }

        } catch (Exception e) {
            System.out.println("오류발생");
        }
    }

    private static void AllPrintData(Stage stage) {
        System.out.println("Stage" + stage.getStageNumber());
        System.out.println();
    }
}
