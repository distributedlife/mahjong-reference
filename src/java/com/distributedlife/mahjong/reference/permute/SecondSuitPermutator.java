package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.ArrayList;
import java.util.List;

public class SecondSuitPermutator implements Permutator {
    private final Permutator permutator;

    public SecondSuitPermutator(Permutator permutator) {
        this.permutator = permutator;
    }

    @Override
    public List<HandCandidate> permute(List<HandCandidate> candidates) {
        List<HandCandidate> newCandidates = new ArrayList<HandCandidate>();

        for (HandCandidate candidate : candidates) {
            if (candidate.getSecondSuit().isEmpty()) {
                List<String> otherSuits = getThreeSuits();
                otherSuits.remove(candidate.getPrimarySuit());

                for (String otherSuit : otherSuits) {
                    HandCandidate newCandidate = candidate.fork();
                    newCandidate.setSecondSuit(otherSuit);
                    newCandidates.add(newCandidate);
                }
            } else {
                newCandidates.add(candidate);
            }
        }

        return permutator.permute(newCandidates);
    }

    private List<String> getThreeSuits() {
        List<String> otherSuits = new ArrayList<String>() ;
        otherSuits.add("Bamboo");
        otherSuits.add("Spot");
        otherSuits.add("Crack");
        return otherSuits;
    }
}
