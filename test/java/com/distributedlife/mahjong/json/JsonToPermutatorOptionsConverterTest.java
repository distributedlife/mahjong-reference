package com.distributedlife.mahjong.json;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JsonToPermutatorOptionsConverterTest {
    private final JSONObject requirements = new JSONObject();
    private final JSONObject missingRequirements = new JSONObject();
    private final JsonToPermutatorOptionsConverter converter = new JsonToPermutatorOptionsConverter();

    @Before
    public void setup() {
        requirements.put("type", "pung");
        requirements.put("from", 1);
        requirements.put("to", 9);
        requirements.put("tiles", new JSONArray("['1', '2', '3']"));
        requirements.put("tile", "4");
        requirements.put("suit", "2nd");
        requirements.put("length", 5);
    }

    @Test
    public void shouldConvertType() {
        assertThat(converter.convert(requirements).getType(), is("pung"));
    }

    @Test
    public void shouldConvertFrom() {
        assertThat(converter.convert(requirements).getFrom(), is(1));
    }

    @Test
    public void shouldConvertTo() {
        assertThat(converter.convert(requirements).getTo(), is(9));
    }

    @Test
    public void shouldConvertTiles() {
        assertThat(converter.convert(requirements).getTiles(), is(Arrays.asList("1", "2", "3")));
        assertThat(converter.convert(requirements).getTiles().get(0), is("1"));
    }

    @Test
    public void shouldConvertTile() {
        assertThat(converter.convert(requirements).getTile(), is("4"));
    }

    @Test
    public void shouldConvertTheSuit() {
        assertThat(converter.convert(requirements).getSuit(), is("2nd"));
    }

    @Test
    public void shouldConvertTheLength() {
        assertThat(converter.convert(requirements).getLength(), is(5));
    }

    @Test
    public void shouldDefaultKMissingValues() {
        assertThat(converter.convert(missingRequirements).getType(), is(""));
        assertThat(converter.convert(missingRequirements).getFrom(), is(0));
        assertThat(converter.convert(missingRequirements).getTo(), is(0));
        List<String> list = new ArrayList<String>();
        assertThat(converter.convert(missingRequirements).getTiles(), is(list));
        assertThat(converter.convert(missingRequirements).getSuit(), is("1st"));
        assertThat(converter.convert(missingRequirements).getTile(), is(""));
        assertThat(converter.convert(missingRequirements).getLength(), is(0));
    }
}
