package com.distributedlife.mahjong.reference;

import com.distributedlife.mahjong.game.TileSet;
import com.distributedlife.mahjong.hand.Hand;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HandLibraryBuilderTest {
    @Test
    public void shouldBuildEachPermutationOfTheRunPungAndPairHand() {
        HandLibraryBuilder builder = new HandLibraryBuilder(new TileSet());

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(216));
    }
}
