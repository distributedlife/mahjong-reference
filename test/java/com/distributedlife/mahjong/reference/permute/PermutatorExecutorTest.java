package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.hand.HandCandidate;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class PermutatorExecutorTest {
    @Test
    public void shouldCallExecuteOnEachPermutatorPassingTheOutputOfThePriorIntoTheNext() {
        Set<HandCandidate> outCandidates = Sets.newSet(mock(HandCandidate.class));

        Permutator permutator1 = mock(Permutator.class);
        when(permutator1.permute(anySet())).thenReturn(outCandidates);
        Permutator permutator2 = mock(Permutator.class);

        List<Permutator> permutators = new ArrayList<Permutator>();
        permutators.add(permutator1);
        permutators.add(permutator2);

        PermutatorExecutor executor = new PermutatorExecutor();
        executor.runPermutatorsOnCandidate(mock(HandCandidate.class), permutators);

        verify(permutator1).permute(anySet());
        verify(permutator2).permute(outCandidates);
    }

    @Test
    public void shouldReturnTheOutputOfTheLastPermutator() {
        Permutator permutator1 = mock(Permutator.class);
        Permutator permutator2 = mock(Permutator.class);
        HandCandidate outCandidate1 = mock(HandCandidate.class);
        HandCandidate outCandidate2 = mock(HandCandidate.class);
        when(permutator1.permute(anySet())).thenReturn(Sets.newSet(outCandidate1, outCandidate2));
        when(permutator2.permute(anySet())).thenReturn(Sets.newSet(outCandidate2));

        List<Permutator> permutators = new ArrayList<Permutator>();
        permutators.add(permutator1);
        permutators.add(permutator2);

        PermutatorExecutor executor = new PermutatorExecutor();
        Set<HandCandidate> outCandidates = executor.runPermutatorsOnCandidate(mock(HandCandidate.class), permutators);

        assertThat(outCandidates.size(), is(1));
        assertThat(new ArrayList<HandCandidate>(outCandidates).get(0), is(outCandidate2));
    }
}
