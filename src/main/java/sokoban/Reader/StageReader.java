package sokoban.Reader;

import sokoban.Data.Stage;

import java.io.IOException;
import java.util.List;

public interface StageReader {
    Stage readStageMap() throws IOException;
    List<Stage> readAllGameMap() throws IOException;
}
