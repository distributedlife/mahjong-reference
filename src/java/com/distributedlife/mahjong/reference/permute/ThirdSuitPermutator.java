package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.ArrayList;
import java.util.List;

public class ThirdSuitPermutator implements Permutator {
    private Permutator permutator;

    public ThirdSuitPermutator(Permutator permutator) {
        this.permutator = permutator;
    }

    @Override
    public List<HandCandidate> permute(List<HandCandidate> candidates) {
        List<HandCandidate> newCandidates = new ArrayList<HandCandidate>();

        for (HandCandidate candidate : candidates) {
            if (candidate.getThirdSuit().isEmpty() || candidate.getSecondSuit().isEmpty()) {
                List<String> otherSuits = getThreeSuits();
                otherSuits.remove(candidate.getPrimarySuit());
                otherSuits.remove(candidate.getSecondSuit());

                candidate.setThirdSuit(otherSuits.get(0));
            }

            newCandidates.add(candidate);
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
