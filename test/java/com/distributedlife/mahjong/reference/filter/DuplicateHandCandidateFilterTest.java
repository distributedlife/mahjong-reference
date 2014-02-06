package com.distributedlife.mahjong.reference.filter;

import com.distributedlife.mahjong.reference.hand.HandCandidate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DuplicateHandCandidateFilterTest {
    @Test
    public void shouldRemoveExactDuplicates() {
        List<String> availableTiles = new ArrayList<String>();
        availableTiles.add("a");
        availableTiles.add("b");
        HandCandidate a = new HandCandidate("hand", availableTiles);
        HandCandidate b = new HandCandidate("hand", availableTiles);
        a.add("a");
        b.add("a");

        DuplicateHandCandidateFilter filter = new DuplicateHandCandidateFilter();

        List<HandCandidate> inCandidates = Arrays.asList(a, b);

        List<HandCandidate> outCandidates = filter.apply(inCandidates);
        assertThat(outCandidates.size(), is(1));
        assertThat(outCandidates.get(0).getRequiredTiles(), is(Arrays.asList("a")));
    }

    @Test
    public void shouldNotRemoveSingleEntries() {
        List<String> availableTiles = new ArrayList<String>();
        availableTiles.add("a");
        availableTiles.add("b");
        HandCandidate a = new HandCandidate("hand", availableTiles);
        HandCandidate b = new HandCandidate("hand", availableTiles);
        a.add("a");
        b.add("b");

        List<HandCandidate> inCandidates = Arrays.asList(a, b);

        DuplicateHandCandidateFilter filter = new DuplicateHandCandidateFilter();
        List<HandCandidate> outCandidates = filter.apply(inCandidates);
        assertThat(outCandidates.size(), is(2));
        assertThat(outCandidates.get(0).getRequiredTiles(), is(Arrays.asList("a")));
        assertThat(outCandidates.get(1).getRequiredTiles(), is(Arrays.asList("b")));
    }
}
