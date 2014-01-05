package com.distributedlife.mahjong.hand.filtering;

import com.distributedlife.mahjong.hand.Hand;
import com.distributedlife.mahjong.hand.matching.Match;

import java.util.ArrayList;
import java.util.List;

public class MatchingHandFilter {
    private static final int NOT_FOUND = -1;
    private final List<Hand> handLibrary;

    public MatchingHandFilter(List<Hand> handLibrary) {
        this.handLibrary = handLibrary;
    }

    public List<Match> keepOnlyBest(List<Match> unfilteredMatches) {
        List<Match> filteredMatches = new ArrayList<Match>();

        for (Match match : unfilteredMatches) {
            int position = getIndexOfHand(match.getName(), filteredMatches);
            if (position == NOT_FOUND) {
                filteredMatches.add(match);
            } else {
                if (match.getCount() > filteredMatches.get(position).getCount()) {
                    filteredMatches.remove(position);
                    filteredMatches.add(match);
                }
            }
        }

        return filteredMatches;
    }

    public List<Match> findAllHandsWithAtLeastOneMatchShould(List<String> tilesInHand) {
        List<Match> allMatches = new ArrayList<Match>();

        for (Hand potentialHand : handLibrary) {
            List<String> tilesLeftInHand = new ArrayList<String>(tilesInHand);

            int numberOfMatchingTiles = 0;
            for (String requiredTile : potentialHand.getRequiredTiles()) {
                if(tilesLeftInHand.contains(requiredTile)) {
                    numberOfMatchingTiles++;
                    tilesLeftInHand.remove(requiredTile);
                }
            }

            if (numberOfMatchingTiles > 0) {
                allMatches.add(new Match(potentialHand.getName(), numberOfMatchingTiles));
            }
        }

        return allMatches;
    }

    private static int getIndexOfHand(String name, List<Match> matches) {
        for (Match match : matches) {
            if (match.getName().equals(name)) {
                return matches.indexOf(match);
            }
        }

        return -1;
    }
}
