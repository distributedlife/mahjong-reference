package com.distributedlife.mahjong.reference.filter;

import com.distributedlife.mahjong.reference.hand.HandCandidate;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DuplicateHandCandidateFilterTest {
    @Test
    public void shouldRemoveExactDuplicates() {
        DuplicateHandCandidateFilter filter = new DuplicateHandCandidateFilter();
        HandCandidate candidate1 = mock(HandCandidate.class);
        when(candidate1.getRequiredTiles()).thenReturn(Arrays.asList("a", "b"));
        HandCandidate candidate2 = mock(HandCandidate.class);
        when(candidate2.getRequiredTiles()).thenReturn(Arrays.asList("a", "b"));

        List<HandCandidate> inCandidates = Arrays.asList(candidate1, candidate2);

        List<HandCandidate> outCandidates = filter.apply(inCandidates);
        assertThat(outCandidates.size(), is(1));
        assertThat(outCandidates.get(0).getRequiredTiles(), is(Arrays.asList("a", "b")));
    }

    @Test
    public void shouldRemoveSemanticDuplicates() {
        DuplicateHandCandidateFilter filter = new DuplicateHandCandidateFilter();
        HandCandidate candidate1 = mock(HandCandidate.class);
        when(candidate1.getRequiredTiles()).thenReturn(Arrays.asList("a", "b"));
        HandCandidate candidate2 = mock(HandCandidate.class);
        when(candidate2.getRequiredTiles()).thenReturn(Arrays.asList("b", "a"));

        List<HandCandidate> inCandidates = Arrays.asList(candidate1, candidate2);

        List<HandCandidate> outCandidates = filter.apply(inCandidates);
        assertThat(outCandidates.size(), is(1));
        assertThat(outCandidates.get(0).getRequiredTiles(), is(Arrays.asList("a", "b")));
    }

    @Test
    public void shouldNotRemoveSingleEntries() {
        DuplicateHandCandidateFilter filter = new DuplicateHandCandidateFilter();
        HandCandidate candidate1 = mock(HandCandidate.class);
        when(candidate1.getRequiredTiles()).thenReturn(Arrays.asList("a", "b"));
        HandCandidate candidate2 = mock(HandCandidate.class);
        when(candidate2.getRequiredTiles()).thenReturn(Arrays.asList("b", "c"));

        List<HandCandidate> inCandidates = Arrays.asList(candidate1, candidate2);

        List<HandCandidate> outCandidates = filter.apply(inCandidates);
        assertThat(outCandidates.size(), is(2));
        assertThat(outCandidates.get(0).getRequiredTiles(), is(Arrays.asList("a", "b")));
        assertThat(outCandidates.get(1).getRequiredTiles(), is(Arrays.asList("b", "c")));
    }
}
