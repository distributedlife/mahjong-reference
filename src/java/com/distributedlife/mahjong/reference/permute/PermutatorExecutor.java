package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermutatorExecutor {
    public Set<HandCandidate> runPermutatorsOnCandidate(HandCandidate candidate, List<Permutator> permutators) {
        Set<HandCandidate> candidates = new HashSet<HandCandidate>();
        candidates.add(candidate);

        for (Permutator permutator : permutators) {
            candidates = permutator.permute(candidates);
        }

        return candidates;
    }
}