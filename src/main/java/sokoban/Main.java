package sokoban;


import sokoban.game.SokobanGame;
import sokoban.stage.Stage;
import sokoban.Reader.CmdStageReaderImpl;
import sokoban.Reader.StageReader;
import sokoban.Wirtter.CmdStageWriterImpl;
import sokoban.Wirtter.StageWriter;

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
            StageWriter writer = new CmdStageWriterImpl();
            List<Stage> stageList = reader.readAllGameMap();

            writer.writeStageAllMap(stageList);
            new SokobanGame(stageList);

        } catch (Exception e) {
            System.out.println("오류발생");
        }
    }
}
