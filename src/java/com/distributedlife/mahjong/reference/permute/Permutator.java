package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.Set;

public abstract class Permutator {
    abstract Set<HandCandidate> permute(Set<HandCandidate> candidates);

    public Set<HandCandidate> permute(Set<HandCandidate> candidates, String suit) {
        return permute(candidates);
    }
}
