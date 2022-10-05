package sokoban.Data;

import java.util.List;

public class StageUtil {

    public static boolean isStartStage(String line) {
        if (line.contains(StaticData.STAGE_START.getKeyword())) {
            return true;
        }
        return false;
    }

    public static boolean isEndStage(String line) {
        if (line.contains(StaticData.STAGE_END.getKeyword())) {
            return true;
        }
        return false;
    }

    public static char[][] changeChMap(List<String> lines) {
        char[][] map = new char[lines.size()][];

        for (int i = 0; i < lines.size(); i++) {
            map[i] = lines.get(i).toCharArray();
        }
        return map;
    }
}
