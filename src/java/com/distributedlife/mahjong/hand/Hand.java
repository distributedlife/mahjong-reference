package com.distributedlife.mahjong.hand;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private String name;
    private final List<String> requiredTiles;

    public Hand(String name, List<String> requiredTiles) {
        this.name = name;
        this.requiredTiles = requiredTiles;
    }

    public Hand(String name) {
        this.name = name;
        requiredTiles = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public List<String> getRequiredTiles() {
        return requiredTiles;
    }

    public void addRequiredTile(String tileRequired) {
        requiredTiles.add(tileRequired);
    }

    public void addRequiredTiles(List<String> requiredTiles) {
        this.requiredTiles.addAll(requiredTiles);
    }
}
