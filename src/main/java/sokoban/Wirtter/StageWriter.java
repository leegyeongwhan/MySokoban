package sokoban.Wirtter;

import sokoban.stage.Stage;

import java.io.IOException;
import java.util.List;

public interface StageWriter {


    void writeStageAllMap(List<Stage> maps) throws IOException;

    void writeStageMap(Stage readStageMap) throws IOException;
}
