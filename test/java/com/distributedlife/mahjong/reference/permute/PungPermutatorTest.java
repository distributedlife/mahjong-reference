package com.distributedlife.mahjong.reference.permute;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PungPermutatorTest {
    @Test
    public void shouldCreateAMultiplePermutatorUsingTilesAndQuantityOf3() {
        PungPermutator pungPermutator = new PungPermutator(Arrays.asList("1 Bamboo"), "1st");
        assertThat(pungPermutator.multiples, is(3));
        assertThat(pungPermutator.tilesMultipleIsAllowedIn, is(Arrays.asList("1 Bamboo")));
    }
}
