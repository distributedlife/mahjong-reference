package com.distributedlife.mahjong.hand.sorting;

import com.distributedlife.mahjong.hand.matching.Match;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatchingHandSorter {
    public List<Match> sortByMostMatches(List<Match> unsortedMatches) {
        List<Match> sortedMatches = new ArrayList<Match>(unsortedMatches);

        Collections.sort(sortedMatches, new MatchComparator());

        return sortedMatches;
    }
}
