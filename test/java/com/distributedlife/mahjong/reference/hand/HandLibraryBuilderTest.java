package com.distributedlife.mahjong.reference.hand;

import com.distributedlife.mahjong.game.TileSet;
import com.distributedlife.mahjong.helpers.Json;
import com.distributedlife.mahjong.json.JsonToHandDefinition;
import com.distributedlife.mahjong.json.JsonToPermutatorOptionsConverter;
import com.distributedlife.mahjong.reference.adapter.HandCandidateToAHandConverter;
import com.distributedlife.mahjong.reference.filter.DuplicateHandCandidateFilter;
import com.distributedlife.mahjong.reference.filter.HandCandidateFilter;
import com.distributedlife.mahjong.reference.filter.InvalidHandCandidateFilter;
import com.distributedlife.mahjong.reference.permute.PermutatorBuilder;
import com.distributedlife.mahjong.reference.permute.PermutatorExecutor;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HandLibraryBuilderTest {
    private final PermutatorExecutor permutatorExecutor = new PermutatorExecutor();
    private final TileSet tileSet = new TileSet();
    private final JsonToPermutatorOptionsConverter jsonToPermutatorConverter = new JsonToPermutatorOptionsConverter();
    private HandCandidateToAHandConverter converter = new HandCandidateToAHandConverter();
    private List<HandCandidateFilter> filters;

    @Before
    public void setupFilters() {
        filters = Arrays.asList(new InvalidHandCandidateFilter(), new DuplicateHandCandidateFilter());
    }

    @Test
    public void buildAllHands() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/all.json")),
                filters,
                permutatorExecutor,
                converter);

        assertThat(builder.buildAll().size(), is(1681));
    }

    @Test
    public void runPungAndAPair() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/runPungAndAPair.json")),
                filters,
                permutatorExecutor,
                converter);

        assertThat(builder.buildAll().size(), is(216));
    }

    @Test
    public void gatesOfHeaven() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/gatesOfHeaven.json")),
                filters,
                permutatorExecutor,
                converter);

        assertThat(builder.buildAll().size(), is(21));
    }

    @Test
    public void confusedGates() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/confusedGates.json")),
                filters,
                permutatorExecutor,
                converter);

        assertThat(builder.buildAll().size(), is(42));
        assertThat(builder.buildAll().get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Spot", "1 Spot", "1 Spot",
                "2 Bamboo",
                "2 Bamboo", "3 Bamboo", "4 Bamboo", "5 Bamboo", "6 Bamboo", "7 Bamboo", "8 Bamboo",
                "9 Crack", "9 Crack", "9 Crack"
        )));
    }

    @Test
    public void wrigglySnake() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/wrigglySnake.json")),
                filters,
                permutatorExecutor,
                converter);

        assertThat(builder.buildAll().size(), is(39));
        assertThat(builder.buildAll().get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo",
                "1 Bamboo", "2 Bamboo", "3 Bamboo", "4 Bamboo", "5 Bamboo", "6 Bamboo", "7 Bamboo", "8 Bamboo", "9 Bamboo",
                "East", "North", "South", "West"
        )));
    }

    @Test
    public void hachiBan() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/hachiBan.json")),
                filters,
                permutatorExecutor,
                converter);

        assertThat(builder.buildAll().size(), is(138));
        assertThat(builder.buildAll().get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "2 Bamboo", "3 Bamboo", "4 Bamboo", "5 Bamboo", "6 Bamboo", "7 Bamboo", "8 Bamboo",
                "East", "East",
                "North", "North",
                "North", "North"
        )));
    }

    @Test
    public void guardianWinds() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/guardianWinds.json")),
                filters,
                permutatorExecutor,
                converter);

        assertThat(builder.buildAll().size(), is(36));
    }

    @Test
    public void guardianDragons() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/guardianDragons.json")),
                filters,
                permutatorExecutor,
                converter);

        assertThat(builder.buildAll().size(), is(18));
    }

    @Test
    public void fiveOddHonours() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/fiveOddHonours.json")),
                filters,
                permutatorExecutor,
                converter);

        assertThat(builder.buildAll().size(), is(63));
        assertThat(builder.buildAll().get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "2 Bamboo", "3 Bamboo", "4 Bamboo", "5 Bamboo", "6 Bamboo", "7 Bamboo", "8 Bamboo", "9 Bamboo",
                "East", "North", "Red", "South", "West"
        )));
    }

    @Test
    public void wrigglyDragon() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/wrigglyDragon.json")),
                filters,
                permutatorExecutor,
                converter);

        assertThat(builder.buildAll().size(), is(9));
        assertThat(builder.buildAll().get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "2 Bamboo", "3 Bamboo", "4 Bamboo", "5 Bamboo", "6 Bamboo", "7 Bamboo", "8 Bamboo", "9 Bamboo",
                "Green", "Red", "Red",
                "Red", "White"
        )));
    }

    @Test
    public void grandSequence() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/grandSequence.json")),
                filters,
                permutatorExecutor,
                converter);

        assertThat(builder.buildAll().size(), is(945));
        assertThat(builder.buildAll().get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo",
                "1 Bamboo",
                "1 Bamboo", "2 Bamboo", "3 Bamboo", "4 Bamboo", "5 Bamboo", "6 Bamboo", "7 Bamboo", "8 Bamboo", "9 Bamboo",
                "North", "North", "North"
        )));
    }

    @Test
    public void dragonsGates() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/dragonsGates.json")),
                filters,
                permutatorExecutor,
                converter);

        assertThat(builder.buildAll().size(), is(42));
        assertThat(builder.buildAll().get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "1 Bamboo", "1 Bamboo",
                "2 Bamboo",
                "2 Bamboo", "3 Bamboo", "4 Bamboo", "5 Bamboo", "6 Bamboo", "7 Bamboo", "8 Bamboo",
                "Green", "Green", "Green"
        )));
    }

    @Test
    public void dragonsTail() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/dragonsTail.json")),
                filters,
                permutatorExecutor,
                converter);

        assertThat(builder.buildAll().size(), is(72));
        assertThat(builder.buildAll().get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "2 Bamboo", "3 Bamboo", "4 Bamboo", "5 Bamboo", "6 Bamboo", "7 Bamboo", "8 Bamboo", "9 Bamboo",
                "North", "North",
                "Red", "Red", "Red"
        )));
    }

    @Test
    public void dragonsTeeth() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/dragonsTeeth.json")),
                filters,
                permutatorExecutor,
                converter);

        //TODO: check that this hand is 1-7, 2-8 and not 3-9 and if it's only for spot and crack
        assertThat(builder.buildAll().size(), is(28));
        assertThat(builder.buildAll().get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Spot",
                "1 Spot", "2 Spot", "3 Spot", "4 Spot", "5 Spot", "6 Spot", "7 Spot",
                "Red", "Red", "Red",
                "White", "White", "White"
        )));
    }

    @Test
    public void gretasGarden() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/gretasGarden.json")),
                filters,
                permutatorExecutor,
                converter);

        assertThat(builder.buildAll().size(), is(3));
    }

    @Test
    public void gretasDragon() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/gretasDragon.json")),
                filters,
                permutatorExecutor,
                converter);

        assertThat(builder.buildAll().size(), is(9));
    }

    @Test
    public void gertiesGarter() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/gertiesGarter.json")),
                filters,
                permutatorExecutor,
                converter);

        assertThat(builder.buildAll().size(), is(3));
        assertThat(builder.buildAll().get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "1 Spot",
                "2 Bamboo", "2 Spot",
                "3 Bamboo", "3 Spot",
                "4 Bamboo", "4 Spot",
                "5 Bamboo", "5 Spot",
                "6 Bamboo", "6 Spot",
                "7 Bamboo", "7 Spot"
        )));
        assertThat(builder.buildAll().get(1).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "1 Crack",
                "2 Bamboo", "2 Crack",
                "3 Bamboo", "3 Crack",
                "4 Bamboo", "4 Crack",
                "5 Bamboo", "5 Crack",
                "6 Bamboo", "6 Crack",
                "7 Bamboo", "7 Crack"
        )));
        assertThat(builder.buildAll().get(2).getRequiredTiles(), is(Arrays.asList(
                "1 Crack", "1 Spot", 
                "2 Crack", "2 Spot", 
                "3 Crack", "3 Spot", 
                "4 Crack", "4 Spot", 
                "5 Crack", "5 Spot", 
                "6 Crack", "6 Spot", 
                "7 Crack", "7 Spot"
        )));
    }
}