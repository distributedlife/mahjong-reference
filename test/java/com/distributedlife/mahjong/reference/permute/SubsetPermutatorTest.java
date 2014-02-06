package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.hand.HandCandidate;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SubsetPermutatorTest {
    @Test
    public void shouldPermuteEachCombinationFromTheMasterSet() {
        SubsetPermutator permutator = new SubsetPermutator(Arrays.asList("1", "2"), 1);

        Set<HandCandidate> inCandidates = Sets.newSet(new HandCandidate("derp", Arrays.asList("1", "2")));
        Set<HandCandidate> outCandidates = permutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(2));
        assertThat(new ArrayList<HandCandidate>(outCandidates).get(0).getRequiredTiles().get(0), is("2"));
        assertThat(new ArrayList<HandCandidate>(outCandidates).get(1).getRequiredTiles().get(0), is("1"));
    }

    @Test
    public void shouldRequireEachItemInTheSet() {
        SubsetPermutator permutator = new SubsetPermutator(Arrays.asList("1", "2"), 2);

        Set<HandCandidate> inCandidates = Sets.newSet(new HandCandidate("derp", Arrays.asList("1", "2")));
        Set<HandCandidate> outCandidates = permutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(1));
        assertThat(new ArrayList<HandCandidate>(outCandidates).get(0).getRequiredTiles().get(0), is("1"));
        assertThat(new ArrayList<HandCandidate>(outCandidates).get(0).getRequiredTiles().get(1), is("2"));
    }

    @Test
    public void shouldDropCandidatesThatCantFormAValidSubset() {
        SubsetPermutator permutator = new SubsetPermutator(Arrays.asList("2"), 1);

        Set<HandCandidate> inCandidates = Sets.newSet(new HandCandidate("derp", Arrays.asList("1", "2")));
        Set<HandCandidate> outCandidates = permutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(1));
        assertThat(new ArrayList<HandCandidate>(outCandidates).get(0).getRequiredTiles().get(0), is("2"));
    }
}
