package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.hand.HandCandidate;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import java.util.Arrays;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CombinationSequencePermutatorTest {
    @Test
    public void shouldReturn1CombinationForALengthOf9() {
        HandCandidate candidate = new HandCandidate("derp", Arrays.asList(
                "1 Bamboo", "2 Bamboo", "3 Bamboo", "4 Bamboo", "5 Bamboo", "6 Bamboo", "7 Bamboo", "8 Bamboo", "9 Bamboo"
        ));
        candidate.setPrimarySuit("Bamboo");
        Set<HandCandidate> inCandidates = Sets.newSet(candidate);

        CombinationSequencePermutator permutator = new CombinationSequencePermutator(9, "1st");
        Set<HandCandidate> outCandidates = permutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(1));
    }

    @Test
    public void shouldReturn2CombinationsForALengthOf8() {
        HandCandidate candidate = new HandCandidate("derp", Arrays.asList(
                "1 Bamboo", "2 Bamboo", "3 Bamboo", "4 Bamboo", "5 Bamboo", "6 Bamboo", "7 Bamboo", "8 Bamboo", "9 Bamboo"
        ));
        candidate.setPrimarySuit("Bamboo");
        Set<HandCandidate> inCandidates = Sets.newSet(candidate);

        CombinationSequencePermutator permutator = new CombinationSequencePermutator(8, "1st");
        Set<HandCandidate> outCandidates = permutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(2));
    }

    @Test
    public void shouldReturn7CombinationsForALengthOf3() {
        HandCandidate candidate = new HandCandidate("derp", Arrays.asList(
                "1 Bamboo", "2 Bamboo", "3 Bamboo", "4 Bamboo", "5 Bamboo", "6 Bamboo", "7 Bamboo", "8 Bamboo", "9 Bamboo"
        ));
        candidate.setPrimarySuit("Bamboo");
        Set<HandCandidate> inCandidates = Sets.newSet(candidate);

        CombinationSequencePermutator permutator = new CombinationSequencePermutator(3, "1st");
        Set<HandCandidate> outCandidates = permutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(7));
    }
}
