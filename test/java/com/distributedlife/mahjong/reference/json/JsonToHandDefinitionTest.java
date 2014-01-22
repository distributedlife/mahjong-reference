package com.distributedlife.mahjong.reference.json;

import com.distributedlife.mahjong.reference.permute.PermutatorBuilder;
import com.distributedlife.mahjong.reference.permute.PermutatorBuilderOptions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class JsonToHandDefinitionTest {
    private JSONObject root;
    private final PermutatorBuilder permutatorBuilder = mock(PermutatorBuilder.class);
    private final JsonToPermutatorOptionsConverter jsonToPermutatorConverter = mock(JsonToPermutatorOptionsConverter.class);
    private final JsonToHandDefinition jsonToHandDefinition = new JsonToHandDefinition(permutatorBuilder, jsonToPermutatorConverter);
    private JSONArray requirements;

    @Before
    public void setup() {
        root = new JSONObject();
        JSONArray hands = new JSONArray();
        root.put("hands", hands);

        JSONObject runPungAndAPair = new JSONObject();
        runPungAndAPair.put("name", "Run, Pung and a Pair");

        JSONArray suitsArray = new JSONArray();
        suitsArray.put("bamboo");
        suitsArray.put("spot");
        suitsArray.put("crack");
        runPungAndAPair.put("suits", suitsArray);

        requirements = new JSONArray();
        requirements.put(new JSONObject());
        runPungAndAPair.put("requirements", requirements);

        hands.put(runPungAndAPair);
    }

    @Test
    public void shouldSetTheNameOfTheHandDefinition() {
        assertThat(jsonToHandDefinition.getHandDefinitions(root).get(0).getName(), is("Run, Pung and a Pair"));
    }

    @Test
    public void shouldHaveAnArrayOfAllowedSuites() {
        List<String> suits = jsonToHandDefinition.getHandDefinitions(root).get(0).getSuits();
        assertThat(suits.size(), is(3));
        assertThat(suits.get(0), is("Bamboo"));
        assertThat(suits.get(1), is("Spot"));
        assertThat(suits.get(2), is("Crack"));
    }

    @Test
    public void shouldDelegateTheRequirementOptionsConversion() {
        jsonToHandDefinition.getHandDefinitions(root);

        verify(jsonToPermutatorConverter).convert(requirements.getJSONObject(0));
    }

    @Test
    public void shouldPassTheConvertedOptionsToTheBuilder() {
        PermutatorBuilderOptions convertedOptions = mock(PermutatorBuilderOptions.class);
        when(jsonToPermutatorConverter.convert(requirements.getJSONObject(0))).thenReturn(convertedOptions);

        jsonToHandDefinition.getHandDefinitions(root);

        verify(permutatorBuilder).build(convertedOptions);
    }
}
