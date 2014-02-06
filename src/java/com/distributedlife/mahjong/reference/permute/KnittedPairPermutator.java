package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.data.TileSet;
import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.*;

public class KnittedPairPermutator extends Permutator {
    private List<String> tiles;

    public KnittedPairPermutator(List<String> tiles) {
        super();
        this.tiles = tiles;
    }

    @Override
    Set<HandCandidate> permute(Set<HandCandidate> candidates) {
        Set<HandCandidate> newCandidates = new HashSet<HandCandidate>();

        for(HandCandidate candidate : candidates) {
            List<String> suits = new ArrayList<String>();
            suits.add("Bamboo");
            suits.add("Crack");
            suits.add("Spot");
            suits.remove(candidate.getPrimarySuit());

            for (String tile : tiles) {
                String first = TileSet.createTile(tile, candidate.getPrimarySuit());
                String second = TileSet.createTile(tile, suits.get(0));
                String third = TileSet.createTile(tile, suits.get(1));

                HandCandidate variation1 = candidate.fork();
                if (variation1.canAdd(first) && variation1.canAdd(second)) {
                    variation1.add(first);
                    variation1.add(second);

                    newCandidates.add(variation1);
                }

                HandCandidate variation2 = candidate.fork();
                if (variation2.canAdd(first) && variation2.canAdd(third)) {
                    variation2.add(first);
                    variation2.add(third);

                    newCandidates.add(variation2);
                }
            }
        }

        return newCandidates;
    }
}
