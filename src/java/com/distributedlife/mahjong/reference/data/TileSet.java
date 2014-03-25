package com.distributedlife.mahjong.reference.data;

import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.ArrayList;
import java.util.Arrays;
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

    public static String getNameForBitTile(Tile tile) {
        if (tile.equals(Tile.B1)) {
            return "1 Bamboo";
        } else if (tile.equals(Tile.B2)) {
            return "2 Bamboo";
        } else if (tile.equals(Tile.B3)) {
            return "3 Bamboo";
        } else if (tile.equals(Tile.B4)) {
            return "4 Bamboo";
        } else if (tile.equals(Tile.B5)) {
            return "5 Bamboo";
        } else if (tile.equals(Tile.B6)) {
            return "6 Bamboo";
        } else if (tile.equals(Tile.B7)) {
            return "7 Bamboo";
        } else if (tile.equals(Tile.B8)) {
            return "8 Bamboo";
        } else if (tile.equals(Tile.B9)) {
            return "9 Bamboo";
        } else if (tile.equals(Tile.C1)) {
            return "1 Crack";
        } else if (tile.equals(Tile.C2)) {
            return "2 Crack";
        } else if (tile.equals(Tile.C3)) {
            return "3 Crack";
        } else if (tile.equals(Tile.C4)) {
            return "4 Crack";
        } else if (tile.equals(Tile.C5)) {
            return "5 Crack";
        } else if (tile.equals(Tile.C6)) {
            return "6 Crack";
        } else if (tile.equals(Tile.C7)) {
            return "7 Crack";
        } else if (tile.equals(Tile.C8)) {
            return "8 Crack";
        } else if (tile.equals(Tile.C9)) {
            return "9 Crack";
        } else if (tile.equals(Tile.S1)) {
            return "1 Spot";
        } else if (tile.equals(Tile.S2)) {
            return "2 Spot";
        } else if (tile.equals(Tile.S3)) {
            return "3 Spot";
        } else if (tile.equals(Tile.S4)) {
            return "4 Spot";
        } else if (tile.equals(Tile.S5)) {
            return "5 Spot";
        } else if (tile.equals(Tile.S6)) {
            return "6 Spot";
        } else if (tile.equals(Tile.S7)) {
            return "7 Spot";
        } else if (tile.equals(Tile.S8)) {
            return "8 Spot";
        } else if (tile.equals(Tile.S9)) {
            return "9 Spot";
        } else if (tile.equals(Tile.RD)) {
            return "Red";
        } else if (tile.equals(Tile.GD)) {
            return "Green";
        } else if (tile.equals(Tile.WD)) {
            return "White";
        } else if (tile.equals(Tile.NW)) {
            return "North";
        } else if (tile.equals(Tile.EW)) {
            return "East";
        } else if (tile.equals(Tile.SW)) {
            return "South";
        } else if (tile.equals(Tile.WW)) {
            return "West";
        } else if (tile.equals(Tile.OW)) {
            return "OwnWind";
        } else {
            return "WRONG";
        }
    }

    public static Long getValueForTile(String tile) {
        if (tile.equals("1 Bamboo")) {
            return Tile.B1.v;
        }
        if (tile.equals("2 Bamboo")) {
            return Tile.B2.v;
        }
        if (tile.equals("3 Bamboo")) {
            return Tile.B3.v;
        }
        if (tile.equals("4 Bamboo")) {
            return Tile.B4.v;
        }
        if (tile.equals("5 Bamboo")) {
            return Tile.B5.v;
        }
        if (tile.equals("6 Bamboo")) {
            return Tile.B6.v;
        }
        if (tile.equals("7 Bamboo")) {
            return Tile.B7.v;
        }
        if (tile.equals("8 Bamboo")) {
            return Tile.B8.v;
        }
        if (tile.equals("9 Bamboo")) {
            return Tile.B9.v;
        }
        if (tile.equals("1 Spot")) {
            return Tile.S1.v;
        }
        if (tile.equals("2 Spot")) {
            return Tile.S2.v;
        }
        if (tile.equals("3 Spot")) {
            return Tile.S3.v;
        }
        if (tile.equals("4 Spot")) {
            return Tile.S4.v;
        }
        if (tile.equals("5 Spot")) {
            return Tile.S5.v;
        }
        if (tile.equals("6 Spot")) {
            return Tile.S6.v;
        }
        if (tile.equals("7 Spot")) {
            return Tile.S7.v;
        }
        if (tile.equals("8 Spot")) {
            return Tile.S8.v;
        }
        if (tile.equals("9 Spot")) {
            return Tile.S9.v;
        }
        if (tile.equals("1 Crack")) {
            return Tile.C1.v;
        }
        if (tile.equals("2 Crack")) {
            return Tile.C2.v;
        }
        if (tile.equals("3 Crack")) {
            return Tile.C3.v;
        }
        if (tile.equals("4 Crack")) {
            return Tile.C4.v;
        }
        if (tile.equals("5 Crack")) {
            return Tile.C5.v;
        }
        if (tile.equals("6 Crack")) {
            return Tile.C6.v;
        }
        if (tile.equals("7 Crack")) {
            return Tile.C7.v;
        }
        if (tile.equals("8 Crack")) {
            return Tile.C8.v;
        }
        if (tile.equals("9 Crack")) {
            return Tile.C9.v;
        }
        if (tile.equals("Red")) {
            return Tile.RD.v;
        }
        if (tile.equals("Green")) {
            return Tile.GD.v;
        }
        if (tile.equals("White")) {
            return Tile.WD.v;
        }
        if (tile.equals("North")) {
            return Tile.NW.v;
        }
        if (tile.equals("East")) {
            return Tile.EW.v;
        }
        if (tile.equals("South")) {
            return Tile.SW.v;
        }
        if (tile.equals("West")) {
            return Tile.WW.v;
        }
        if (tile.equals("OwnWind")) {
            return Tile.OW.v;
        }

        return 0L;
    }

    public enum Winds {East, North, West, South, OwnWind}
    public enum Dragons {White, Red, Green}
    public enum Suits {Bamboo, Spot, Crack}

    public static List<Tile> BIT_TILES = Arrays.asList(
            Tile.B1, Tile.B2, Tile.B3, Tile.B4, Tile.B5, Tile.B6 , Tile.B7, Tile.B8, Tile.B9,
            Tile.C1, Tile.C2, Tile.C3, Tile.C4, Tile.C5, Tile.C6, Tile.C7, Tile.C8, Tile.C9,
            Tile.S1, Tile.S2, Tile.S3, Tile.S4, Tile.S5, Tile.S6, Tile.S7, Tile.S8, Tile.S9,
            Tile.RD, Tile.GD, Tile.WD,
            Tile.NW, Tile.EW, Tile.SW, Tile.WW, Tile.OW
    );

    public enum Tile {

        B1, B2, B3, B4, B5, B6, B7, B8, B9,
        C1, C2, C3, C4, C5, C6, C7, C8, C9,
        S1, S2, S3, S4, S5, S6, S7, S8, S9,
        RD, GD, WD,
        NW, EW, SW, WW, OW;

        public final long v;
        Tile() {
            this.v = 1L << this.ordinal();
        }
    }

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
