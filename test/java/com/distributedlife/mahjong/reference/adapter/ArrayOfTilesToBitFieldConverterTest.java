package com.distributedlife.mahjong.reference.adapter;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.distributedlife.mahjong.reference.data.TileSet.Tile;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ArrayOfTilesToBitFieldConverterTest {
    @Test
    public void shouldConvertTilesToBitValues() {
        List<String> tiles = Arrays.asList("1 Bamboo", "2 Bamboo", "3 Bamboo", "4 Bamboo");

        assertThat(ArrayOfTilesToBitFieldConverter.convertToBitField(tiles).get(0), is(Tile.B1.v + Tile.B2.v + Tile.B3.v + Tile.B4.v));
        assertThat(ArrayOfTilesToBitFieldConverter.convertToBitField(tiles).get(1), is(0L));
        assertThat(ArrayOfTilesToBitFieldConverter.convertToBitField(tiles).get(2), is(0L));
        assertThat(ArrayOfTilesToBitFieldConverter.convertToBitField(tiles).get(3), is(0L));
    }

    @Test
    public void shouldConvertMultiplesOfATileToCorrectPart() {
        List<String> tiles = Arrays.asList("1 Bamboo", "1 Bamboo", "1 Bamboo", "1 Bamboo");

        assertThat(ArrayOfTilesToBitFieldConverter.convertToBitField(tiles).get(0), is(Tile.B1.v));
        assertThat(ArrayOfTilesToBitFieldConverter.convertToBitField(tiles).get(1), is(Tile.B1.v));
        assertThat(ArrayOfTilesToBitFieldConverter.convertToBitField(tiles).get(2), is(Tile.B1.v));
        assertThat(ArrayOfTilesToBitFieldConverter.convertToBitField(tiles).get(3), is(Tile.B1.v));
    }

    @Test
    public void shouldReverseTheConversion() {
        assertThat(ArrayOfTilesToBitFieldConverter.convertFromBitField(
                Tile.B1.v + Tile.B2.v + Tile.B3.v + Tile.B4.v,
                0L,
                0L,
                0L
        ), is(Arrays.asList("1 Bamboo", "2 Bamboo", "3 Bamboo", "4 Bamboo")));

        assertThat(ArrayOfTilesToBitFieldConverter.convertFromBitField(
                Tile.B1.v, Tile.B1.v, Tile.B1.v, Tile.B1.v
        ), is(Arrays.asList("1 Bamboo", "1 Bamboo", "1 Bamboo", "1 Bamboo")));
    }
}
