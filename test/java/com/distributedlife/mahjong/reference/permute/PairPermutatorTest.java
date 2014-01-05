package com.distributedlife.mahjong.reference.permute;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PairPermutatorTest {
    @Test
    public void shouldCreateAMultiplePermutatorUsingTilesAndQuantityOf2() {
        List<String> tiles = new ArrayList<String>();
        tiles.add("1 bamboo");

        PairPermutator pairPermutator = new PairPermutator(tiles);
        assertThat(pairPermutator.multiples, is(2));
        assertThat(pairPermutator.tilesMultipleIsAllowedIn, is(tiles));
    }
}
