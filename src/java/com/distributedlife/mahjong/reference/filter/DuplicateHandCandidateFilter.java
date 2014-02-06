package com.distributedlife.mahjong.reference.filter;

import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicateHandCandidateFilter implements HandCandidateFilter {
    @Override
    public List<HandCandidate> apply(List<HandCandidate> candidates) {
        Set<HandCandidate> validCandidates = new HashSet<HandCandidate>(candidates);

        return new ArrayList<HandCandidate>(validCandidates);
    }
}
