package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.data.TileSet;
import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.ArrayList;
import java.util.List;

import static com.distributedlife.mahjong.reference.hand.HandCandidate.times;

public class MultiplePermutator extends Permutator {
    final int multiples;
    private final String defaultSuit;
    final List<String> tilesMultipleIsAllowedIn;

    public MultiplePermutator(List<String> tilesMultipleIsAllowedIn, int multiples, String defaultSuit) {
        this.tilesMultipleIsAllowedIn = tilesMultipleIsAllowedIn;
        this.multiples = multiples;
        this.defaultSuit = defaultSuit;
    }

    @Override
    public List<HandCandidate> permute(List<HandCandidate> candidates) {
        return permute(candidates, defaultSuit);
    }

    @Override
    public List<HandCandidate> permute(List<HandCandidate> candidates, String suit) {
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
