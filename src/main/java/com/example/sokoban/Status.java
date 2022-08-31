package com.example.sokoban;

import java.util.ArrayList;

public class Status {
    private ArrayList<ArrayList<String>> map;

    public Status(ArrayList<ArrayList<String>> map) {
        this.map = map;
    }

    public static String width;
    public static String height;
    public static String hallcount;
    public static String ballcount;
    int wall;
    int hall;
    int ball;
    int player;
    int stage;

    void countStatus() {
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {

            }
        }
    }
}
