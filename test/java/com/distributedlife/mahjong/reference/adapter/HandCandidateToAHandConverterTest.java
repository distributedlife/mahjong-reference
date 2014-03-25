package com.distributedlife.mahjong.reference.adapter;

import com.distributedlife.mahjong.reference.hand.Hand;
import com.distributedlife.mahjong.reference.hand.HandCandidate;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.distributedlife.mahjong.reference.data.TileSet.Tile;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HandCandidateToAHandConverterTest {
    @Test
    public void shouldConvertAllHandCandidatesToHands() {
        HandCandidateToAHandConverter converter = new HandCandidateToAHandConverter();

        HandCandidate candidate1 = mock(HandCandidate.class);
        when(candidate1.getName()).thenReturn("derp");
        when(candidate1.getRequiredTiles()).thenReturn(Arrays.asList("1 Bamboo", "2 Bamboo", "White"));

        HandCandidate candidate2 = mock(HandCandidate.class);
        when(candidate2.getName()).thenReturn("herp");
        when(candidate2.getRequiredTiles()).thenReturn(Arrays.asList("1 Bamboo"));

        List<HandCandidate> candidates = Arrays.asList(candidate1, candidate2);
        List<Hand> hands = converter.convert(candidates);

        assertThat(hands.size(), is(2));
        assertThat(hands.get(0).getName(), is("derp"));
        assertThat(hands.get(0).getPart1(), is(Tile.B1.v + Tile.B2.v + Tile.WD.v));
        assertThat(hands.get(0).getPart2(), is(0L));
        assertThat(hands.get(0).getPart3(), is(0L));
        assertThat(hands.get(0).getPart4(), is(0L));
        assertThat(hands.get(1).getName(), is("herp"));
        assertThat(hands.get(1).getPart1(), is(Tile.B1.v));
        assertThat(hands.get(1).getPart2(), is(0L));
        assertThat(hands.get(1).getPart3(), is(0L));
        assertThat(hands.get(1).getPart4(), is(0L));
    }
}
