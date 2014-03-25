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
        jsonObject.put("part1", hand.getPart1());
        jsonObject.put("part2", hand.getPart2());
        jsonObject.put("part3", hand.getPart3());
        jsonObject.put("part4", hand.getPart4());

        return jsonObject;
    }
}
