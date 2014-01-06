package com.distributedlife.mahjong.reference.filter;

import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DuplicateHandCandidateFilter implements HandCandidateFilter {
    @Override
    public List<HandCandidate> apply(List<HandCandidate> candidates) {
        List<HandCandidate> validCandidates = new ArrayList<HandCandidate>();

        for (HandCandidate candidate : candidates) {
            boolean duplicate = false;

            for (HandCandidate validCandidate : validCandidates) {
                Collections.sort(candidate.getRequiredTiles());
                Collections.sort(validCandidate.getRequiredTiles());

                if (validCandidate.getRequiredTiles().equals(candidate.getRequiredTiles())) {
                    duplicate = true;
                }
            }

            if (!duplicate) {
                validCandidates.add(candidate);
            }
        }

        return validCandidates;
    }
}
