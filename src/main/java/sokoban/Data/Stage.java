package sokoban.Data;

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
        //         System.out.println(lines);
        this.chMap = StageUtil.changeChMap(lines);
        this.intMap = StageUtil.changeInnMap(lines);

        //      System.out.println(Arrays.deepToString(chMap));
//        System.out.println(Arrays.deepToString(intMap));
        this.stageNumber = stageNumber;

        setMap(chMap, intMap);
    }


    public void setMap(char[][] charMap, int[][] intMap) {
        int height = charMap.length;
        int width;

        for (int i = 0; i < height; i++) {
            width = charMap[i].length;
            for (int j = 0; j < width; j++) {
                System.out.print(charMap[i][j]);

                int value = intMap[i][j];
                if (Sign.BALL.getValue() == value) ballCount++;
                if (Sign.HALL.getValue() == value) hallCount++;
                if (Sign.PLAYER.getValue() == value) playerLocation = new Point(i, j);
            }
            System.out.println();
        }
    }

    public char[][] getChMap() {
        return chMap;
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
