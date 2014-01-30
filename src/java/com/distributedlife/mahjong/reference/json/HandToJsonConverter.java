package com.distributedlife.mahjong.reference.json;

import com.distributedlife.mahjong.reference.hand.Hand;
import org.json.JSONObject;

public class HandToJsonConverter {
    public JSONObject convert(Hand hand) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", hand.getName());
        jsonObject.put("requiredTiles", hand.getRequiredTiles());

        return jsonObject;
    }
}
