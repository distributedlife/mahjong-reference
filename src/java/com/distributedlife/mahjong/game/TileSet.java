package com.distributedlife.mahjong.game;

import java.util.ArrayList;
import java.util.List;

public class TileSet {
    List<String> tiles = new ArrayList<String>();

    public static String createTile(int i, String suit) {
        return String.format("%s %s", i, suit);
    }

    public static String createTile(int i, Suits suit) {
        return String.format("%s %s", i, suit.toString());
    }

    public static String createTile(String i, String suit) {
        return String.format("%s %s", i, suit);
    }

    public List<String> getTiles() {
        return tiles;
    }

    public enum Winds {East, North, West, South}
    public enum Dragons {White, Red, Green}
    public enum Suits {Bamboo, Spot, Crack}

    public TileSet() {
        for (Winds wind : Winds.values()) {
            tiles.add(wind.toString());
            tiles.add(wind.toString());
            tiles.add(wind.toString());
            tiles.add(wind.toString());
        }
        for (Dragons dragon : Dragons.values()) {
            tiles.add(dragon.toString());
            tiles.add(dragon.toString());
            tiles.add(dragon.toString());
            tiles.add(dragon.toString());
        }
        for (Suits suit : Suits.values()) {
            for (int i = 1; i <= 9; i++) {
                tiles.add(createTile(i, suit));
                tiles.add(createTile(i, suit));
                tiles.add(createTile(i, suit));
                tiles.add(createTile(i, suit));
            }
        }
    }
}
