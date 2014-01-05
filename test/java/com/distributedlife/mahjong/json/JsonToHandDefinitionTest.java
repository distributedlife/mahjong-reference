package com.distributedlife.mahjong.json;

import com.distributedlife.mahjong.reference.permute.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JsonToHandDefinitionTest {
    private JSONObject root;
    private JsonToHandDefinition jsonToHandDefinition = new JsonToHandDefinition(new PermutatorBuilder());
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
    public void shouldMapRequirementOfRunToSequencePermutator() {
        JSONObject requirement = new JSONObject();
        requirement.put("type", "run");
        requirement.put("from", "1");
        requirement.put("to", "9");

        requirements.put(requirement);

        List<Permutator> loadedRequirements = jsonToHandDefinition.getHandDefinitions(root).get(0).getRequirements();
        assertThat(loadedRequirements.size(), is(1));

        assertThat(loadedRequirements.get(0).getClass().toString(), is(SequencePermutator.class.toString()));
    }

    @Test
    public void shouldMapRequirementOfPungToPungPermutator() {
        JSONObject requirement = new JSONObject();
        requirement.put("type", "pung");
        requirement.put("tiles", new JSONArray("[1, 2, 3]"));

        requirements.put(requirement);

        List<Permutator> loadedRequirements = jsonToHandDefinition.getHandDefinitions(root).get(0).getRequirements();
        assertThat(loadedRequirements.size(), is(1));

        assertThat(loadedRequirements.get(0).getClass().toString(), is(PungPermutator.class.toString()));
    }

    @Test
    public void shouldMapRequirementOfPairToPairPermutator() {
        JSONObject requirement = new JSONObject();
        requirement.put("type", "pair");
        requirement.put("tiles", new JSONArray("[1, 2, 3]"));

        requirements.put(requirement);

        List<Permutator> loadedRequirements = jsonToHandDefinition.getHandDefinitions(root).get(0).getRequirements();
        assertThat(loadedRequirements.size(), is(1));

        assertThat(loadedRequirements.get(0).getClass().toString(), is(PairPermutator.class.toString()));
    }

    @Test
    public void shouldMapRequirementOfAnyPairedToAnyPairedPermutator() {
        requirements.put(new JSONObject("{type: 'any-paired'}"));

        List<Permutator> loadedRequirements = jsonToHandDefinition.getHandDefinitions(root).get(0).getRequirements();
        assertThat(loadedRequirements.size(), is(1));

        assertThat(loadedRequirements.get(0).getClass().toString(), is(AnyPairedPermutator.class.toString()));
    }
}
