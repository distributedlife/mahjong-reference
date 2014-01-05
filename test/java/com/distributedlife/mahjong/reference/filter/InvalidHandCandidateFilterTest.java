package com.distributedlife.mahjong.reference.filter;

import com.distributedlife.mahjong.hand.Hand;
import com.distributedlife.mahjong.reference.hand.HandCandidate;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InvalidHandCandidateFilterTest {
    @Test
    public void shouldFilterAllInvalidCandidates() {
        InvalidHandCandidateFilter filter = new InvalidHandCandidateFilter();
        HandCandidate candidate1 = mock(HandCandidate.class);
        when(candidate1.isValid()).thenReturn(false);
        HandCandidate candidate2 = mock(HandCandidate.class);
        when(candidate2.getName()).thenReturn("include");
        when(candidate2.isValid()).thenReturn(true);

        List<HandCandidate> inCandidates = Arrays.asList(candidate1, candidate2);

        List<Hand> outCandidates = filter.apply(inCandidates);
        assertThat(outCandidates.size(), is(1));
        assertThat(outCandidates.get(0).getName(), is("include"));
    }
}
