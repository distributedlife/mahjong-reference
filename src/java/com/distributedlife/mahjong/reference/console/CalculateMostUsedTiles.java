package com.distributedlife.mahjong.reference.console;

import com.distributedlife.mahjong.reference.adapter.HandCandidateToAHandConverter;
import com.distributedlife.mahjong.reference.data.TileSet;
import com.distributedlife.mahjong.reference.filter.DuplicateHandCandidateFilter;
import com.distributedlife.mahjong.reference.filter.HandCandidateFilter;
import com.distributedlife.mahjong.reference.filter.InvalidHandCandidateFilter;
import com.distributedlife.mahjong.reference.hand.Hand;
import com.distributedlife.mahjong.reference.hand.HandLibraryBuilder;
import com.distributedlife.mahjong.reference.json.JsonToHandDefinition;
import com.distributedlife.mahjong.reference.json.JsonToPermutatorOptionsConverter;
import com.distributedlife.mahjong.reference.permute.PermutatorBuilder;
import com.distributedlife.mahjong.reference.permute.PermutatorExecutor;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.distributedlife.mahjong.reference.data.TileSet.*;

public class CalculateMostUsedTiles {
    public static void main(String [] args) {
        List<HandCandidateFilter> filters = Arrays.asList(new InvalidHandCandidateFilter(), new DuplicateHandCandidateFilter());

        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(IOUtils.toString(CalculateMostUsedTiles.class.getResourceAsStream("/all.json")));
        } catch (Exception e) {
            throw new RuntimeException("Unable to load 'all.json'", e);
        }

        HandLibraryBuilder builder = new HandLibraryBuilder(
                new TileSet(),
                new JsonToHandDefinition(new PermutatorBuilder(), new JsonToPermutatorOptionsConverter()).getHandDefinitions(jsonObject),
                filters,
                new PermutatorExecutor(),
                new HandCandidateToAHandConverter());

        Map<TileSet.Tile, Long> tileCounts = new HashMap<TileSet.Tile, Long>();
        for (Tile tile : BIT_TILES ) {
            tileCounts.put(tile, 0L);
        }

        for (Hand hand : builder.buildAll()) {
            for (Tile tile : BIT_TILES ) {
                if ((hand.getPart1() & tile.v) == tile.v) {
                    tileCounts.put(tile, tileCounts.get(tile) + 1);
                }
                if ((hand.getPart2() & tile.v) == tile.v) {
                    tileCounts.put(tile, tileCounts.get(tile) + 1);
                }
                if ((hand.getPart3() & tile.v) == tile.v) {
                    tileCounts.put(tile, tileCounts.get(tile) + 1);
                }
                if ((hand.getPart4() & tile.v) == tile.v) {
                    tileCounts.put(tile, tileCounts.get(tile) + 1);
                }
            }
        }

        for (Tile tile : tileCounts.keySet()) {
            System.out.println(String.format("Tile: '%s' with count: %d", TileSet.getNameForBitTile(tile), tileCounts.get(tile)));
        }
    }
}
