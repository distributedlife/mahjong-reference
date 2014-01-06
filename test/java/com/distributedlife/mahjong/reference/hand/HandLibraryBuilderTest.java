package com.distributedlife.mahjong.reference.hand;

import com.distributedlife.mahjong.game.TileSet;
import com.distributedlife.mahjong.helpers.Json;
import com.distributedlife.mahjong.json.JsonToHandDefinition;
import com.distributedlife.mahjong.json.JsonToPermutatorOptionsConverter;
import com.distributedlife.mahjong.reference.filter.InvalidHandCandidateFilter;
import com.distributedlife.mahjong.reference.permute.PermutatorBuilder;
import com.distributedlife.mahjong.reference.permute.PermutatorExecutor;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HandLibraryBuilderTest {
    private final InvalidHandCandidateFilter filter = new InvalidHandCandidateFilter();
    private final PermutatorExecutor permutatorExecutor = new PermutatorExecutor();
    private final TileSet tileSet = new TileSet();
    private final JsonToPermutatorOptionsConverter jsonToPermutatorConverter = new JsonToPermutatorOptionsConverter();

    @Test
    public void buildAllHands() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/all.json")),
                filter,
                permutatorExecutor
        );

        assertThat(builder.buildAll().size(), is(876));
    }

    @Test
    public void runPungAndAPair() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/runPungAndAPair.json")),
                filter,
                permutatorExecutor
        );

        assertThat(builder.buildAll().size(), is(216));
    }

    @Test
    public void gatesOfHeaven() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/gatesOfHeaven.json")),
                filter,
                permutatorExecutor
        );

        assertThat(builder.buildAll().size(), is(21));
    }

    @Test
    public void confusedGates() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/confusedGates.json")),
                filter,
                permutatorExecutor
        );

        assertThat(builder.buildAll().size(), is(42));
        assertThat(builder.buildAll().get(0).getRequiredTiles(), is(Arrays.asList(
                "2 Bamboo", "3 Bamboo", "4 Bamboo", "5 Bamboo", "6 Bamboo", "7 Bamboo", "8 Bamboo",
                "2 Bamboo",
                "1 Spot", "1 Spot", "1 Spot",
                "9 Crack", "9 Crack", "9 Crack"
        )));
    }

    @Test
    public void wrigglySnake() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/wrigglySnake.json")),
                filter,
                permutatorExecutor
        );

        assertThat(builder.buildAll().size(), is(39));
        assertThat(builder.buildAll().get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "2 Bamboo", "3 Bamboo", "4 Bamboo", "5 Bamboo", "6 Bamboo", "7 Bamboo", "8 Bamboo", "9 Bamboo",
                "North", "West", "East", "South",
                "1 Bamboo"
        )));
    }

    @Test
    public void hachiBan() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/hachiBan.json")),
                filter,
                permutatorExecutor
        );

        assertThat(builder.buildAll().size(), is(504));
        assertThat(builder.buildAll().get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "2 Bamboo", "3 Bamboo", "4 Bamboo", "5 Bamboo", "6 Bamboo", "7 Bamboo", "8 Bamboo",
                "North", "North",
                "North", "North",
                "East", "East"
        )));
    }

    @Test
    public void guardianWinds() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/guardianWinds.json")),
                filter,
                permutatorExecutor
        );

        assertThat(builder.buildAll().size(), is(36));
    }

    @Test
    public void guardianDragons() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/guardianDragons.json")),
                filter,
                permutatorExecutor
        );

        assertThat(builder.buildAll().size(), is(18));
    }
}