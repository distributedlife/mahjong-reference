package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.data.TileSet;
import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombinationSequencePermutator implements Permutator {
    private int length;
    private String suit;

    public CombinationSequencePermutator(int length, String suit) {
        this.length = length;
        this.suit = suit;
    }

    @Override
    public List<HandCandidate> permute(List<HandCandidate> candidates) {
        List<HandCandidate> newCandidates = new ArrayList<HandCandidate>();

        for (Map<String, Integer> run : produceListOfCombinations()) {
            for(HandCandidate candidate : candidates) {
                HandCandidate variation = candidate.fork();

                for (int i = run.get("from"); i <= run.get("to"); i++) {
                    String tile = TileSet.convertTileToAppropriateSuit(suit, String.valueOf(i), candidate);

                    if (variation.canAdd(tile)) {
                        variation.add(tile);
                    }
                }

                newCandidates.add(variation);
            }
        }

        return newCandidates;
    }

    private List<Map<String, Integer>> produceListOfCombinations() {
        List<Map<String, Integer>> listOfRuns = new ArrayList<Map<String, Integer>>();

        for(int start = 1; start <= 9; start++) {
            int finish = start + length - 1;
            if (finish <= 9) {
                Map<String, Integer> run = new HashMap<String, Integer>();
                run.put("from", start);
                run.put("to", finish);

                listOfRuns.add(run);
            }
        }
        return listOfRuns;
    }
}
