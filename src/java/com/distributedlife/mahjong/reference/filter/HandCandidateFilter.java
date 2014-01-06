package com.distributedlife.mahjong.reference.filter;

import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.List;

public interface HandCandidateFilter {
    List<HandCandidate> apply(List<HandCandidate> candidates);
}
