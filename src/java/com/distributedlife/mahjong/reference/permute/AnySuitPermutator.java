package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.ArrayList;
import java.util.List;

public class AnySuitPermutator extends Permutator {
    private Permutator permutator;

    public AnySuitPermutator(Permutator permutator) {
        this.permutator = permutator;
    }

    @Override
    public List<HandCandidate> permute(List<HandCandidate> candidates) {
        List<HandCandidate> newCandidates = new ArrayList<HandCandidate>();

        for (String suit : getThreeSuits()) {
            newCandidates.addAll(permutator.permute(candidates, suit));
        }

        return newCandidates;
    }

    @Override
    public List<HandCandidate> permute(List<HandCandidate> candidates, String suit) {
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
