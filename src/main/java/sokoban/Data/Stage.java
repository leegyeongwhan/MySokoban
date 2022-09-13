package sokoban.Data;

import sokoban.Status;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Stage {
    static ArrayList<ArrayList<String>> map;
    static ArrayList<ArrayList<String>> numMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new ArrayList<>();
        String input = "";
        numMap = new ArrayList<>();

        while (true) {
            input = br.readLine();
            if (input == null || input.equals("")) {
                break;
            }
            map.add(new ArrayList<>(Collections.singleton(input)));
        }
        readerInfoMap();
        //       System.out.println(map);
        System.out.println(numMap);
        printMap();
        new Status(numMap);

    }

    private static void readerInfoMap() {
        for (int i = 0; i < map.size(); i++) {
            //System.out.println(String.valueOf(map.get(i)));
            String[] str = String.valueOf(map.get(i)).split("");
            numMap.add(new ArrayList<>());
            for (int j = 0; j < str.length; j++) {
                // System.out.println(str[j]);
                if (str[j].equals("#")) {
                    numMap.get(i).add("0");
                } else if (str[j].equals("O")) {
                    numMap.get(i).add("1");
                } else if (str[j].equals("O")) {
                    numMap.get(i).add("2");
                } else if (str[j].equals("P")) {
                    numMap.get(i).add("3");
                } else if (str[j].equals("=")) {
                    numMap.get(i).add("4");
                } else {
                    numMap.get(i).add("");
                }
            }
        }
    }

    private static void printMap() {
        for (int i = 0; i < map.size(); i++) {
            String str = String.join(" ", map.get(i));
            //  System.out.println("str=" + str);
            if (str.contains("Stage")) {
                System.out.println(str);
                System.out.println();
                continue;
            } else if (str.contains("=")) {
                printInfo();
                continue;
            } else {
                System.out.print(str);
                System.out.println();
            }
            if (i == map.size() - 1) {
                printInfo();
            }
        }
    }

    private static void printInfo() {
        System.out.println();
        System.out.println("가로크기: " + Status.width);
        System.out.println("세로크기: " + Status.height);
        System.out.println("구멍의 수: " + Status.hallcount);
        System.out.println("공의 수: " + Status.ballcount);
        System.out.println("플레이어 위치");
        System.out.println();
    }
}
