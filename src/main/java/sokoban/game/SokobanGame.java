package sokoban.game;

import sokoban.Reader.CmdStageReaderImpl;
import sokoban.Reader.StageReader;
import sokoban.Wirtter.CmdStageWriterImpl;
import sokoban.Wirtter.StageWriter;
import sokoban.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SokobanGame {

    private List<Stage> stageList = new ArrayList<>();

    public SokobanGame(List<Stage> stageList) throws IOException {
        if (stageList.size() < 1) {
            throw new IllegalArgumentException("올바르지 않은 스테이지 목록입니다");
        }
        //스테이지 호출
        System.out.println("플레이할 스테이지 입력");
        StageReader reader = new CmdStageReaderImpl();
        StageWriter writer = new CmdStageWriterImpl();
        Stage stage = reader.readStageMap();
        writer.writeStageCharMap(stage);
        firstStageGame(stage);
    }

    private void firstStageGame(Stage stage) throws IOException {
        PlayGame playGame = new PlayGame(stage);
        playGame.GameStart();
    }
}
