package com.distributedlife.mahjong.reference.permute;

import java.util.List;

public class PermutatorBuilderOptions {
    private final String type;
    private final Integer from;
    private final Integer to;
    private final List<String> tiles;
    private final String suit;
    private String tile;
    private int length;

    public PermutatorBuilderOptions(String type, int from, int to, List<String> tiles, String suit, String tile, int length) {
        this.type = type;
        this.from = from;
        this.to = to;
        this.tiles = tiles;
        this.suit = suit;
        this.tile = tile;
        this.length = length;
    }

    public String getType() {
        return type;
    }

    public Integer getFrom() {
        return from;
    }

    public Integer getTo() {
        return to;
    }

    public List<String> getTiles() {
        return tiles;
    }

    public String getSuit() {
        return suit;
    }

    public String getTile() {
        return tile;
    }

    public int getLength() {
        return length;
    }
}
