package com.distributedlife.mahjong.reference.permute;

import java.util.List;

public class PermutatorBuilderOptions {
    private String type;
    private Integer from;
    private Integer to;
    private List<String> tiles;
    private String suit;

    public PermutatorBuilderOptions(String type, int from, int to, List<String> tiles, String suit) {
        this.type = type;
        this.from = from;
        this.to = to;
        this.tiles = tiles;
        this.suit = suit;
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
}
