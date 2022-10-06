package sokoban.Data;

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

        this.chMap = StageUtil.changeChMap(lines);
        this.intMap = StageUtil.changeInnMap(lines);
        this.stageNumber = stageNumber;

        setMap(chMap, intMap);
    }


    public void setMap(char[][] charMap, int[][] intMap) {
        int height = charMap.length;
        int width;
        int widthMax = 0;

        for (int i = 0; i < height; i++) {
            width = charMap[i].length;
            widthMax = Math.max(widthMax, width);
            for (int j = 0; j < width; j++) {

                int value = intMap[i][j];
                if (Sign.BALL.getValue() == value) ballCount++;
                if (Sign.HALL.getValue() == value) hallCount++;
                if (Sign.PLAYER.getValue() == value) playerLocation = new Point(i, j);
            }
        }
        mapSize = new Point(widthMax, height);
    }

    public void printMap() {
        int height = chMap.length;
        int width;

        System.out.println();
        for (int i = 0; i < height; i++) {
            width = chMap[i].length;
            for (int j = 0; j < width; j++) {
                System.out.print(chMap[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getStageNumber() {
        return stageNumber;
    }

    public Point getMapSize() {
        return mapSize;
    }

    public Point getPlayerLocation() {
        return playerLocation;
    }


    public int getHallCount() {
        return hallCount;
    }

    public int getBallCount() {
        return ballCount;
    }

}
