package com.distributedlife.mahjong.reference.hand;

import java.util.ArrayList;
import java.util.List;

public class HandCandidate {
    private final String name;
    private final List<String> availableTiles;
    private String primarySuit;
    private List<String> requiredTiles;

    public HandCandidate(String name, List<String> availableTiles) {
        this.name = name;
        this.availableTiles = availableTiles;
        this.requiredTiles = new ArrayList<String>();
    }

    public HandCandidate(HandCandidate other) {
        this.name = other.name;
        this.primarySuit = other.primarySuit;
        this.availableTiles = new ArrayList<String>(other.availableTiles);
        this.requiredTiles = new ArrayList<String>(other.requiredTiles);
    }

    public void setPrimarySuit(String primarySuit) {
        this.primarySuit = primarySuit;
    }

    public boolean isValid() {
        return requiredTiles.size() == 14;
    }

    public String getName() {
        return name;
    }

    public List<String> getRequiredTiles() {
        return requiredTiles;
    }

    public String getPrimarySuit() {
        return primarySuit;
    }

    public boolean canAdd(String tile) {
        return canAdd(tile, times(1));
    }

    public boolean canAdd(String tile, int times) {
        List<String> copyOfRemainingTiles = new ArrayList<String>(availableTiles);

        for (int i = 0; i < times; i++) {
            if (!copyOfRemainingTiles.contains(tile)) {
                return false;
            }

            copyOfRemainingTiles.remove(tile);
        }

        return true;
    }

    public void add(String tile) {
        add(tile, times(1));
    }

    public void add(String tile, int times) {
        for (int i = 0; i < times; i++) {
            availableTiles.remove(tile);
            requiredTiles.add(tile);
        }
    }

    public static int times(int i) {
        return i;
    }

    public HandCandidate fork() {
        return new HandCandidate(this);
    }
}
