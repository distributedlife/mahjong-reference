package com.distributedlife.mahjong.reference.json;

import com.distributedlife.mahjong.reference.permute.PermutatorBuilderOptions;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonToPermutatorOptionsConverter {
    public PermutatorBuilderOptions convert(JSONObject requirements) {

        String type = getStringValueOrReturnDefault(requirements, "type", "");
        int from = getIntegerValueOrReturnDefault(requirements, "from");
        int to = getIntegerValueOrReturnDefault(requirements, "to");
        List<String> tiles = getStringArrayOrReturnDefault(requirements, "tiles");
        String suit = getStringValueOrReturnDefault(requirements, "suit", "1st");
        String tile = getStringValueOrReturnDefault(requirements, "tile", "");
        int length = getIntegerValueOrReturnDefault(requirements, "length");

        return new PermutatorBuilderOptions(type, from, to, tiles, suit, tile, length);
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
        List<String> listOfEntries = new ArrayList<String>();

        for(int i = 0; i < jsonArray.length(); i++) {
            listOfEntries.add(jsonArray.getString(i));
        }

        return listOfEntries;
    }
}
