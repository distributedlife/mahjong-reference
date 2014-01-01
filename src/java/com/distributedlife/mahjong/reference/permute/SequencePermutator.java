package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.HandCandidate;

import java.util.ArrayList;
import java.util.List;

public class SequencePermutator implements Permutator {
    private final int from;
    private final int to;

    public SequencePermutator(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public List<HandCandidate> permute(List<HandCandidate> candidates) {
        ArrayList<HandCandidate> handCandidates = new ArrayList<HandCandidate>();

        for(HandCandidate candidate : candidates) {
            for (int i = from; i <= to; i++) {
                String tile = createTile(i, candidate.getPrimarySuit());

                if (candidate.canAdd(tile)) {
                    candidate.add(tile);
                }
            }

            handCandidates.add(candidate);
        }

        return handCandidates;
    }

    public static String createTile(int i, String suit) {
        return String.format("%s %s", i, suit);
    }
}
