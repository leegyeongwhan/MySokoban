package sokoban.stage;

import sokoban.Data.Sign;
import sokoban.Data.StaticData;

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


    public static int[][] changeInnMap(List<String> lines) {
        int[][] map = new int[lines.size()][];

        for (int i = 0; i < map.length; i++) {
            map[i] = changeIntMapRow(lines.get(i));
        }

        return map;
    }

    private static int[] changeIntMapRow(String row) {
        char[] chars = row.toCharArray();
        int[] intRow = new int[chars.length];

        for (int i = 0; i < chars.length; i++) {
            intRow[i] = Sign.chageCharToInt(chars[i]);
        }

        return intRow;
    }


}
