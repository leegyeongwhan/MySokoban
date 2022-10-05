package sokoban.Data;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Stage {
    private char[][] chMap;
    private int[][] intMap;
    private int stageNumber;

    private Point mapSize;  //가로 세로
    private Point playerLocation;
    private int hallCount;
    private int ballCount;

    public Stage(List<String> lines, int stageNumber) {
        System.out.println(lines);
        this.chMap = StageUtil.changeChMap(lines);
        this.intMap = StageUtil.changeInnMap(lines);

        System.out.println(Arrays.deepToString(chMap));
        System.out.println(Arrays.deepToString(intMap));
        this.stageNumber = stageNumber;
    }


    public void setMap(char[][] map) {
        int height = map.length;
        int width = map[0].length;


    }


    public int getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(int stageNumber) {
        this.stageNumber = stageNumber;
    }

    public Point getMapSize() {
        return mapSize;
    }

    public void setMapSize(Point mapSize) {
        this.mapSize = mapSize;
    }

    public Point getPlayerLocation() {
        return playerLocation;
    }

    public void setPlayerLocation(Point playerLocation) {
        this.playerLocation = playerLocation;
    }

    public int getHallCount() {
        return hallCount;
    }

    public void setHallCount(int hallCount) {
        this.hallCount = hallCount;
    }

    public int getBallCount() {
        return ballCount;
    }

    public void setBallCount(int ballCount) {
        this.ballCount = ballCount;
    }
}
