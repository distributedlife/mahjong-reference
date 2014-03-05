package com.distributedlife.mahjong.reference.json;

import com.distributedlife.mahjong.reference.hand.Hand;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HandToJsonConverter {
    public JSONObject convert(Hand hand) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", hand.getName());
        jsonObject.put("requiredTiles", hand.getRequiredTiles());

        return jsonObject;
    }
}
