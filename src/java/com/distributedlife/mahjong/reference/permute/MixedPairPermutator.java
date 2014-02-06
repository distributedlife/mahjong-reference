package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.data.TileSet;
import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.*;

public class MixedPairPermutator extends Permutator {
    private List<String> tiles;

    public MixedPairPermutator(List<String> tiles) {
        super();
        this.tiles = tiles;
    }

    private List<List<String>> produceListOfMixedPairs() {
        List<List<String>> listOfMixedPungs = new ArrayList<List<String>>();

        for(String tile : tiles) {
            String bamboo = TileSet.createTile(tile, "Bamboo");
            String crack = TileSet.createTile(tile, "Crack");
            String spot = TileSet.createTile(tile, "Spot");

            listOfMixedPungs.add(Arrays.asList(bamboo, crack));
            listOfMixedPungs.add(Arrays.asList(bamboo, spot));
            listOfMixedPungs.add(Arrays.asList(crack, spot));
        }

        return listOfMixedPungs;
    }

    @Override
    Set<HandCandidate> permute(Set<HandCandidate> candidates) {
        Set<HandCandidate> newCandidates = new HashSet<HandCandidate>();

        for (List<String> mixedPair : produceListOfMixedPairs()) {
            for(HandCandidate candidate : candidates) {
                HandCandidate variation = candidate.fork();

                for (String tile : mixedPair) {
                    if (variation.canAdd(tile)) {
                        variation.add(tile);
                    }
                }

                if (variation.getRequiredTiles().size() == candidate.getRequiredTiles().size() + 2) {
                    newCandidates.add(variation);
                }
            }
        }

        return newCandidates;
    }
}
