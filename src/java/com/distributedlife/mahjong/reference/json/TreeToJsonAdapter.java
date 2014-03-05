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
        return name.replace("Bamboo", "B").replace("Crack", "C").replace("Spot", "Sp")
                .replace("West", "We").replace("East", "E").replace("South", "St").replace("North", "N")
                .replace("Red", "R").replace("Green", "G").replace("White", "Wh");
    }

    private String expand(String name) {
        return name.replace("B", "Bamboo").replace("C", "Crack").replace("Sp", "Spot")
                .replace("We", "West").replace("E", "East").replace("St", "South").replace("N", "North")
                .replace("R", "Red").replace("G", "Green").replace("Wh", "White");
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
