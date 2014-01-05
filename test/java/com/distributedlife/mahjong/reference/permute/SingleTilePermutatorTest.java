package com.distributedlife.mahjong.reference.permute;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SingleTilePermutatorTest {
    @Test
    public void shouldCreateAMultiplePermutatorUsingTilesAndQuantityOf1() {
        SingleTilePermutator singleTilePermutator = new SingleTilePermutator("1 Bamboo", "1st");
        assertThat(singleTilePermutator.multiples, is(1));
        assertThat(singleTilePermutator.tilesMultipleIsAllowedIn, is(Arrays.asList("1 Bamboo")));
    }
}
