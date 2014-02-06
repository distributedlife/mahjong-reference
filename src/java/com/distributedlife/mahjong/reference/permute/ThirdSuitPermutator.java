package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThirdSuitPermutator extends Permutator {
    private final Permutator permutator;

    public ThirdSuitPermutator(Permutator permutator) {
        this.permutator = permutator;
    }

    @Override
    public Set<HandCandidate> permute(Set<HandCandidate> candidates) {
        Set<HandCandidate> newCandidates = new HashSet<HandCandidate>();

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
