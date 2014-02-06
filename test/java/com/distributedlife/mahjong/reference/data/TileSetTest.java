package com.distributedlife.mahjong.reference.data;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class TileSetTest {

    private TileSet tileSet;

    @Test
    public void shouldContainFourOfEastWind() {
        tileSet = new TileSet();

        assertThat(getPositionAndRemoveFromSet(tileSet, "East"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "East"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "East"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "East"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("East"), is(-1));
    }

    @Test
    public void shouldContainFourOfWestWind() {
        tileSet = new TileSet();

        assertThat(getPositionAndRemoveFromSet(tileSet, "West"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "West"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "West"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "West"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("West"), is(-1));
    }

    @Test
    public void shouldContainFourOfNorthWind() {
        tileSet = new TileSet();

        assertThat(getPositionAndRemoveFromSet(tileSet, "North"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "North"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "North"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "North"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("North"), is(-1));
    }

    @Test
    public void shouldContainFourOfSouthWind() {
        tileSet = new TileSet();

        assertThat(getPositionAndRemoveFromSet(tileSet, "South"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "South"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "South"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "South"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("South"), is(-1));
    }

    @Test
    public void shouldContainFourRedDragons() {
        tileSet = new TileSet();

        assertThat(getPositionAndRemoveFromSet(tileSet, "Red"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "Red"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "Red"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "Red"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("Red"), is(-1));
    }

    @Test
    public void shouldContainFourWhiteDragons() {
        tileSet = new TileSet();

        assertThat(getPositionAndRemoveFromSet(tileSet, "White"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "White"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "White"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "White"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("White"), is(-1));
    }

    @Test
    public void shouldContainFourGreenDragons() {
        tileSet = new TileSet();

        assertThat(getPositionAndRemoveFromSet(tileSet, "Green"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "Green"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "Green"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "Green"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("Green"), is(-1));
    }

    @Test
    public void shouldContainFourBamboosForEachNumber() {
        tileSet = new TileSet();

        assertThat(getPositionAndRemoveFromSet(tileSet, "1 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "1 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "1 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "1 Bamboo"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("1 Bamboo"), is(-1));

        assertThat(getPositionAndRemoveFromSet(tileSet, "2 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "2 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "2 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "2 Bamboo"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("2 Bamboo"), is(-1));

        assertThat(getPositionAndRemoveFromSet(tileSet, "3 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "3 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "3 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "3 Bamboo"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("3 Bamboo"), is(-1));

        assertThat(getPositionAndRemoveFromSet(tileSet, "4 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "4 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "4 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "4 Bamboo"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("4 Bamboo"), is(-1));

        assertThat(getPositionAndRemoveFromSet(tileSet, "5 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "5 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "5 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "5 Bamboo"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("5 Bamboo"), is(-1));

        assertThat(getPositionAndRemoveFromSet(tileSet, "6 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "6 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "6 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "6 Bamboo"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("6 Bamboo"), is(-1));

        assertThat(getPositionAndRemoveFromSet(tileSet, "7 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "7 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "7 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "7 Bamboo"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("7 Bamboo"), is(-1));

        assertThat(getPositionAndRemoveFromSet(tileSet, "8 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "8 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "8 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "8 Bamboo"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("8 Bamboo"), is(-1));

        assertThat(getPositionAndRemoveFromSet(tileSet, "9 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "9 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "9 Bamboo"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "9 Bamboo"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("9 Bamboo"), is(-1));
    }

    @Test
    public void shouldContainFourSpotsForEachNumber() {
        tileSet = new TileSet();

        assertThat(getPositionAndRemoveFromSet(tileSet, "1 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "1 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "1 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "1 Spot"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("1 Spot"), is(-1));

        assertThat(getPositionAndRemoveFromSet(tileSet, "2 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "2 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "2 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "2 Spot"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("2 Spot"), is(-1));

        assertThat(getPositionAndRemoveFromSet(tileSet, "3 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "3 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "3 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "3 Spot"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("3 Spot"), is(-1));

        assertThat(getPositionAndRemoveFromSet(tileSet, "4 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "4 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "4 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "4 Spot"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("4 Spot"), is(-1));

        assertThat(getPositionAndRemoveFromSet(tileSet, "5 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "5 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "5 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "5 Spot"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("5 Spot"), is(-1));

        assertThat(getPositionAndRemoveFromSet(tileSet, "6 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "6 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "6 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "6 Spot"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("6 Spot"), is(-1));

        assertThat(getPositionAndRemoveFromSet(tileSet, "7 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "7 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "7 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "7 Spot"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("7 Spot"), is(-1));

        assertThat(getPositionAndRemoveFromSet(tileSet, "8 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "8 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "8 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "8 Spot"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("8 Spot"), is(-1));

        assertThat(getPositionAndRemoveFromSet(tileSet, "9 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "9 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "9 Spot"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "9 Spot"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("9 Spot"), is(-1));
    }

    @Test
    public void shouldContainFourCharactersForEachNumber() {
        tileSet = new TileSet();

        assertThat(getPositionAndRemoveFromSet(tileSet, "1 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "1 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "1 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "1 Crack"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("1 Crack"), is(-1));

        assertThat(getPositionAndRemoveFromSet(tileSet, "2 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "2 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "2 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "2 Crack"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("2 Crack"), is(-1));

        assertThat(getPositionAndRemoveFromSet(tileSet, "3 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "3 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "3 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "3 Crack"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("3 Crack"), is(-1));

        assertThat(getPositionAndRemoveFromSet(tileSet, "4 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "4 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "4 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "4 Crack"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("4 Crack"), is(-1));

        assertThat(getPositionAndRemoveFromSet(tileSet, "5 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "5 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "5 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "5 Crack"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("5 Crack"), is(-1));

        assertThat(getPositionAndRemoveFromSet(tileSet, "6 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "6 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "6 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "6 Crack"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("6 Crack"), is(-1));

        assertThat(getPositionAndRemoveFromSet(tileSet, "7 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "7 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "7 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "7 Crack"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("7 Crack"), is(-1));

        assertThat(getPositionAndRemoveFromSet(tileSet, "8 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "8 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "8 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "8 Crack"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("8 Crack"), is(-1));

        assertThat(getPositionAndRemoveFromSet(tileSet, "9 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "9 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "9 Crack"), is(not(-1)));
        assertThat(getPositionAndRemoveFromSet(tileSet, "9 Crack"), is(not(-1)));
        assertThat(tileSet.getTiles().indexOf("9 Crack"), is(-1));
    }

    @Test
    public void shouldContain140Tiles() {
        tileSet = new TileSet();

        assertThat(tileSet.getTiles().size(), is(140));
    }

    private int getPositionAndRemoveFromSet(TileSet tileSet, String dragon) {
        int index = tileSet.getTiles().indexOf(dragon);
        tileSet.getTiles().remove(index);

        return index;
    }
}
