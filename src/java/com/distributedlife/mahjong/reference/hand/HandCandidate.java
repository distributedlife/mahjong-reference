package com.distributedlife.mahjong.reference.hand;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandCandidate {
    private final String name;
    final List<String> availableTiles;
    private String primarySuit;
    private final List<String> requiredTiles;
    private String secondSuit;
    private String thirdSuit;

    public HandCandidate(String name, List<String> availableTiles) {
        this.name = name;
        this.availableTiles = availableTiles;
        this.requiredTiles = new ArrayList<String>();
        this.secondSuit = "";
        this.thirdSuit = "";
    }

    public HandCandidate(HandCandidate other) {
        this.name = other.name;
        this.primarySuit = other.primarySuit;
        this.availableTiles = new ArrayList<String>(other.availableTiles);
        this.requiredTiles = new ArrayList<String>(other.requiredTiles);
        this.secondSuit = other.secondSuit;
        this.thirdSuit = other.thirdSuit;
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

        Collections.sort(requiredTiles);
    }

    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof HandCandidate))
            return false;

        HandCandidate rhs = (HandCandidate) obj;
        return new EqualsBuilder().
                append(name, rhs.name).
                append(requiredTiles, rhs.requiredTiles).
                isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 31).
                append(name).
                append(requiredTiles).
                toHashCode();
    }

    public static int times(int i) {
        return i;
    }

    public HandCandidate fork() {
        return new HandCandidate(this);
    }

    public String getSecondSuit() {
        return secondSuit;
    }

    public void setSecondSuit(String secondSuit) {
        this.secondSuit = secondSuit;
    }

    public String getThirdSuit() {
        return thirdSuit;
    }

    public void setThirdSuit(String thirdSuit) {
        this.thirdSuit = thirdSuit;
    }
}
