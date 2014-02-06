package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.data.TileSet;
import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.HashSet;
import java.util.Set;

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
    public Set<HandCandidate> permute(Set<HandCandidate> candidates) {
        return permute(candidates, defaultSuit);
    }

    @Override
    public Set<HandCandidate> permute(Set<HandCandidate> candidates, String suit) {
        Set<HandCandidate> handCandidates = new HashSet<HandCandidate>();

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
