package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.Set;

public class UnknownPermutator extends Permutator {
    private final String key;

    public UnknownPermutator(String key) {
        this.key = key;
    }

    @Override
    public Set<HandCandidate> permute(Set<HandCandidate> candidates) {
        System.err.println(String.format("An unknown permutator was supplied. Key name is '%s'", key));

        return candidates;
    }
}
