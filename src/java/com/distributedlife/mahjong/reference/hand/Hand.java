package com.distributedlife.mahjong.reference.hand;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Hand))
            return false;

        Hand rhs = (Hand) obj;
        return new EqualsBuilder().
                append(name, rhs.name).
                append(requiredTiles, rhs.requiredTiles).
                isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31).
                append(name).
                append(requiredTiles).
                toHashCode();
    }
}
