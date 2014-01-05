package com.distributedlife.mahjong.reference;

import com.distributedlife.mahjong.game.TileSet;
import com.distributedlife.mahjong.hand.Hand;
import com.distributedlife.mahjong.helpers.Json;
import com.distributedlife.mahjong.json.JsonToHandDefinition;
import com.distributedlife.mahjong.reference.permute.PermutatorBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HandLibraryBuilderTest {
    @Test
    public void shouldBuildEachPermutationOfTheRunPungAndPairHand() throws IOException {
        System.out.println(Json.loadFromResource().toString());

        HandLibraryBuilder builder = new HandLibraryBuilder(new TileSet(), new JsonToHandDefinition(new PermutatorBuilder()).getHandDefinitions(Json.loadFromResource()));

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(216));
    }

}
