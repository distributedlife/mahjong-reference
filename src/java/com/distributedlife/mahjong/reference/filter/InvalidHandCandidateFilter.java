package com.distributedlife.mahjong.reference.filter;

import com.distributedlife.mahjong.hand.Hand;
import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.ArrayList;
import java.util.List;

public class InvalidHandCandidateFilter {
    public List<Hand> apply(List<HandCandidate> candidates) {
        List<Hand> hands = new ArrayList<Hand>();
        for (HandCandidate candidate : candidates) {
            if (!candidate.isValid()) {
                continue;
            }

            hands.add(new Hand(candidate.getName(), candidate.getRequiredTiles()));
        }

        return hands;
    }
}