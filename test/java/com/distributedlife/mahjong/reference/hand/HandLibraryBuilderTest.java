package com.distributedlife.mahjong.reference.hand;

import com.distributedlife.mahjong.game.TileSet;
import com.distributedlife.mahjong.helpers.Json;
import com.distributedlife.mahjong.json.JsonToHandDefinition;
import com.distributedlife.mahjong.reference.filter.InvalidHandCandidateFilter;
import com.distributedlife.mahjong.reference.permute.PermutatorBuilder;
import com.distributedlife.mahjong.reference.permute.PermutatorExecutor;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HandLibraryBuilderTest {
    private final InvalidHandCandidateFilter filter = new InvalidHandCandidateFilter();
    private final PermutatorExecutor permutatorExecutor = new PermutatorExecutor();
    private TileSet tileSet = new TileSet();

    @Test
    public void shouldBuildAllHands() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder()).getHandDefinitions(Json.loadFromResource("/all.json")),
                filter,
                permutatorExecutor
        );

        assertThat(builder.buildAll().size(), is(237));
    }

    @Test
    public void runPungAndAPair() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder()).getHandDefinitions(Json.loadFromResource("/runPungAndAPair.json")),
                filter,
                permutatorExecutor
        );

        assertThat(builder.buildAll().size(), is(216));
    }

    @Test
    public void gatesOfHeaven() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder()).getHandDefinitions(Json.loadFromResource("/gatesOfHeaven.json")),
                filter,
                permutatorExecutor
        );

        assertThat(builder.buildAll().size(), is(21));
    }
}
