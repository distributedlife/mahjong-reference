package com.distributedlife.mahjong.reference;

import com.distributedlife.mahjong.game.TileSet;
import com.distributedlife.mahjong.helpers.Json;
import com.distributedlife.mahjong.json.JsonToHandDefinition;
import com.distributedlife.mahjong.reference.permute.PermutatorBuilder;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HandLibraryBuilderTest {
    private TileSet tileSet = new TileSet();

    @Test
    public void runPungAndAPair() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet, new JsonToHandDefinition(new PermutatorBuilder()).getHandDefinitions(Json.loadFromResource("/runPungAndAPair.json"))
        );

        assertThat(builder.buildAll().size(), is(216));
    }

    @Test
    public void gatesOfHeaven() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet, new JsonToHandDefinition(new PermutatorBuilder()).getHandDefinitions(Json.loadFromResource("/gatesOfHeaven.json"))
        );

        assertThat(builder.buildAll().size(), is(21));
    }
}
