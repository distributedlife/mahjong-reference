package com.distributedlife.mahjong.reference.hand;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.distributedlife.mahjong.reference.hand.HandCandidate.times;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class HandCandidateTest {
    @Test
    public void shouldBeValidIfItHas14TilesRequired() {
        ArrayList<String> availableTiles = new ArrayList<String>();
        for(int i = 0; i < 15; i++) {
            availableTiles.add("a");
        }
        HandCandidate handCandidate = new HandCandidate("derp", availableTiles);

        handCandidate.add("a", times(13));
        assertFalse(handCandidate.isValid());

        handCandidate.add("a");
        assertTrue(handCandidate.isValid());

        handCandidate.add("a");
        assertFalse(handCandidate.isValid());
    }

    @Test
    public void canAddShouldBeTrueWhenAvailableTilesContainsThatQuantityOfTile() {
        ArrayList<String> availableTiles = new ArrayList<String>();
        availableTiles.add("a");

        HandCandidate handCandidate = new HandCandidate("derp", availableTiles);
        assertTrue(handCandidate.canAdd("a"));
        assertFalse(handCandidate.canAdd("a", times(2)));
        assertFalse(handCandidate.canAdd("b"));
    }

    @Test
    public void addShouldMoveQuantityOfTilesFromAvailableToRequired() {
        ArrayList<String> availableTiles = new ArrayList<String>();
        availableTiles.add("a");

        HandCandidate handCandidate = new HandCandidate("derp", availableTiles);
        handCandidate.add("a");

        assertThat(handCandidate.getRequiredTiles().size(), is(1));
        assertThat(handCandidate.getRequiredTiles().get(0), is("a"));
        assertFalse(handCandidate.canAdd("a"));
    }

    @Test
    public void shouldForkAllValues() {
        List<String> availableTiles = new ArrayList<String>();
        availableTiles.add("1 Bamboo");
        availableTiles.add("2 Bamboo");
        HandCandidate original = new HandCandidate("hello", availableTiles);
        original.setPrimarySuit("Bamboo");
        original.setSecondSuit("Spot");
        original.setThirdSuit("Crack");
        original.add("1 Bamboo");

        assertThat(original.fork().getName(), is("hello"));
        assertThat(original.fork().getPrimarySuit(), is("Bamboo"));
        assertThat(original.fork().getSecondSuit(), is("Spot"));
        assertThat(original.fork().getThirdSuit(), is("Crack"));
        assertThat(original.fork().getRequiredTiles(), is(Arrays.asList("1 Bamboo")));
        assertThat(original.fork().availableTiles, is(Arrays.asList("2 Bamboo")));
    }
}
