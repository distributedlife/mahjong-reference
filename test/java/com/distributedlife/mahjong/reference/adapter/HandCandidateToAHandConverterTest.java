package com.distributedlife.mahjong.reference.adapter;

import com.distributedlife.mahjong.reference.hand.Hand;
import com.distributedlife.mahjong.reference.hand.HandCandidate;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
        when(candidate1.getRequiredTiles()).thenReturn(Arrays.asList("1", "2", "White"));

        HandCandidate candidate2 = mock(HandCandidate.class);
        when(candidate2.getName()).thenReturn("herp");
        when(candidate2.getRequiredTiles()).thenReturn(Arrays.asList("1"));

        List<HandCandidate> candidates = Arrays.asList(candidate1, candidate2);
        List<Hand> hands = converter.convert(candidates);

        assertThat(hands.size(), is(2));
        assertThat(hands.get(0).getName(), is("derp"));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList("1", "2", "White")));
        assertThat(hands.get(1).getName(), is("herp"));
        assertThat(hands.get(1).getRequiredTiles(), is(Arrays.asList("1")));
    }
}
