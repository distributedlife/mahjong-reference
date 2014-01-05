package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.List;

public interface Permutator {
    List<HandCandidate> permute(List<HandCandidate> candidates);
}
