package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.hand.HandCandidate;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ThirdSuitPermutatorTest {
    @Test
    public void shouldIgnoreCandidatesThatAlreadyHaveAThirdSuit() {
        HandCandidate candidate = mock(HandCandidate.class);
        when(candidate.getSecondSuit()).thenReturn("Spot");
        when(candidate.getThirdSuit()).thenReturn("Bamboo");
        Set<HandCandidate> inCandidates = Sets.newSet(candidate);

        Permutator innerPermutator = mock(Permutator.class);
        when(innerPermutator.permute(inCandidates)).thenReturn(inCandidates);
        ThirdSuitPermutator thirdSuitPermutator = new ThirdSuitPermutator(innerPermutator);

        Set<HandCandidate> outCandidates = thirdSuitPermutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(1));
    }

    @Test
    public void shouldIgnoreCandidatesThatHaveNotSetASecondSuit() {
        HandCandidate candidate = mock(HandCandidate.class);
        when(candidate.getSecondSuit()).thenReturn("");
        when(candidate.getThirdSuit()).thenReturn("");
        Set<HandCandidate> inCandidates = Sets.newSet(candidate);

        Permutator innerPermutator = mock(Permutator.class);
        when(innerPermutator.permute(inCandidates)).thenReturn(inCandidates);
        ThirdSuitPermutator thirdSuitPermutator = new ThirdSuitPermutator(innerPermutator);

        Set<HandCandidate> outCandidates = thirdSuitPermutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(1));
    }

    @Test
    public void shouldSetTheThirdSuitOnEveryCandidateThatDoesNotHaveAThirdSet() {
        HandCandidate candidate = new HandCandidate("derp", new ArrayList<String>());
        candidate.setPrimarySuit("Bamboo");
        candidate.setSecondSuit("Spot");
        Set<HandCandidate> inCandidates = Sets.newSet(candidate);

        Permutator innerPermutator = new UnknownPermutator("derp");
        ThirdSuitPermutator thirdSuitPermutator = new ThirdSuitPermutator(innerPermutator);

        Set<HandCandidate> outCandidates = thirdSuitPermutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(1));
        assertThat(new ArrayList<HandCandidate>(outCandidates).get(0).getPrimarySuit(), is("Bamboo"));
        assertThat(new ArrayList<HandCandidate>(outCandidates).get(0).getSecondSuit(), is("Spot"));
        assertThat(new ArrayList<HandCandidate>(outCandidates).get(0).getThirdSuit(), is("Crack"));
    }

    @Test
    public void shouldCallPermuteOnSuppliedPermutatorWithTheExpandedCandidatesList() {
        HandCandidate candidate = mock(HandCandidate.class);
        when(candidate.getThirdSuit()).thenReturn("Derp");
        when(candidate.getSecondSuit()).thenReturn("Derp");

        Permutator innerPermutator = mock(Permutator.class);
        ThirdSuitPermutator thirdSuitPermutator = new ThirdSuitPermutator(innerPermutator);

        thirdSuitPermutator.permute(Sets.newSet(candidate));

        verify(innerPermutator).permute(anySet());
    }
}
