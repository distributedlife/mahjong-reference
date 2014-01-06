package com.distributedlife.mahjong.game;

import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.ArrayList;
import java.util.List;

public class TileSet {
    private final List<String> tiles = new ArrayList<String>();

    public static String createTile(int i, String suit) {
        return String.format("%s %s", i, suit);
    }

    private static String createTile(int i, Suits suit) {
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

    public static String convertTileToAppropriateSuit(String suit, String tile, HandCandidate candidate) {
        String tileSuit = "";
        if (suit.equals("1st")) {
            tileSuit = candidate.getPrimarySuit();
        }
        if (suit.equals("2nd")) {
            tileSuit = candidate.getSecondSuit();
        }
        if (suit.equals("3rd")) {
            tileSuit = candidate.getThirdSuit();
        }

        if (tile.equals("1")) {
            return TileSet.createTile(tile, tileSuit);
        }
        if (tile.equals("2")) {
            return TileSet.createTile(tile, tileSuit);
        }
        if (tile.equals("3")) {
            return TileSet.createTile(tile, tileSuit);
        }
        if (tile.equals("4")) {
            return TileSet.createTile(tile, tileSuit);
        }
        if (tile.equals("5")) {
            return TileSet.createTile(tile, tileSuit);
        }
        if (tile.equals("6")) {
            return TileSet.createTile(tile, tileSuit);
        }
        if (tile.equals("7")) {
            return TileSet.createTile(tile, tileSuit);
        }
        if (tile.equals("8")) {
            return TileSet.createTile(tile, tileSuit);
        }
        if (tile.equals("9")) {
            return TileSet.createTile(tile, tileSuit);
        }

        return tile;
    }
}
