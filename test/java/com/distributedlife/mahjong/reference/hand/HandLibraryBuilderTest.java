package com.distributedlife.mahjong.reference.hand;

import com.distributedlife.mahjong.helpers.Json;
import com.distributedlife.mahjong.reference.adapter.HandCandidateToAHandConverter;
import com.distributedlife.mahjong.reference.data.TileSet;
import com.distributedlife.mahjong.reference.filter.DuplicateHandCandidateFilter;
import com.distributedlife.mahjong.reference.filter.HandCandidateFilter;
import com.distributedlife.mahjong.reference.filter.InvalidHandCandidateFilter;
import com.distributedlife.mahjong.reference.json.JsonToHandDefinition;
import com.distributedlife.mahjong.reference.json.JsonToPermutatorOptionsConverter;
import com.distributedlife.mahjong.reference.permute.PermutatorBuilder;
import com.distributedlife.mahjong.reference.permute.PermutatorExecutor;
import org.junit.Before;
import org.junit.Ignore;
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

        assertThat(builder.buildAll().size(), is(618421));
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

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(42));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "1 Bamboo", "1 Bamboo",
                "2 Crack",
                "2 Crack", "3 Crack", "4 Crack", "5 Crack", "6 Crack", "7 Crack", "8 Crack",
                "9 Spot", "9 Spot", "9 Spot"
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

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(39));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList(
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

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(138));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "2 Bamboo", "3 Bamboo", "4 Bamboo", "5 Bamboo", "6 Bamboo", "7 Bamboo", "8 Bamboo",
                "East", "East",
                "East", "East",
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

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(63));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "2 Bamboo", "3 Bamboo", "4 Bamboo", "5 Bamboo", "6 Bamboo", "7 Bamboo", "8 Bamboo", "9 Bamboo",
                "East", "Green", "North", "Red", "South"
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

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(9));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "2 Bamboo", "3 Bamboo", "4 Bamboo", "5 Bamboo", "6 Bamboo", "7 Bamboo", "8 Bamboo", "9 Bamboo",
                "Green", "Green", "Green",
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

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(945));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo",
                "1 Bamboo",
                "1 Bamboo",
                "2 Bamboo", "3 Bamboo", "4 Bamboo", "5 Bamboo", "6 Bamboo", "7 Bamboo", "8 Bamboo", "9 Bamboo",
                "East", "East", "East"
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

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(42));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList(
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

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(72));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "2 Bamboo", "3 Bamboo", "4 Bamboo", "5 Bamboo", "6 Bamboo", "7 Bamboo", "8 Bamboo", "9 Bamboo",
                "East", "East", "East",
                "Green", "Green"
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

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(28));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Crack",
                "1 Crack", "2 Crack", "3 Crack", "4 Crack", "5 Crack", "6 Crack", "7 Crack",
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

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(3));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "1 Crack",
                "2 Bamboo", "2 Crack",
                "3 Bamboo", "3 Crack",
                "4 Bamboo", "4 Crack",
                "5 Bamboo", "5 Crack",
                "6 Bamboo", "6 Crack",
                "7 Bamboo", "7 Crack"
        )));
        assertThat(hands.get(1).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "1 Spot",
                "2 Bamboo", "2 Spot",
                "3 Bamboo", "3 Spot",
                "4 Bamboo", "4 Spot",
                "5 Bamboo", "5 Spot",
                "6 Bamboo", "6 Spot",
                "7 Bamboo", "7 Spot"
        )));
        assertThat(hands.get(2).getRequiredTiles(), is(Arrays.asList(
                "1 Crack", "1 Spot", 
                "2 Crack", "2 Spot", 
                "3 Crack", "3 Spot", 
                "4 Crack", "4 Spot", 
                "5 Crack", "5 Spot", 
                "6 Crack", "6 Spot", 
                "7 Crack", "7 Spot"
        )));
    }

    @Test
    public void yinYang() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/yinYang.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(6));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "1 Bamboo",
                "2 Bamboo", "3 Bamboo", "4 Bamboo",
                "5 Bamboo", "5 Bamboo",
                "5 Crack", "5 Crack",
                "6 Crack", "7 Crack", "8 Crack",
                "9 Crack", "9 Crack"
        )));
        assertThat(hands.get(1).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "1 Bamboo",
                "2 Bamboo", "3 Bamboo", "4 Bamboo",
                "5 Bamboo", "5 Bamboo",
                "5 Spot", "5 Spot",
                "6 Spot", "7 Spot", "8 Spot",
                "9 Spot", "9 Spot"
        )));
    }

    @Test
    public void redLantern() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/redLantern.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(21));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo",
                "1 Bamboo", "2 Bamboo", "3 Bamboo", "4 Bamboo", "5 Bamboo", "6 Bamboo", "7 Bamboo",
                "OwnWind", "OwnWind", "OwnWind",
                "Red", "Red", "Red"
        )));
    }

    @Test
    public void bigRobert() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/bigRobert.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(1008));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "1 Bamboo", "1 Bamboo",
                "2 Bamboo", "2 Bamboo", "2 Bamboo",
                "3 Bamboo", "3 Bamboo", "3 Bamboo",
                "4 Bamboo", "4 Bamboo", "4 Bamboo",
                "East", "East"
        )));
    }

    @Test
    public void moonAtTheBottomOfTheWell() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/moonAtTheBottomOfTheWell.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(1383));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Spot", "1 Spot", "1 Spot", "1 Spot",
                "2 Spot", "2 Spot", "2 Spot", "2 Spot",
                "3 Spot", "3 Spot", "3 Spot", "3 Spot",
                "4 Spot", "4 Spot"
        )));
    }

    @Test
    public void chowChow() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/chowChow.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(4149));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "1 Bamboo", "1 Bamboo", "1 Bamboo",
                "2 Bamboo", "2 Bamboo", "2 Bamboo", "2 Bamboo",
                "3 Bamboo", "3 Bamboo", "3 Bamboo", "3 Bamboo",
                "4 Bamboo", "4 Bamboo"
        )));
    }

    @Test
    public void threePhilosophers() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/threePhilosophers.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(339570));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "1 Bamboo", "1 Bamboo", "1 Bamboo", "1 Crack", "1 Spot",
                "2 Bamboo", "2 Crack", "2 Crack", "2 Spot",
                "3 Bamboo", "3 Crack", "3 Spot", "3 Spot"
        )));
    }

    @Test
    @Ignore
    public void crazyChows() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/crazyChows.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(339570));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "1 Bamboo", "1 Bamboo", "1 Bamboo", "1 Crack", "1 Spot",
                "2 Crack", "2 Crack", "2 Crack", "2 Crack",
                "3 Spot", "3 Spot", "3 Spot", "3 Spot"
        )));
    }

    @Test
    public void littleRobert() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/littleRobert.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(239904));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "1 Bamboo", "1 Bamboo", "1 Bamboo", "1 Crack", "1 Crack", "1 Crack", "1 Spot",
                "2 Bamboo", "2 Crack", "2 Spot",
                "3 Bamboo", "3 Crack", "3 Spot"
        )));
    }

    @Test
    public void littleBrother() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/littleBrother.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(4116));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "1 Bamboo", "1 Crack", "1 Spot",
                "2 Bamboo", "2 Bamboo", "2 Crack", "2 Spot",
                "3 Bamboo", "3 Bamboo", "3 Crack", "3 Spot",
                "OwnWind", "OwnWind"
        )));
    }

    @Test
    public void hoveringAngel() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/hoveringAngel.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(343));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "1 Crack", "1 Spot",
                "2 Bamboo", "2 Crack", "2 Spot",
                "3 Bamboo", "3 Crack", "3 Spot",
                "OwnWind", "OwnWind", "OwnWind",
                "Red", "Red"
        )));
    }

    @Test
    public void windyChows() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/windyChows.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(1372));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "1 Crack", "1 Spot",
                "2 Bamboo", "2 Crack", "2 Spot",
                "3 Bamboo", "3 Crack", "3 Spot",
                "East",
                "East", "North", "South", "West"
        )));
    }

    @Test
    public void chopSuey() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/chopSuey.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(4));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "1 Crack", "1 Spot",
                "2 Bamboo", "2 Crack", "2 Spot",
                "3 Bamboo", "3 Crack", "3 Spot",
                "East",
                "East", "North", "South", "West"
        )));
    }

    @Test
    public void chowMein() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/chowMein.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(4));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList(
                "7 Bamboo", "7 Crack", "7 Spot",
                "8 Bamboo", "8 Crack", "8 Spot",
                "9 Bamboo", "9 Crack", "9 Spot",
                "East",
                "East", "North", "South", "West"
        )));
    }

    @Test
    public void theProfessors() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/theProfessors.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(12430));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "1 Bamboo", "1 Bamboo",
                "2 Crack", "2 Crack", "2 Crack",
                "3 Spot", "3 Spot", "3 Spot",
                "Green",
                "OwnWind", "OwnWind",
                "Red", "White"
        )));
    }

    @Test
    public void appleBlossom() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/appleBlossom.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(12430));
        assertThat(hands.get(0).getRequiredTiles(), is(Arrays.asList(
                "1 Bamboo", "1 Bamboo", "1 Bamboo",
                "2 Crack", "2 Crack", "2 Crack",
                "3 Spot", "3 Spot", "3 Spot",
                "Green","Green",
                "White", "White", "White"
        )));
    }
}