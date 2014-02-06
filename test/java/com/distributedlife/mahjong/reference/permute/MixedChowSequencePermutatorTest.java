package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.hand.HandCandidate;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import java.util.Arrays;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MixedChowSequencePermutatorTest {
    @Test
    public void shouldReturn21Combination() {
        HandCandidate candidate = new HandCandidate("derp", Arrays.asList(
                "1 Bamboo", "2 Bamboo", "3 Bamboo", "4 Bamboo", "5 Bamboo", "6 Bamboo", "7 Bamboo", "8 Bamboo", "9 Bamboo",
                "1 Spot", "2 Spot", "3 Spot", "4 Spot", "5 Spot", "6 Spot", "7 Spot", "8 Spot", "9 Spot",
                "1 Crack", "2 Crack", "3 Crack", "4 Crack", "5 Crack", "6 Crack", "7 Crack", "8 Crack", "9 Crack"
        ));
        Set<HandCandidate> inCandidates = Sets.newSet(candidate);

        MixedChowSequencePermutator permutator = new MixedChowSequencePermutator();
        Set<HandCandidate> outCandidates = permutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(42));
    }
}
