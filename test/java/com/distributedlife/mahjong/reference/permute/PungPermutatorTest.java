package com.distributedlife.mahjong.reference.permute;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PungPermutatorTest {
    @Test
    public void shouldCreateAMultiplePermutatorUsingTilesAndQuantityOf3() {
        List<String> tiles = new ArrayList<String>();
        tiles.add("1 bamboo");

        PungPermutator pungPermutator = new PungPermutator(tiles);
        assertThat(pungPermutator.multiples, is(3));
        assertThat(pungPermutator.tilesMultipleIsAllowedIn, is(tiles));
    }
}
