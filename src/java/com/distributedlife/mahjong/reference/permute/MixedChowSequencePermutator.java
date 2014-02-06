package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.data.TileSet;
import com.distributedlife.mahjong.reference.hand.HandCandidate;

import java.util.*;

public class MixedChowSequencePermutator extends Permutator {
    @Override
    public Set<HandCandidate> permute(Set<HandCandidate> candidates) {
        Set<HandCandidate> newCandidates = new HashSet<HandCandidate>();

        for (Map<String, String> run : produceListOfCombinations()) {
            for(HandCandidate candidate : candidates) {
                HandCandidate variation = candidate.fork();

                int from = Integer.parseInt(run.get("from"));
                String tile;

                tile = TileSet.createTile(from, run.get("suit1"));
                if (variation.canAdd(tile)) {
                    variation.add(tile);
                }

                tile = TileSet.createTile(from + 1, run.get("suit2"));
                if (variation.canAdd(tile)) {
                    variation.add(tile);
                }

                tile = TileSet.createTile(from + 2, run.get("suit3"));
                if (variation.canAdd(tile)) {
                    variation.add(tile);
                }

                newCandidates.add(variation);
            }
        }

        return newCandidates;
    }

    private List<Map<String, String>> produceListOfCombinations() {
        List<Map<String, String>> listOfRuns = new ArrayList<Map<String, String>>();

        for(int start = 1; start <= 9; start++) {
            int finish = start + 2;
            if (finish <= 9) {
                Map<String, String> run = new HashMap<String, String>();
                run.put("from", String.valueOf(start));

                listOfRuns.add(run);
            }
        }

        List<Map<String, String>> listOfMixedRuns = new ArrayList<Map<String, String>>();
        for (Map<String, String> run : listOfRuns) {
            Map<String, String> run1 = new HashMap<String, String>(run);
            run1.put("suit1", "Bamboo");
            run1.put("suit2", "Spot");
            run1.put("suit3", "Crack");
            listOfMixedRuns.add(run1);

            Map<String, String> run2 = new HashMap<String, String>(run);
            run2.put("suit1", "Bamboo");
            run2.put("suit2", "Crack");
            run2.put("suit3", "Spot");
            listOfMixedRuns.add(run2);

            Map<String, String> run3 = new HashMap<String, String>(run);
            run3.put("suit1", "Spot");
            run3.put("suit2", "Bamboo");
            run3.put("suit3", "Crack");
            listOfMixedRuns.add(run3);

            Map<String, String> run4 = new HashMap<String, String>(run);
            run4.put("suit1", "Spot");
            run4.put("suit2", "Crack");
            run4.put("suit3", "Bamboo");
            listOfMixedRuns.add(run4);

            Map<String, String> run5 = new HashMap<String, String>(run);
            run5.put("suit1", "Crack");
            run5.put("suit2", "Spot");
            run5.put("suit3", "Bamboo");
            listOfMixedRuns.add(run5);

            Map<String, String> run6 = new HashMap<String, String>(run);
            run6.put("suit1", "Crack");
            run6.put("suit2", "Bamboo");
            run6.put("suit3", "Spot");
            listOfMixedRuns.add(run6);
        }

        return listOfMixedRuns;
    }
}
