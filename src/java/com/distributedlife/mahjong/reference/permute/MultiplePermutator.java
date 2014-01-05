package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.game.TileSet;
import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.ArrayList;
import java.util.List;

import static com.distributedlife.mahjong.reference.hand.HandCandidate.times;

public class MultiplePermutator implements Permutator {
    protected final int multiples;
    private String suit;
    protected final List<String> tilesMultipleIsAllowedIn;

    public MultiplePermutator(List<String> tilesMultipleIsAllowedIn, int multiples, String suit) {
        this.tilesMultipleIsAllowedIn = tilesMultipleIsAllowedIn;
        this.multiples = multiples;
        this.suit = suit;
    }

    @Override
    public List<HandCandidate> permute(List<HandCandidate> candidates) {
        ArrayList<HandCandidate> handCandidates = new ArrayList<HandCandidate>();

        for(HandCandidate candidate : candidates) {
            for(String tile : tilesMultipleIsAllowedIn) {
                String tileInSuit = convertTileToAppropriateSuit(tile, candidate);

                if (candidate.canAdd(tileInSuit, times(multiples))) {
                    HandCandidate variation = candidate.fork();
                    variation.add(tileInSuit, times(multiples));
                    handCandidates.add(variation);
                }
            }
        }

        return handCandidates;
    }

    private String convertTileToAppropriateSuit(String tile, HandCandidate candidate) {
        String tileSuit = "";
        if (suit.equals("1st")) {
            tileSuit = candidate.getPrimarySuit();
        }
        if (suit.equals("2nd")) {
            tileSuit = candidate.getSecondSuit();
        }
        if (suit.equals("3rd")) {
            tileSuit = candidate.getThirdSuit();
        }

        if (tile.equals("1")) {
            return TileSet.createTile(tile, tileSuit);
        }
        if (tile.equals("2")) {
            return TileSet.createTile(tile, tileSuit);
        }
        if (tile.equals("3")) {
            return TileSet.createTile(tile, tileSuit);
        }
        if (tile.equals("4")) {
            return TileSet.createTile(tile, tileSuit);
        }
        if (tile.equals("5")) {
            return TileSet.createTile(tile, tileSuit);
        }
        if (tile.equals("6")) {
            return TileSet.createTile(tile, tileSuit);
        }
        if (tile.equals("7")) {
            return TileSet.createTile(tile, tileSuit);
        }
        if (tile.equals("8")) {
            return TileSet.createTile(tile, tileSuit);
        }
        if (tile.equals("9")) {
            return TileSet.createTile(tile, tileSuit);
        }

        return tile;
    }
}
