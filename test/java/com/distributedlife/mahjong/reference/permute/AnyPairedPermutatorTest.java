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

public class AnyPairedPermutatorTest {
    @Test
    public void shouldCreateANewPermutationForAnyTileCurrentlyInTheHand() {
        List<String> availableTiles = new ArrayList<String>();
        availableTiles.add("1 Bamboo");
        availableTiles.add("1 Bamboo");

        HandCandidate candidate = new HandCandidate("candidate", availableTiles);
        candidate.setPrimarySuit("Bamboo");
        candidate.add("1 Bamboo");
        Set<HandCandidate> inCandidates = Sets.newSet(candidate);

        AnyPairedPermutator anyPairedPermutator = new AnyPairedPermutator();
        Set<HandCandidate> outCandidates = anyPairedPermutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(1));
        assertThat(new ArrayList<HandCandidate>(outCandidates).get(0).getRequiredTiles(), is(Arrays.asList("1 Bamboo", "1 Bamboo")));
    }

    @Test
    public void shouldCheckEachPermutation() {
        List<String> availableTiles1 = new ArrayList<String>();
        availableTiles1.add("1 Bamboo");
        availableTiles1.add("1 Bamboo");
        HandCandidate candidate1 = new HandCandidate("candidate1", availableTiles1);
        candidate1.setPrimarySuit("Bamboo");
        candidate1.add("1 Bamboo");

        List<String> availableTiles2 = new ArrayList<String>();
        availableTiles2.add("1 Spot");
        availableTiles2.add("1 Spot");
        HandCandidate candidate2 = new HandCandidate("candidate1", availableTiles2);
        candidate2.setPrimarySuit("Spot");
        candidate2.add("1 Spot");
        Set<HandCandidate> inCandidates = Sets.newSet(candidate1, candidate2);

        AnyPairedPermutator anyPairedPermutator = new AnyPairedPermutator();
        Set<HandCandidate> outCandidates = anyPairedPermutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(2));
        assertThat(new ArrayList<HandCandidate>(outCandidates).get(0).getRequiredTiles(), is(Arrays.asList("1 Spot", "1 Spot")));
        assertThat(new ArrayList<HandCandidate>(outCandidates).get(1).getRequiredTiles(), is(Arrays.asList("1 Bamboo", "1 Bamboo")));
    }

    @Test
    public void shouldExcludeCandidatesThatCantFormAPair() {
        List<String> availableTiles = new ArrayList<String>();
        availableTiles.add("1 Bamboo");
        availableTiles.add("2 Bamboo");

        HandCandidate candidate = new HandCandidate("candidate", availableTiles);
        candidate.setPrimarySuit("Bamboo");
        candidate.add("1 Bamboo");
        Set<HandCandidate> inCandidates = Sets.newSet(candidate);

        AnyPairedPermutator anyPairedPermutator = new AnyPairedPermutator();
        Set<HandCandidate> outCandidates = anyPairedPermutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(0));
    }
}
