package com.distributedlife.mahjong.reference.filter;

import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.ArrayList;
import java.util.List;

public class InvalidHandCandidateFilter implements HandCandidateFilter {
    @Override
    public List<HandCandidate> apply(List<HandCandidate> candidates) {
        List<HandCandidate> validCandidates = new ArrayList<HandCandidate>();

        for (HandCandidate candidate : candidates) {
            if (!candidate.isValid()) {
                continue;
            }

            validCandidates.add(candidate);
        }

        return validCandidates;
    }
}