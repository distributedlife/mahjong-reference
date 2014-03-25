package com.distributedlife.mahjong.reference.json;

import com.distributedlife.mahjong.reference.node.HandNode;
import org.json.JSONArray;
import org.json.JSONObject;

public class TreeToJsonAdapter {
    public JSONObject toJson(HandNode node) {
        JSONObject nodeAsJson = new JSONObject();
        JSONArray childrenAsJson = new JSONArray();
        nodeAsJson.put("n", reduce(node.getName()));

        if (node.getChildren().isEmpty()) {
            return nodeAsJson;
        }

        for (HandNode child : node.getChildren()) {
            childrenAsJson.put(toJson(child));
        }

        nodeAsJson.put("c", childrenAsJson);
        return nodeAsJson;
    }

    private String reduce(String name) {
        return name.replace("Bamboo", "一").replace("Crack", "二").replace("Spot", "三")
                .replace("West", "西").replace("East", "東").replace("South", "南").replace("North", "北")
                .replace("Red", "中").replace("Green", "G").replace("White", "门");
    }

    private String expand(String name) {
        return name.replace("一", "Bamboo").replace("二", "Crack").replace("三", "Spot")
                .replace("西", "West").replace("東", "East").replace("南", "South").replace("北", "North")
                .replace("中", "Red").replace("G", "Green").replace("门", "White");
    }

    public HandNode fromJson(JSONObject json, HandNode root) {
        String name = expand(json.getString("n"));

        if (!root.hasChild(name)) {
            root.addChild(name);
        }
        HandNode current = root.getChild(name);

        if (!json.has("c")) {
            return root;
        }

        JSONArray children = json.getJSONArray("c");
        for (int i = 0; i < children.length(); i++) {
            fromJson(children.getJSONObject(i), current);
        }

        return root;
    }

    public HandNode fromJson(JSONObject json) {
        String name = expand(json.getString("n"));

        HandNode root = new HandNode(name);

        if (!json.has("c")) {
            return root;
        }

        JSONArray children = json.getJSONArray("c");
        for (int i = 0; i < children.length(); i++) {
            fromJson(children.getJSONObject(i), root);
        }

        return root;
    }
}
