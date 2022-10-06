package sokoban.Reader;

import sokoban.Data.Stage;

import java.io.IOException;
import java.util.List;

public interface StageReader {
    List<Stage> readAllGameMap() throws IOException;
    Stage readStageMap() throws IOException;

}
