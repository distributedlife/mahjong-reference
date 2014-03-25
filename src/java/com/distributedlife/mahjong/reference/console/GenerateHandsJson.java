package com.distributedlife.mahjong.reference.console;

import com.distributedlife.mahjong.reference.adapter.HandCandidateToAHandConverter;
import com.distributedlife.mahjong.reference.data.TileSet;
import com.distributedlife.mahjong.reference.filter.DuplicateHandCandidateFilter;
import com.distributedlife.mahjong.reference.filter.HandCandidateFilter;
import com.distributedlife.mahjong.reference.filter.InvalidHandCandidateFilter;
import com.distributedlife.mahjong.reference.hand.Hand;
import com.distributedlife.mahjong.reference.hand.HandLibraryBuilder;
import com.distributedlife.mahjong.reference.json.HandToJsonConverter;
import com.distributedlife.mahjong.reference.json.JsonToHandDefinition;
import com.distributedlife.mahjong.reference.json.JsonToPermutatorOptionsConverter;
import com.distributedlife.mahjong.reference.permute.PermutatorBuilder;
import com.distributedlife.mahjong.reference.permute.PermutatorExecutor;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class GenerateHandsJson {
    public static void main(String [] args) {
        List<HandCandidateFilter> filters = Arrays.asList(new InvalidHandCandidateFilter(), new DuplicateHandCandidateFilter());

        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(IOUtils.toString(GenerateHandsJson.class.getResourceAsStream("/all.json")));
        } catch (Exception e) {
            throw new RuntimeException("Unable to load 'all.json'", e);
        }

        HandLibraryBuilder builder = new HandLibraryBuilder(
                new TileSet(),
                new JsonToHandDefinition(new PermutatorBuilder(), new JsonToPermutatorOptionsConverter()).getHandDefinitions(jsonObject),
                filters,
                new PermutatorExecutor(),
                new HandCandidateToAHandConverter());


        JSONObject rootJson = new JSONObject();
        JSONArray handsJson = new JSONArray();
        rootJson.put("hands", handsJson);

        Map<String, JSONArray> handCombinations = new HashMap<String, JSONArray>();
        HandToJsonConverter handToJsonConverter = new HandToJsonConverter();
        for (Hand hand : builder.buildAll()) {
            if (!handCombinations.containsKey(hand.getName())) {
                handCombinations.put(hand.getName(), new JSONArray());
            }

            JSONArray combinations = handCombinations.get(hand.getName());
            combinations.put(handToJsonConverter.convert(hand));
        }

        for (String name : handCombinations.keySet()) {
            JSONArray combinations = handCombinations.get(name);

            JSONObject handCombinationsAsJson = new JSONObject();
            handCombinationsAsJson.put("name", name);
            handCombinationsAsJson.put("combinations", combinations);
            handsJson.put(handCombinationsAsJson);
        }

        try {
            PrintWriter writer = new PrintWriter("all-hands.json", "UTF-8");
            writer.print(rootJson);
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Unable to write generated file", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unable to write generated file", e);
        }
    }
}
