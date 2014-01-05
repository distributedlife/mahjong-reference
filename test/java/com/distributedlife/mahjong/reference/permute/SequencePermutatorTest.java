package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.HandCandidate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SequencePermutatorTest {
    @Test
    public void shouldOnlyPermuteOneWithAFullRunOfNine() {
        SequencePermutator permutator = new SequencePermutator(1, 9);

        List<HandCandidate> candidates = new ArrayList<HandCandidate>();
        List<String> availableTiles = new ArrayList<String>();
        availableTiles.add("1 Bamboo");
        availableTiles.add("2 Bamboo");
        availableTiles.add("3 Bamboo");
        availableTiles.add("4 Bamboo");
        availableTiles.add("5 Bamboo");
        availableTiles.add("6 Bamboo");
        availableTiles.add("7 Bamboo");
        availableTiles.add("8 Bamboo");
        availableTiles.add("9 Bamboo");

        HandCandidate candidate = new HandCandidate("derp", availableTiles);
        candidate.setPrimarySuit("Bamboo");

        candidates.add(candidate);

        List<HandCandidate> permutations = permutator.permute(candidates);

        assertThat(permutations.size(), is(1));
        assertThat(permutations.get(0).getRequiredTiles().size(), is(9));
        assertThat(availableTiles.size(), is(0));
    }
}
