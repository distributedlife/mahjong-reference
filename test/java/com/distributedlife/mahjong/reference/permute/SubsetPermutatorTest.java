package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.hand.HandCandidate;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SubsetPermutatorTest {
    @Test
    public void shouldPermuteEachCombinationFromTheMasterSet() {
        SubsetPermutator permutator = new SubsetPermutator(Arrays.asList("1", "2"), 1);

        List<HandCandidate> inCandidates = Arrays.asList(new HandCandidate("derp", Arrays.asList("1", "2")));
        List<HandCandidate> outCandidates = permutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(2));
        assertThat(outCandidates.get(0).getRequiredTiles().get(0), is("1"));
        assertThat(outCandidates.get(1).getRequiredTiles().get(0), is("2"));
    }

    @Test
    public void shouldRequireEachItemInTheSet() {
        SubsetPermutator permutator = new SubsetPermutator(Arrays.asList("1", "2"), 2);

        List<HandCandidate> inCandidates = Arrays.asList(new HandCandidate("derp", Arrays.asList("1", "2")));
        List<HandCandidate> outCandidates = permutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(2));
        assertThat(outCandidates.get(0).getRequiredTiles().get(0), is("1"));
        assertThat(outCandidates.get(0).getRequiredTiles().get(1), is("2"));

        assertThat(outCandidates.get(1).getRequiredTiles().get(0), is("2"));
        assertThat(outCandidates.get(1).getRequiredTiles().get(1), is("1"));
    }

    @Test
    public void shouldDropCandidatesThatCantFormAValidSubset() {
        SubsetPermutator permutator = new SubsetPermutator(Arrays.asList("2"), 1);

        List<HandCandidate> inCandidates = Arrays.asList(new HandCandidate("derp", Arrays.asList("1", "2")));
        List<HandCandidate> outCandidates = permutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(1));
        assertThat(outCandidates.get(0).getRequiredTiles().get(0), is("2"));
    }
}
