package sokoban.Reader;

import sokoban.Data.Stage;

import java.io.IOException;
import java.util.List;

public interface StageReader {
    Stage readGameMap() throws IOException;
    List<Stage> readAllMap() throws IOException;
}
