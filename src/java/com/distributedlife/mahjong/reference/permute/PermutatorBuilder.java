package com.distributedlife.mahjong.reference.permute;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class PermutatorBuilder {
    public Permutator build(JSONObject requirement) {
        String type = requirement.getString("type");

        if (type.equals("run")) {
            return new SequencePermutator(requirement.getInt("from"), requirement.getInt("to"));
        }
        if (type.equals("pung")) {
            return new PungPermutator(convertJsonArrayToJavaList(requirement.getJSONArray("tiles")));
        }
        if (type.equals("pair")) {
            return new PairPermutator(convertJsonArrayToJavaList(requirement.getJSONArray("tiles")));
        }

        return new UnknownPermutator(type);
    }

    private List<String> convertJsonArrayToJavaList(JSONArray jsonArray) {
        return Arrays.asList(jsonArray.join(",").split(","));
    }
}
