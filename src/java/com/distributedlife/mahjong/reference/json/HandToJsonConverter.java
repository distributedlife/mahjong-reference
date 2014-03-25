package com.distributedlife.mahjong.reference.json;

import com.distributedlife.mahjong.reference.hand.Hand;
import org.json.JSONObject;

public class HandToJsonConverter {
    public JSONObject convert(Hand hand) {
        JSONObject jsonObject = new JSONObject();

        if (hand.getPart1() > 0) {
            jsonObject.put("1", hand.getPart1());
        }
        if (hand.getPart2() > 0) {
            jsonObject.put("2", hand.getPart2());
        }
        if (hand.getPart3() > 0) {
            jsonObject.put("3", hand.getPart3());
        }
        if (hand.getPart4() > 0) {
            jsonObject.put("4", hand.getPart4());
        }

        return jsonObject;
    }
}
