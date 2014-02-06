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

public class SecondSuitPermutatorTest {
    @Test
    public void shouldIgnoreCandidatesThatAlreadyHaveASecondSuit() {
        HandCandidate candidate = mock(HandCandidate.class);
        when(candidate.getSecondSuit()).thenReturn("Bamboo");
        Set<HandCandidate> inCandidates = Sets.newSet(candidate);

        Permutator innerPermutator = mock(Permutator.class);
        when(innerPermutator.permute(inCandidates)).thenReturn(inCandidates);
        SecondSuitPermutator secondSuitPermutator = new SecondSuitPermutator(innerPermutator);

        Set<HandCandidate> outCandidates = secondSuitPermutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(1));
    }

    @Test
    public void shouldDuplicateEveryCandidateThatDoesNotHaveASecondSuit() {
        HandCandidate candidate = new HandCandidate("derp", new ArrayList<String>());
        candidate.setPrimarySuit("Bamboo");
        Set<HandCandidate> inCandidates = Sets.newSet(candidate);

        Permutator innerPermutator = new UnknownPermutator("derp");
        SecondSuitPermutator secondSuitPermutator = new SecondSuitPermutator(innerPermutator);

        Set<HandCandidate> outCandidates = secondSuitPermutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(2));
        assertThat(new ArrayList<HandCandidate>(outCandidates).get(0).getPrimarySuit(), is("Bamboo"));
        assertThat(new ArrayList<HandCandidate>(outCandidates).get(0).getSecondSuit(), is("Spot"));
        assertThat(new ArrayList<HandCandidate>(outCandidates).get(1).getPrimarySuit(), is("Bamboo"));
        assertThat(new ArrayList<HandCandidate>(outCandidates).get(1).getSecondSuit(), is("Crack"));
    }

    @Test
    public void shouldCallPermuteOnSuppliedPermutatorWithTheExpandedCandidatesList() {
        HandCandidate candidate = mock(HandCandidate.class);
        when(candidate.getSecondSuit()).thenReturn("Derp");

        Permutator innerPermutator = mock(Permutator.class);
        SecondSuitPermutator secondSuitPermutator = new SecondSuitPermutator(innerPermutator);

        secondSuitPermutator.permute(Sets.newSet(candidate));

        verify(innerPermutator).permute(anySet());
    }
}
