package com.distributedlife.mahjong.reference.hand;

import java.util.List;

public class Hand {
    private final String name;
    private final List<String> requiredTiles;

    public Hand(String name, List<String> requiredTiles) {
        this.name = name;
        this.requiredTiles = requiredTiles;
    }

    public String getName() {
        return name;
    }

    public List<String> getRequiredTiles() {
        return requiredTiles;
    }
}
