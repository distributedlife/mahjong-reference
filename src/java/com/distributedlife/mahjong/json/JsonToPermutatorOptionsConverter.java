package com.distributedlife.mahjong.json;

import com.distributedlife.mahjong.reference.permute.PermutatorBuilderOptions;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonToPermutatorOptionsConverter {
    public PermutatorBuilderOptions convert(JSONObject requirements) {

        String type = getStringValueOrReturnDefault(requirements, "type", "");
        int from = getIntegerValueOrReturnDefault(requirements, "from");
        int to = getIntegerValueOrReturnDefault(requirements, "to");
        List<String> tiles = getStringArrayOrReturnDefault(requirements, "tiles");
        String suit = getStringValueOrReturnDefault(requirements, "suit", "1st");

        return new PermutatorBuilderOptions(type, from, to, tiles, suit);
    }

    private int getIntegerValueOrReturnDefault(JSONObject requirementsForHand, String key) {
        return requirementsForHand.has(key) ? requirementsForHand.getInt(key) : 0;
    }

    private String getStringValueOrReturnDefault(JSONObject requirementsForHand, String key, String defaultValue) {
        return requirementsForHand.has(key) ? requirementsForHand.getString(key) : defaultValue;
    }

    private List<String> getStringArrayOrReturnDefault(JSONObject requirementsForHand, String key) {
        return requirementsForHand.has(key) ? convertJsonArrayToJavaList(requirementsForHand.getJSONArray(key)) : new ArrayList<String>();
    }


    private List<String> convertJsonArrayToJavaList(JSONArray jsonArray) {
        return Arrays.asList(jsonArray.join(",").split(","));
    }
}
