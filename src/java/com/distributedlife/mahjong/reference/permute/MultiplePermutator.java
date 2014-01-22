package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.data.TileSet;
import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.ArrayList;
import java.util.List;

import static com.distributedlife.mahjong.reference.hand.HandCandidate.times;

public class MultiplePermutator implements Permutator {
    final int multiples;
    private final String suit;
    final List<String> tilesMultipleIsAllowedIn;

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
                String tileInSuit = TileSet.convertTileToAppropriateSuit(suit, tile, candidate);

                if (candidate.canAdd(tileInSuit, times(multiples))) {
                    HandCandidate variation = candidate.fork();
                    variation.add(tileInSuit, times(multiples));
                    handCandidates.add(variation);
                }
            }
        }

        return handCandidates;
    }
}
