package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.ArrayList;
import java.util.List;

public class PermutatorExecutor {
    public List<HandCandidate> runPermutatorsOnCandidate(HandCandidate candidate, List<Permutator> permutators) {
        List<HandCandidate> candidates = new ArrayList<HandCandidate>();
        candidates.add(candidate);

        for (Permutator permutator : permutators) {
            candidates = permutator.permute(candidates);
        }

        return candidates;
    }
}