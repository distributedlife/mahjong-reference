package com.distributedlife.mahjong.hand.matching;

import com.distributedlife.mahjong.hand.filtering.MatchingHandFilter;
import com.distributedlife.mahjong.hand.sorting.MatchingHandSorter;

import java.util.List;

public class MahJongHandMatcher {
    private final MatchingHandSorter sorter;
    private final MatchingHandFilter filter;

    public MahJongHandMatcher(MatchingHandSorter sorter, MatchingHandFilter filter) {
        this.sorter = sorter;
        this.filter = filter;
    }

    public List<Match> getMatches(List<String> tilesInHand) {
        return sorter.sortByMostMatches(
                filter.keepOnlyBest(
                        filter.findAllHandsWithAtLeastOneMatchShould(tilesInHand)
                )
        );
    }
}
