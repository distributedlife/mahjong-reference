package com.distributedlife.mahjong.reference.permute;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PairPermutatorTest {
    @Test
    public void shouldCreateAMultiplePermutatorUsingTilesAndQuantityOf2() {
        PairPermutator pairPermutator = new PairPermutator(Arrays.asList("1 Bamboo"));
        assertThat(pairPermutator.multiples, is(2));
        assertThat(pairPermutator.tilesMultipleIsAllowedIn, is(Arrays.asList("1 Bamboo")));
    }
}
