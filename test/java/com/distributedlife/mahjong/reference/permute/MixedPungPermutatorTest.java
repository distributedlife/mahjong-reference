package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.hand.HandCandidate;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MixedPungPermutatorTest {
    @Test
    public void shouldCreateAMixedPungForEachTile() {
        List<String> tiles = Arrays.asList("1");
        MixedPungPermutator pungPermutator = new MixedPungPermutator(tiles);

        List<String> availableTiles = new ArrayList<String>();
        availableTiles.add("1 Bamboo");
        availableTiles.add("1 Spot");
        availableTiles.add("1 Crack");

        HandCandidate candidate = new HandCandidate("a", availableTiles);

        Set<HandCandidate> candidates = new HashSet<HandCandidate>();
        candidates.add(candidate);

        Set<HandCandidate> outCandidates = pungPermutator.permute(candidates);

        assertThat(outCandidates.size(), is(1));
        assertThat(new ArrayList<HandCandidate>(outCandidates).get(0).getRequiredTiles(), is(Arrays.asList("1 Bamboo", "1 Crack", "1 Spot")));
    }

    @Test
    public void shouldExcludeHandsThatCantAddAllThreeTiles() {
        List<String> tiles = Arrays.asList("1");
        MixedPungPermutator pungPermutator = new MixedPungPermutator(tiles);

        List<String> availableTiles = new ArrayList<String>();
        availableTiles.add("1 Bamboo");
        availableTiles.add("1 Spot");

        HandCandidate candidate = new HandCandidate("a", availableTiles);

        Set<HandCandidate> candidates = new HashSet<HandCandidate>();
        candidates.add(candidate);

        Set<HandCandidate> outCandidates = pungPermutator.permute(candidates);

        assertThat(outCandidates.size(), is(0));
    }
}
