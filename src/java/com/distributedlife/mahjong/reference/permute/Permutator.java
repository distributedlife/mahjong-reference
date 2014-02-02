package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.hand.HandCandidate;
import java.util.List;

public abstract class Permutator {
    abstract List<HandCandidate> permute(List<HandCandidate> candidates);

    public List<HandCandidate> permute(List<HandCandidate> candidates, String suit) {
        return permute(candidates);
    }
}
