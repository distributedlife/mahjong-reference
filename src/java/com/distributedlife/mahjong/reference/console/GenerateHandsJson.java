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
    private static final int SPLIT_SIZE = 40000;

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



        Map<String, JSONArray> handCombinations = new HashMap<String, JSONArray>();
        HandToJsonConverter handToJsonConverter = new HandToJsonConverter();
        writeToFile(builder.buildAll());
//        for (Hand hand : builder.buildAll()) {
//            if (!handCombinations.containsKey(hand.getName())) {
//                handCombinations.put(hand.getName(), new JSONArray());
//            }
//
//            JSONArray combinations = handCombinations.get(hand.getName());
//            combinations.put(handToJsonConverter.convert(hand));
//        }

//        JSONArray handCombinationsForFile = new JSONArray();
//        for (String name : handCombinations.keySet()) {
//            JSONArray handCombinationsForName = handCombinations.get(name);
//            for (int i = 0; i < handCombinationsForName.length(); i++) {
//                handCombinationsForFile.put(handCombinationsForName.get(i));
//            }
//        }
//
//
//        int filePart = 0;
//        JSONArray subset = new JSONArray();
//        for (int i = 0; i < handCombinationsForFile.length(); i++) {
//            if (i == limitOfNextFile(filePart)) {
//                writeToFile("data", subset, filePart);
//                filePart++;
//                subset = new JSONArray();
//            }
//            subset.put(handCombinationsForFile.get(i));
//        }
//        writeToFile("data", subset, filePart);
    }

    private static int limitOfNextFile(int filePart) {
        return SPLIT_SIZE * (filePart + 1);
    }

    private static void writeToFile(List<Hand> hands) {
        try {
            PrintWriter writer = new PrintWriter("data.csv", "UTF-8");
            writer.println("Name,P1,P2,P3,P4");

            for (Hand hand : hands) {
                writer.println(String.format(
                        "%s,%d,%d,%d,%d",
                        hand.getName(),
                        hand.getPart1(),
                        hand.getPart2(),
                        hand.getPart3(),
                        hand.getPart4()
                ));
            }

            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Unable to write generated file", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unable to write generated file", e);
        }
    }

    private static void writeToFile(String name, JSONArray value, Integer filePart) {
        JSONObject rootJson = new JSONObject();
        rootJson.put("name", name);
        rootJson.put("combinations", value);

        try {
            if (filePart == null) {
                PrintWriter writer = new PrintWriter(String.format("%s.json", name.replaceAll(",", "").replaceAll("'", "").replaceAll(" ", "-").toLowerCase()), "UTF-8");
                writer.print(rootJson);
                writer.close();
            } else {
                PrintWriter writer = new PrintWriter(String.format(
                        "%s-%s.json",
                        name.replaceAll(",", "").replaceAll("'", "").replaceAll(" ", "-").toLowerCase(),
                        filePart
                ), "UTF-8");
                writer.print(rootJson);
                writer.close();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Unable to write generated file", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unable to write generated file", e);
        }
    }
}
