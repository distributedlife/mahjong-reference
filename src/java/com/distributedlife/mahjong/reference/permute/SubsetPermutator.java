package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.ArrayList;
import java.util.List;

public class SubsetPermutator extends Permutator {

    private List<List<String>> subsets = new ArrayList<List<String>>();
    private int subsetLength;

    public SubsetPermutator(List<String> set, int subsetLength) {
        this.subsetLength = subsetLength;

        for (String element : set) {
            ArrayList<String> subset = new ArrayList<String>();
            subset.add(element);

            subsets.add(subset);
        }

        subsets = generateSubsets(subsets, set, subsetLength - 1);
    }

    private List<List<String>> generateSubsets(List<List<String>> subsets, List<String> set, int length) {
        if (length == 0) {
            return subsets;
        }

        List<List<String>> newSubsets = new ArrayList<List<String>>();
        for (List<String> subset : subsets) {
            for (String element : set) {
                if (subset.contains(element)) {
                    continue;
                }

                List<String> newSubset = new ArrayList<String>(subset);
                newSubset.add(element);
                newSubsets.add(newSubset);
            }
        }

        return generateSubsets(newSubsets, set, length - 1);
    }

    @Override
    public List<HandCandidate> permute(List<HandCandidate> candidates) {
        List<HandCandidate> newCandidates = new ArrayList<HandCandidate>();

        for (List<String> subset : subsets) {
            for (HandCandidate candidate : candidates) {
                HandCandidate variation = candidate.fork();

                for (String tile : subset) {
                    if (variation.canAdd(tile)) {
                        variation.add(tile);
                    }
                }

                if (candidate.getRequiredTiles().size() + subsetLength == variation.getRequiredTiles().size()) {
                    newCandidates.add(variation);
                }
            }
        }

        return newCandidates;
    }
}
