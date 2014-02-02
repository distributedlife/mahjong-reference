package com.distributedlife.mahjong.reference.hand;

import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.List;

public class Hand implements Comparable<Hand> {
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

    @Override
    public int compareTo(Hand rhs) {
        return new CompareToBuilder()
                .append(name, rhs.name)
                .append(requiredTiles.toArray(), rhs.requiredTiles.toArray())
                .toComparison();
    }
}
