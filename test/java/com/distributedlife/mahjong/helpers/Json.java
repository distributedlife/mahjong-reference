package com.distributedlife.mahjong.helpers;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;

public class Json {
    public static JSONObject loadFromResource(String filename) throws IOException {
        return new JSONObject(IOUtils.toString(Json.class.getResourceAsStream(filename)));
    }
}
