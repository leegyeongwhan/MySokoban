package sokoban.Data;

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
}
