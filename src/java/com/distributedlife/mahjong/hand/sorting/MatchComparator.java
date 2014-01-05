package com.distributedlife.mahjong.hand.sorting;

import com.distributedlife.mahjong.hand.matching.Match;

import java.util.Comparator;

class MatchComparator implements Comparator<Match> {
    @Override
    public int compare(Match match, Match match2) {
        int countCompare = match2.getCount().compareTo(match.getCount());

        if (countCompare == 0) {
            return (match.getName().compareTo(match2.getName()));
        } else {
            return countCompare;
        }
    }
}
