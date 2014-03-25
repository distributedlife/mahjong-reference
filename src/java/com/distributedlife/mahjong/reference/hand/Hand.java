package com.distributedlife.mahjong.reference.hand;

import com.distributedlife.mahjong.reference.data.TileSet;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class Hand implements Comparable<Hand> {
    private final String name;
    private long part1 = 0;
    private long part2 = 0;
    private long part3 = 0;
    private long part4 = 0;

    public Hand(String name, List<String> requiredTiles) {
        this.name = name;

        if (requiredTiles != null) {
            convertHandToBitFields(requiredTiles);
        }
    }

    public Hand(String name, Long part1, Long part2, Long part3, Long part4) {
        this.name = name;
        this.part1 = part1;
        this.part2 = part2;
        this.part3 = part3;
        this.part4 = part4;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        List<String> tiles = new ArrayList<String>();

        for (TileSet.Tile bitTile : TileSet.BIT_TILES) {
            if ((part1 & bitTile.v) == bitTile.v) {
                tiles.add(TileSet.getNameForBitTile(bitTile));
            }
        }
        for (TileSet.Tile bitTile : TileSet.BIT_TILES) {
            if ((part2 & bitTile.v) == bitTile.v) {
                tiles.add(TileSet.getNameForBitTile(bitTile));
            }
        }
        for (TileSet.Tile bitTile : TileSet.BIT_TILES) {
            if ((part3 & bitTile.v) == bitTile.v) {
                tiles.add(TileSet.getNameForBitTile(bitTile));
            }
        }
        for (TileSet.Tile bitTile : TileSet.BIT_TILES) {
            if ((part4 & bitTile.v) == bitTile.v) {
                tiles.add(TileSet.getNameForBitTile(bitTile));
            }
        }

        return tiles.toString();
    }

    private void convertHandToBitFields(List<String> requiredTiles) {
        List<String> remainingRequiredTiles = new ArrayList<String>(requiredTiles);

        for (TileSet.Tile bitTile : TileSet.BIT_TILES) {
            String tile = TileSet.getNameForBitTile(bitTile);

            if (remainingRequiredTiles.contains(tile)) {
                part1 += bitTile.v;
                remainingRequiredTiles.remove(tile);
            }
            if (remainingRequiredTiles.contains(tile)) {
                part2 += bitTile.v;
                remainingRequiredTiles.remove(tile);
            }
            if (remainingRequiredTiles.contains(tile)) {
                part3 += bitTile.v;
                remainingRequiredTiles.remove(tile);
            }
            if (remainingRequiredTiles.contains(tile)) {
                part4 += bitTile.v;
                remainingRequiredTiles.remove(tile);
            }
        }
    }

    @Override
    public int compareTo(Hand rhs) {
        return new CompareToBuilder()
                .append(name, rhs.name)
                .append(part1, rhs.part1)
                .append(part2, rhs.part2)
                .append(part3, rhs.part3)
                .append(part4, rhs.part4)
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
                append(part1, rhs.part1).
                append(part2, rhs.part2).
                append(part3, rhs.part3).
                append(part4, rhs.part4).
                isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31).
                append(name).
                append(part1).
                append(part2).
                append(part3).
                append(part4).
                toHashCode();
    }

    public long getPart1() {
        return part1;
    }

    public long getPart2() {
        return part2;
    }

    public long getPart3() {
        return part3;
    }

    public long getPart4() {
        return part4;
    }

    public boolean isPartialMatch(Hand compareTo) {
        return isPartialMatch(compareTo.getPart1());
    }

    public boolean isPartialMatch(Long part1) {
        if ((this.part1 & part1) > 0L) {
            return true;
        }

        return false;
    }
}
