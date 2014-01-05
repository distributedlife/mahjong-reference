package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.List;

public class UnknownPermutator implements Permutator {
    private final String key;

    public UnknownPermutator(String key) {
        this.key = key;
    }

    @Override
    public List<HandCandidate> permute(List<HandCandidate> candidates) {
        System.err.println(String.format("An unknown permutator was supplied. Key name is '%s'", key));

        return candidates;
    }
}
