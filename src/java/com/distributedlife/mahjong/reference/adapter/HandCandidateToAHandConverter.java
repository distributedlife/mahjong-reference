package com.distributedlife.mahjong.reference.adapter;

import com.distributedlife.mahjong.reference.hand.Hand;
import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.ArrayList;
import java.util.List;

public class HandCandidateToAHandConverter {
    public List<Hand> convert(List<HandCandidate> candidates) {
        List<Hand> hands = new ArrayList<Hand>();

        for (HandCandidate candidate : candidates) {
            hands.add(new Hand(candidate.getName(), candidate.getRequiredTiles()));
        }

        return hands;
    }
}
