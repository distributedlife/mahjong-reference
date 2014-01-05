package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.HandCandidate;

import java.util.ArrayList;
import java.util.List;

public class AnyPairedPermutator implements Permutator {
    @Override
    public List<HandCandidate> permute(List<HandCandidate> candidates) {
        ArrayList<HandCandidate> handCandidates = new ArrayList<HandCandidate>();

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
