package com.distributedlife.mahjong.hand.filtering;

import com.distributedlife.mahjong.hand.Hand;
import com.distributedlife.mahjong.hand.matching.Match;
import com.distributedlife.mahjong.reference.HandLibrary;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MatchingHandFilterTest {
    @Test
    public void keepOnlyBestShouldFilterOutDuplicatesKeepingHighestCount() {
        MatchingHandFilter matchingHandFilter = new MatchingHandFilter(mock(HandLibrary.class));
        List<Match> unfilteredMatches = new ArrayList<Match>();
        unfilteredMatches.add(new Match("Best", 10));
        unfilteredMatches.add(new Match("Best", 9));

        List<Match> matches = matchingHandFilter.keepOnlyBest(unfilteredMatches);

        assertThat(matches.size(), is(1));
        assertThat(matches.get(0).getName(), is("Best"));
        assertThat(matches.get(0).getCount(), is(10));
    }

    @Test
    public void keepOnlyBestShouldPickFirstInCaseOfATie() {
        MatchingHandFilter matchingHandFilter = new MatchingHandFilter(mock(HandLibrary.class));
        List<Match> unfilteredMatches = new ArrayList<Match>();
        unfilteredMatches.add(new Match("Best", 10));
        unfilteredMatches.add(new Match("Best", 10));

        List<Match> matches = matchingHandFilter.keepOnlyBest(unfilteredMatches);

        assertThat(matches.size(), is(1));
        assertThat(matches.get(0).getName(), is("Best"));
        assertThat(matches.get(0).getCount(), is(10));
    }

    @Test
    public void findAllHandsWithAtLeastOneMatchShouldReturnAllHandsFromLibraryWhereCountIsOver1() {
        List<Hand> potentialHands = new ArrayList<Hand>();
        List<String> firstHand = new ArrayList<String>();
        firstHand.add("1 Bamboo");
        potentialHands.add(new Hand("First", firstHand));
        List<String> secondHand = new ArrayList<String>();
        secondHand.add("1 Bamboo");
        potentialHands.add(new Hand("Second", secondHand));
        List<String> thirdHand = new ArrayList<String>();
        thirdHand.add("1 Spot");
        potentialHands.add(new Hand("Third", thirdHand));

        HandLibrary handLibrary = mock(HandLibrary.class);
        when(handLibrary.getHands()).thenReturn(potentialHands);

        List<String> hand = new ArrayList<String>();
        hand.add("1 Bamboo");

        MatchingHandFilter matchingHandFilter = new MatchingHandFilter(handLibrary);
        List<Match> matches = matchingHandFilter.findAllHandsWithAtLeastOneMatchShould(hand);

        assertThat(matches.size(), is(2));
        assertThat(matches.get(0).getName(), is("First"));
        assertThat(matches.get(0).getCount(), is(1));

        assertThat(matches.get(1).getName(), is("Second"));
        assertThat(matches.get(1).getCount(), is(1));
    }
}
