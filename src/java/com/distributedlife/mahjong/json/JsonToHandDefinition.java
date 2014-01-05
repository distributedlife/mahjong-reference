package com.distributedlife.mahjong.json;

import com.distributedlife.mahjong.reference.hand.HandDefinition;
import com.distributedlife.mahjong.reference.permute.PermutatorBuilder;
import com.sun.xml.internal.ws.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonToHandDefinition {
    private PermutatorBuilder permutatorBuilder;

    public JsonToHandDefinition(PermutatorBuilder permutatorBuilder) {
        this.permutatorBuilder = permutatorBuilder;
    }

    public List<HandDefinition> getHandDefinitions(JSONObject jsonRoot) {
        List<HandDefinition> definitions = new ArrayList<HandDefinition>();

        JSONArray jsonHands = jsonRoot.getJSONArray("hands");
        for (int i = 0; i < jsonHands.length(); i++) {
            JSONObject jsonDefinition = jsonHands.getJSONObject(i);

            HandDefinition handDefinition = new HandDefinition(jsonDefinition.getString("name"));

            loadSuitsForHand(jsonDefinition, handDefinition);
            loadTileRequirementsForHand(jsonDefinition, handDefinition);

            definitions.add(handDefinition);
        }

        return definitions;
    }

    private void loadTileRequirementsForHand(JSONObject jsonDefinition, HandDefinition handDefinition) {
        JSONArray requirements = jsonDefinition.getJSONArray("requirements");
        for(int j = 0; j < requirements.length(); j++) {
            handDefinition.addRequirement(permutatorBuilder.build(requirements.getJSONObject(j)));
        }
    }

    private void loadSuitsForHand(JSONObject jsonDefinition, HandDefinition handDefinition) {
        JSONArray suits = jsonDefinition.getJSONArray("suits");
        for(int j = 0; j < suits.length(); j++) {
            handDefinition.addSuit(StringUtils.capitalize(suits.getString(j)));
        }
    }
}
