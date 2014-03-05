package com.distributedlife.mahjong.reference.json;

import com.distributedlife.mahjong.reference.node.HandNode;
import org.json.JSONArray;
import org.json.JSONObject;

public class TreeToJsonAdapter {
    public JSONObject convert(HandNode node) {
        JSONObject nodeAsJson = new JSONObject();
        JSONArray childrenAsJson = new JSONArray();
        nodeAsJson.put("n", node.getName().replace("Bamboo", "B").replace("Crack", "C").replace("Spot", "S").replace(" ", ""));

        if (node.getChildren().isEmpty()) {
            return nodeAsJson;
        }

        for (HandNode child : node.getChildren()) {
            childrenAsJson.put(convert(child));
        }

        nodeAsJson.put("c", childrenAsJson);
        return nodeAsJson;
    }
}
