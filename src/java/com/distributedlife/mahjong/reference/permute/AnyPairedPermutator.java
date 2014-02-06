package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.HashSet;
import java.util.Set;

public class AnyPairedPermutator extends Permutator {
    @Override
    public Set<HandCandidate> permute(Set<HandCandidate> candidates) {
        Set<HandCandidate> handCandidates = new HashSet<HandCandidate>();

        for (HandCandidate candidate : candidates) {
            for (String tile : candidate.getRequiredTiles()) {
                if (candidate.canAdd(tile)) {
                    HandCandidate variation = candidate.fork();

                    variation.add(tile);
                    handCandidates.add(variation);
                }
            }
        }

        return handCandidates;
    }
}
