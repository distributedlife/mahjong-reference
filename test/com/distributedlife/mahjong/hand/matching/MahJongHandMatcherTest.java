package com.distributedlife.mahjong.hand.matching;

import com.distributedlife.mahjong.hand.Hand;
import com.distributedlife.mahjong.hand.filtering.MatchingHandFilter;
import com.distributedlife.mahjong.hand.sorting.MatchingHandSorter;
import com.distributedlife.mahjong.reference.HandLibrary;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MahJongHandMatcherTest {
    @Test
    public void shouldMatchEntriesInTheHandLibrary() {
        List<String> hand = new ArrayList<String>();
        hand.add("1 Bamboo");

        HandLibrary handLibrary = mock(HandLibrary.class);
        List<Hand> candidateHands = new ArrayList<Hand>();
        candidateHands.add(new Hand("Run, Pung and a Pair", runPungAndAPairBamboo()));
        when(handLibrary.getHands()).thenReturn(candidateHands);

        MahJongHandMatcher mahJongHandMatcher = new MahJongHandMatcher(new MatchingHandSorter(), new MatchingHandFilter(handLibrary));
        List<Match> matches = mahJongHandMatcher.getMatches(hand);
        assertThat(matches.get(0).getName(), is("Run, Pung and a Pair"));
        assertThat(matches.get(0).getCount(), is(1));
        assertThat(matches.size(), is(1));
    }

    @Test
    public void shouldPickTheBestMatchForAHand() {
        List<String> hand = new ArrayList<String>();
        hand.add("1 Bamboo");
        hand.add("2 Spot");
        hand.add("2 Spot");

        HandLibrary handLibrary = mock(HandLibrary.class);
        List<Hand> candidateHands = new ArrayList<Hand>();
        candidateHands.add(new Hand("Run, Pung and a Pair", runPungAndAPairBamboo()));
        candidateHands.add(new Hand("Run, Pung and a Pair", runPungAndAPairSpot()));
        when(handLibrary.getHands()).thenReturn(candidateHands);

        MahJongHandMatcher mahJongHandMatcher = new MahJongHandMatcher(new MatchingHandSorter(), new MatchingHandFilter(handLibrary));
        List<Match> matches = mahJongHandMatcher.getMatches(hand);
        assertThat(matches.get(0).getName(), is("Run, Pung and a Pair"));
        assertThat(matches.get(0).getCount(), is(2));
        assertThat(matches.size(), is(1));
    }

    @Test
    public void shouldMatchEachDifferentHandOrderedByMatchCount() {
        List<String> hand = new ArrayList<String>();
        hand.add("2 Spot");
        hand.add("8 Spot");
        hand.add("8 Spot");

        HandLibrary handLibrary = mock(HandLibrary.class);
        List<Hand> candidateHands = new ArrayList<Hand>();
        candidateHands.add(new Hand("Run, Pung and a Pair", runPungAndAPairSpot()));
        candidateHands.add(new Hand("Gates of Heaven", gatesOfHeaven()));
        when(handLibrary.getHands()).thenReturn(candidateHands);

        MahJongHandMatcher mahJongHandMatcher = new MahJongHandMatcher(new MatchingHandSorter(), new MatchingHandFilter(handLibrary));
        List<Match> matches = mahJongHandMatcher.getMatches(hand);
        assertThat(matches.size(), is(2));

        assertThat(matches.get(0).getName(), is("Gates of Heaven"));
        assertThat(matches.get(0).getCount(), is(3));
        assertThat(matches.get(1).getName(), is("Run, Pung and a Pair"));
        assertThat(matches.get(1).getCount(), is(2));
    }

    private List<String> runPungAndAPairBamboo() {
        ArrayList<String> hand = new ArrayList<String>();
        hand.add("1 Bamboo");
        hand.add("2 Bamboo");
        hand.add("3 Bamboo");
        hand.add("4 Bamboo");
        hand.add("5 Bamboo");
        hand.add("6 Bamboo");
        hand.add("7 Bamboo");
        hand.add("8 Bamboo");
        hand.add("9 Bamboo");

        hand.add("1 Bamboo");
        hand.add("1 Bamboo");
        hand.add("1 Bamboo");

        hand.add("2 Bamboo");
        hand.add("2 Bamboo");

        return hand;
    }

    private List<String> runPungAndAPairSpot() {
        ArrayList<String> hand = new ArrayList<String>();
        hand.add("1 Spot");
        hand.add("2 Spot");
        hand.add("3 Spot");
        hand.add("4 Spot");
        hand.add("5 Spot");
        hand.add("6 Spot");
        hand.add("7 Spot");
        hand.add("8 Spot");
        hand.add("9 Spot");

        hand.add("1 Spot");
        hand.add("1 Spot");
        hand.add("1 Spot");

        hand.add("2 Spot");
        hand.add("2 Spot");

        return hand;
    }

    private List<String> gatesOfHeaven() {
        ArrayList<String> hand = new ArrayList<String>();
        hand.add("2 Spot");
        hand.add("3 Spot");
        hand.add("4 Spot");
        hand.add("5 Spot");
        hand.add("6 Spot");
        hand.add("7 Spot");
        hand.add("8 Spot");

        hand.add("8 Spot");

        hand.add("1 Spot");
        hand.add("1 Spot");
        hand.add("1 Spot");

        hand.add("9 Spot");
        hand.add("9 Spot");
        hand.add("9 Spot");

        return hand;
    }
}
