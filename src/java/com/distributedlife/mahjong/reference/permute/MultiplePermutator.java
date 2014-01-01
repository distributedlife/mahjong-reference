package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.HandCandidate;

import java.util.ArrayList;
import java.util.List;

import static com.distributedlife.mahjong.reference.HandCandidate.times;

public class MultiplePermutator implements Permutator {
    private final int multiples;
    private final List<String> tilesMultipleIsAllowedIn;

    public MultiplePermutator(List<String> tilesMultipleIsAllowedIn, int multiples) {
        this.tilesMultipleIsAllowedIn = tilesMultipleIsAllowedIn;
        this.multiples = multiples;
    }

    @Override
    public List<HandCandidate> permute(List<HandCandidate> candidates) {
        ArrayList<HandCandidate> handCandidates = new ArrayList<HandCandidate>();

        for(HandCandidate candidate : candidates) {
            for(String tile : tilesMultipleIsAllowedIn) {
                if (candidate.canAdd(tile, times(multiples))) {
                    HandCandidate variation = candidate.fork();
                    variation.add(tile, times(multiples));
                    handCandidates.add(variation);
                }
            }
        }

        return handCandidates;
    }
}
