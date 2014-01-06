package com.distributedlife.mahjong.hand.matching;

import com.distributedlife.mahjong.game.TileSet;
import com.distributedlife.mahjong.hand.filtering.MatchingHandFilter;
import com.distributedlife.mahjong.hand.sorting.MatchingHandSorter;

import java.util.ArrayList;
import java.util.List;

class MahJongHandMatcher {
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

    public List<Match> getMatchesWithOwnWind(List<String> tilesInHand, TileSet.Winds ownWind) {
        List<String> tilesInHandWithOwnWind = new ArrayList<String>();
        for (String tile : tilesInHand) {
            if (tile.equals(ownWind.toString())) {
                tilesInHandWithOwnWind.add(TileSet.Winds.OwnWind.toString());
            } else {
                tilesInHandWithOwnWind.add(tile);
            }
        }

        return getMatches(tilesInHandWithOwnWind);
    }
}
