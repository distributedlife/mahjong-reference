package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.game.TileSet;
import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.ArrayList;
import java.util.List;

import static com.distributedlife.mahjong.reference.hand.HandCandidate.times;

public class MultiplePermutator implements Permutator {
    protected final int multiples;
    protected final List<String> tilesMultipleIsAllowedIn;

    public MultiplePermutator(List<String> tilesMultipleIsAllowedIn, int multiples) {
        this.tilesMultipleIsAllowedIn = tilesMultipleIsAllowedIn;
        this.multiples = multiples;
    }

    @Override
    public List<HandCandidate> permute(List<HandCandidate> candidates) {
        ArrayList<HandCandidate> handCandidates = new ArrayList<HandCandidate>();

        for(HandCandidate candidate : candidates) {
            for(String tile : tilesMultipleIsAllowedIn) {
                String tileInSuit = convertTileToCandidateSuit(candidate, tile);

                if (candidate.canAdd(tileInSuit, times(multiples))) {
                    HandCandidate variation = candidate.fork();
                    variation.add(tileInSuit, times(multiples));
                    handCandidates.add(variation);
                }
            }
        }

        return handCandidates;
    }

    private String convertTileToCandidateSuit(HandCandidate candidate, String tile) {
        if (tile.equals("1")) {
            return TileSet.createTile(tile, candidate.getPrimarySuit());
        }
        if (tile.equals("2")) {
            return TileSet.createTile(tile, candidate.getPrimarySuit());
        }
        if (tile.equals("3")) {
            return TileSet.createTile(tile, candidate.getPrimarySuit());
        }
        if (tile.equals("4")) {
            return TileSet.createTile(tile, candidate.getPrimarySuit());
        }
        if (tile.equals("5")) {
            return TileSet.createTile(tile, candidate.getPrimarySuit());
        }
        if (tile.equals("6")) {
            return TileSet.createTile(tile, candidate.getPrimarySuit());
        }
        if (tile.equals("7")) {
            return TileSet.createTile(tile, candidate.getPrimarySuit());
        }
        if (tile.equals("8")) {
            return TileSet.createTile(tile, candidate.getPrimarySuit());
        }
        if (tile.equals("9")) {
            return TileSet.createTile(tile, candidate.getPrimarySuit());
        }

        return tile;
    }
}
