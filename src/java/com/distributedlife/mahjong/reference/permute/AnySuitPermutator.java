package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnySuitPermutator extends Permutator {
    private Permutator permutator;

    public AnySuitPermutator(Permutator permutator) {
        this.permutator = permutator;
    }

    @Override
    public Set<HandCandidate> permute(Set<HandCandidate> candidates) {
        Set<HandCandidate> newCandidates = new HashSet<HandCandidate>();

        for (String suit : getThreeSuits()) {
            newCandidates.addAll(permutator.permute(candidates, suit));
        }

        return newCandidates;
    }

    @Override
    public Set<HandCandidate> permute(Set<HandCandidate> candidates, String suit) {
        return permute(candidates);
    }

    private List<String> getThreeSuits() {
        List<String> otherSuits = new ArrayList<String>() ;
        otherSuits.add("1st");
        otherSuits.add("2nd");
        otherSuits.add("3rd");
        return otherSuits;
    }
}
