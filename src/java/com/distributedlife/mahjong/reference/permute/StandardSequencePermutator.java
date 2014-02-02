package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.data.TileSet;
import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.ArrayList;
import java.util.List;

public class StandardSequencePermutator extends Permutator {
    private final int from;
    private final int to;
    private String defaultSuit;

    public StandardSequencePermutator(int from, int to, String defaultSuit) {
        this.from = from;
        this.to = to;
        this.defaultSuit = defaultSuit;
    }

    @Override
    public List<HandCandidate> permute(List<HandCandidate> candidates) {
        return permute(candidates, defaultSuit);
    }

    @Override
    public List<HandCandidate> permute(List<HandCandidate> candidates, String suit) {
        ArrayList<HandCandidate> handCandidates = new ArrayList<HandCandidate>();

        for(HandCandidate candidate : candidates) {
            for (int i = from; i <= to; i++) {
                String tile = TileSet.convertTileToAppropriateSuit(suit, Integer.toString(i), candidate);

                if (candidate.canAdd(tile)) {
                    candidate.add(tile);
                }
            }

            handCandidates.add(candidate);
        }

        return handCandidates;
    }
}
