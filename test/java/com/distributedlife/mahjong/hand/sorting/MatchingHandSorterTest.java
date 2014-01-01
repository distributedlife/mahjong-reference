package com.distributedlife.mahjong.hand.sorting;

import com.distributedlife.mahjong.hand.matching.Match;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MatchingHandSorterTest {
    @Test
    public void shouldSortByMostMatchesFirst() {
        MatchingHandSorter matchingHandSorter = new MatchingHandSorter();

        List<Match> unsortedMatches = new ArrayList<Match>();
        unsortedMatches.add(new Match("should be second", 5));
        unsortedMatches.add(new Match("should be first", 10));

        List<Match> sortedMatches = matchingHandSorter.sortByMostMatches(unsortedMatches);

        assertThat(sortedMatches.get(0).getName(), is("should be first"));
        assertThat(sortedMatches.get(1).getName(), is("should be second"));
    }

    @Test
    public void shouldSortAlphabeticallySecond() {
        MatchingHandSorter matchingHandSorter = new MatchingHandSorter();

        List<Match> unsortedMatches = new ArrayList<Match>();
        unsortedMatches.add(new Match("samuel", 10));
        unsortedMatches.add(new Match("sammy", 10));

        List<Match> sortedMatches = matchingHandSorter.sortByMostMatches(unsortedMatches);

        assertThat(sortedMatches.get(0).getName(), is("sammy"));
        assertThat(sortedMatches.get(1).getName(), is("samuel"));
    }
}
