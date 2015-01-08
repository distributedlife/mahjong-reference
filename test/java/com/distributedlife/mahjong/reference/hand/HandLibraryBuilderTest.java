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

import static com.distributedlife.mahjong.reference.data.TileSet.Tile;
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
    @Ignore
    public void buildAllHands() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/all.json")),
                filters,
                permutatorExecutor,
                converter);

        assertThat(builder.buildAll().size(), is(5476659));
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
        assertThat(hands.get(0).getPart1(), is(Tile.B9.v + Tile.C2.v + Tile.C3.v + Tile.C4.v + Tile.C5.v + Tile.C6.v + Tile.C7.v + Tile.C8.v + Tile.S1.v));
        assertThat(hands.get(0).getPart2(), is(Tile.B9.v + Tile.C2.v + Tile.S1.v));
        assertThat(hands.get(0).getPart3(), is(Tile.B9.v + Tile.S1.v));
        assertThat(hands.get(0).getPart4(), is(0L));
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
        assertThat(hands.get(0).getPart1(), is(
                Tile.B1.v + Tile.B2.v + Tile.B3.v + Tile.B4.v + Tile.B5.v +
                Tile.B6.v + Tile.B7.v + Tile.B8.v + Tile.B9.v +
                Tile.NW.v + Tile.EW.v + Tile.SW.v + Tile.WW.v
                )
        );
        assertThat(hands.get(0).getPart2(), is(Tile.B1.v));
        assertThat(hands.get(0).getPart3(), is(0L));
        assertThat(hands.get(0).getPart4(), is(0L));
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
        assertThat(hands.get(0).getPart1(), is(
            Tile.B1.v + Tile.B2.v + Tile.B3.v + Tile.B4.v + Tile.B5.v + Tile.B6.v + Tile.B7.v + Tile.B8.v +
            Tile.GD.v + Tile.RD.v)
        );
        assertThat(hands.get(0).getPart2(), is(Tile.RD.v + Tile.GD.v));
        assertThat(hands.get(0).getPart3(), is(Tile.RD.v));
        assertThat(hands.get(0).getPart4(), is(Tile.RD.v));
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
        assertThat(hands.get(0).getPart1(), is(
            Tile.B1.v + Tile.B2.v + Tile.B3.v + Tile.B4.v + Tile.B5.v + Tile.B6.v + Tile.B7.v + Tile.B8.v + Tile.B9.v +
            Tile.GD.v + Tile.RD.v + Tile.EW.v + Tile.NW.v + Tile.WD.v)
        );
        assertThat(hands.get(0).getPart2(), is(0L));
        assertThat(hands.get(0).getPart3(), is(0L));
        assertThat(hands.get(0).getPart4(), is(0L));
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
        assertThat(hands.get(0).getPart1(), is(
            Tile.B1.v + Tile.B2.v + Tile.B3.v + Tile.B4.v + Tile.B5.v + Tile.B6.v + Tile.B7.v + Tile.B8.v + Tile.B9.v +
            Tile.GD.v + Tile.RD.v + Tile.WD.v)
        );
        assertThat(hands.get(0).getPart2(), is(Tile.RD.v));
        assertThat(hands.get(0).getPart3(), is(Tile.RD.v));
        assertThat(hands.get(0).getPart4(), is(0L));
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
        assertThat(hands.get(0).getPart1(), is(
            Tile.B1.v + Tile.B2.v + Tile.B3.v + Tile.B4.v + Tile.B5.v + Tile.B6.v + Tile.B7.v + Tile.B8.v + Tile.B9.v +
            Tile.RD.v)
        );
        assertThat(hands.get(0).getPart2(), is(Tile.B1.v + Tile.RD.v));
        assertThat(hands.get(0).getPart3(), is(Tile.B1.v + Tile.RD.v));
        assertThat(hands.get(0).getPart4(), is(0L));
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
        assertThat(hands.get(0).getPart1(), is(
            Tile.C1.v + Tile.C2.v + Tile.C3.v + Tile.C4.v + Tile.C5.v + Tile.C6.v + Tile.C7.v + Tile.C8.v +
            Tile.RD.v)
        );
        assertThat(hands.get(0).getPart2(), is(Tile.C1.v + Tile.C2.v + Tile.RD.v));
        assertThat(hands.get(0).getPart3(), is(Tile.C1.v + Tile.RD.v));
        assertThat(hands.get(0).getPart4(), is(0L));
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
    }

    @Test
    public void crazyChows() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/crazyChows.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(3470340));
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
    }

    @Test
    public void knitting() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/knitting.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(994950));
    }

    @Test
    public void tripleKnitting() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/tripleKnitting.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(13338));
    }

    @Test
    public void sparrowsSanctuary() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/sparrowsSanctuary.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(1));
    }

    @Test
    public void heavenlyTwins() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/heavenlyTwins.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(6912));
    }

    @Test
    public void allPair() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/allPair.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(330558));
    }

    @Test
    public void allPairHonours() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/allPairHonours.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(27742));
    }

    @Test
    public void sevenTwins() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/sevenTwins.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(4));
    }

    @Test
    public void allPairJade() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/allPairJade.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(96));
    }

    @Test
    public void allPairRubyJade() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/allPairRubyJade.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(882));
    }

    @Test
    public void dragonsBreath() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/dragonsBreath.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(7938));
    }

    @Test
    public void windfall() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/windfall.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(2646));
    }

    @Test
    public void dragonette() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/dragonette.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(693));
    }

    @Test
    public void goldenGates() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/goldenGates.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(6));
    }

    @Test
    public void windyDragons() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/windyDragons.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(3));
    }

    @Test
    public void redWaratah() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/redWaratah.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(324));
    }

    @Test
    public void greenJade() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/greenJade.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(504));
    }

    @Test
    public void redCoral() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/redCoral.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(504));
    }

    @Test
    public void whiteOpal() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/whiteOpal.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(504));
    }

    @Test
    public void imperialJade() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/imperialJade.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(20));
    }

    @Test
    public void lilyOfTheValley() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/lilyOfTheValley.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(30));
    }

    @Test
    public void lillyPilly() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/lillyPilly.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(84));
    }

    @Test
    public void royalRuby() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/royalRuby.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(4));
    }

    @Test
    public void rubyJade() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/rubyJade.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(140));
    }

    @Test
    public void redLily() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/redLily.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(12));
    }

    @Test
    public void ordinaryMahJong() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/ordinaryMahjong.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(216552));
    }

    @Test
    public void sunrise() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/sunrise.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(343));
    }

    @Test
    public void sunset() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/sunrise.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(343));
    }

    @Test
    public void numbersInParallel() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/numbersInParallel.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(42));
    }

    @Test
    @Ignore
    public void numbersDoubled() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/numbersDoubled.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(42));
    }

    @Test
    @Ignore
    public void uniqueWonder() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/uniqueWonder.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(13));
    }

    @Test
    @Ignore
    public void allHonourHand() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/allHonourHand.json")),
                filters,
                permutatorExecutor,
                converter);

        //Does honour mean 1 to 9 in the pair?
        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(13));
    }

    @Test
    public void windyOnes() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/windyOnes.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(4));
    }

    @Test
    public void windyNines() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/windyNines.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(4));
    }

    @Test
    @Ignore
    public void headAndTails() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/headAndTails.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(4));
    }

    @Test
    public void allWindsAndDragons() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/allWindsAndDragons.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(105));
    }

    @Test
    public void purityHand() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/purityHand.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(12096));
    }

    @Test
    public void chineseOdds() throws IOException {
        HandLibraryBuilder builder = new HandLibraryBuilder(
                tileSet,
                new JsonToHandDefinition(new PermutatorBuilder(), jsonToPermutatorConverter).getHandDefinitions(Json.loadFromResource("/chineseOdds.json")),
                filters,
                permutatorExecutor,
                converter);

        List<Hand> hands = builder.buildAll();
        assertThat(hands.size(), is(15));
    }
}