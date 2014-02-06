package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.data.TileSet;
import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.*;

public class MixedPungPermutator extends Permutator {
    private List<String> tiles;

    public MixedPungPermutator(List<String> tiles) {
        super();
        this.tiles = tiles;
    }

    private List<List<String>> produceListOfMixedPungs() {
        List<List<String>> listOfMixedPungs = new ArrayList<List<String>>();

        for(String tile : tiles) {
            String bamboo = TileSet.createTile(tile, "Bamboo");
            String crack = TileSet.createTile(tile, "Crack");
            String spot = TileSet.createTile(tile, "Spot");

            listOfMixedPungs.add(Arrays.asList(bamboo, crack, spot));
        }

        return listOfMixedPungs;
    }

    @Override
    Set<HandCandidate> permute(Set<HandCandidate> candidates) {
        Set<HandCandidate> newCandidates = new HashSet<HandCandidate>();

        for (List<String> mixedPung : produceListOfMixedPungs()) {
            for(HandCandidate candidate : candidates) {
                HandCandidate variation = candidate.fork();

                for (String tile : mixedPung) {
                    if (variation.canAdd(tile)) {
                        variation.add(tile);
                    }
                }

                if (variation.getRequiredTiles().size() == candidate.getRequiredTiles().size() + 3) {
                    newCandidates.add(variation);
                }
            }
        }

        return newCandidates;
    }
}
