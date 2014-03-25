package com.distributedlife.mahjong.reference.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.distributedlife.mahjong.reference.data.TileSet.*;

public class ArrayOfTilesToBitFieldConverter {
    public static List<Long> convertToBitField(List<String> tiles) {
        Long part1 = 0L;
        Long part2 = 0L;
        Long part3 = 0L;
        Long part4 = 0L;

        for(String tile : tiles) {
            Long tileAsBitField = getValueForTile(tile);

            if ((part1 & tileAsBitField) != tileAsBitField) {
                part1 += tileAsBitField;
                continue;
            }
            if ((part2 & tileAsBitField) != tileAsBitField) {
                part2 += tileAsBitField;
                continue;
            }
            if ((part3 & tileAsBitField) != tileAsBitField) {
                part3 += tileAsBitField;
                continue;
            }
            if ((part4 & tileAsBitField) != tileAsBitField) {
                part4 += tileAsBitField;
                continue;
            }
        }

        return Arrays.asList(part1, part2, part3, part4);
    }

    public static List<String> convertFromBitField(Long part1, Long part2, Long part3, Long part4) {
        List<String> tiles = new ArrayList<String>();

        for(Tile tile : BIT_TILES) {
            if ((part1 & tile.v) == tile.v) {
                tiles.add(getNameForBitTile(tile));
            }
            if ((part2 & tile.v) == tile.v) {
                tiles.add(getNameForBitTile(tile));
            }
            if ((part3 & tile.v) == tile.v) {
                tiles.add(getNameForBitTile(tile));
            }
            if ((part4 & tile.v) == tile.v) {
                tiles.add(getNameForBitTile(tile));
            }
        }

        return tiles;
    }
}
