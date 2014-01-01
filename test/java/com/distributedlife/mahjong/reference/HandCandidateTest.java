package com.distributedlife.mahjong.reference;

import org.junit.Test;

import java.util.ArrayList;

import static com.distributedlife.mahjong.reference.HandCandidate.times;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
}
