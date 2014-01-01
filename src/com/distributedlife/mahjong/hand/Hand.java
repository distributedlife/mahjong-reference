package com.distributedlife.mahjong.hand;

import java.util.List;

public class Hand {
    private String name;
    private final List<String> requiredTiles;

    public Hand(String name, List<String> requiredTiles) {
        this.name = name;
        this.requiredTiles = requiredTiles;
    }

    public String getName() {
        return name;
    }

    public Iterable<? extends String> getRequiredTiles() {
        return requiredTiles;
    }
}
