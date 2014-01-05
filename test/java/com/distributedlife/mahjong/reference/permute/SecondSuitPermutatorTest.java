package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.hand.HandCandidate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class SecondSuitPermutatorTest {
    @Test
    public void shouldIgnoreCandidatesThatAlreadyHaveASecondSuit() {
        HandCandidate candidate = mock(HandCandidate.class);
        when(candidate.getSecondSuit()).thenReturn("Bamboo");
        List<HandCandidate> inCandidates = Arrays.asList(candidate);

        Permutator innerPermutator = mock(Permutator.class);
        when(innerPermutator.permute(inCandidates)).thenReturn(inCandidates);
        SecondSuitPermutator secondSuitPermutator = new SecondSuitPermutator(innerPermutator);

        List<HandCandidate> outCandidates = secondSuitPermutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(1));
    }

    @Test
    public void shouldDuplicateEveryCandidateThatDoesNotHaveASecondSuit() {
        HandCandidate candidate = new HandCandidate("derp", new ArrayList<String>());
        candidate.setPrimarySuit("Bamboo");
        List<HandCandidate> inCandidates = Arrays.asList(candidate);

        Permutator innerPermutator = new UnknownPermutator("derp");
        SecondSuitPermutator secondSuitPermutator = new SecondSuitPermutator(innerPermutator);

        List<HandCandidate> outCandidates = secondSuitPermutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(2));
        assertThat(outCandidates.get(0).getPrimarySuit(), is("Bamboo"));
        assertThat(outCandidates.get(0).getSecondSuit(), is("Spot"));
        assertThat(outCandidates.get(1).getPrimarySuit(), is("Bamboo"));
        assertThat(outCandidates.get(1).getSecondSuit(), is("Crack"));
    }

    @Test
    public void shouldCallPermuteOnSuppliedPermutatorWithTheExpandedCandidatesList() {
        HandCandidate candidate = mock(HandCandidate.class);
        when(candidate.getSecondSuit()).thenReturn("Derp");

        Permutator innerPermutator = mock(Permutator.class);
        SecondSuitPermutator secondSuitPermutator = new SecondSuitPermutator(innerPermutator);

        secondSuitPermutator.permute(Arrays.asList(candidate));

        verify(innerPermutator).permute(anyList());
    }
}
