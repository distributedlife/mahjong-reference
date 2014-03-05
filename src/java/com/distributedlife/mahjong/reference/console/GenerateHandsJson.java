package com.distributedlife.mahjong.reference.console;

import com.distributedlife.mahjong.reference.adapter.HandCandidateToAHandConverter;
import com.distributedlife.mahjong.reference.data.TileSet;
import com.distributedlife.mahjong.reference.filter.DuplicateHandCandidateFilter;
import com.distributedlife.mahjong.reference.filter.HandCandidateFilter;
import com.distributedlife.mahjong.reference.filter.InvalidHandCandidateFilter;
import com.distributedlife.mahjong.reference.hand.Hand;
import com.distributedlife.mahjong.reference.hand.HandLibraryBuilder;
import com.distributedlife.mahjong.reference.json.JsonToHandDefinition;
import com.distributedlife.mahjong.reference.json.JsonToPermutatorOptionsConverter;
import com.distributedlife.mahjong.reference.json.TreeToJsonAdapter;
import com.distributedlife.mahjong.reference.node.ArrayToTreeAdapter;
import com.distributedlife.mahjong.reference.node.HandNode;
import com.distributedlife.mahjong.reference.permute.PermutatorBuilder;
import com.distributedlife.mahjong.reference.permute.PermutatorExecutor;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        ArrayToTreeAdapter arrayToTreeAdapter = new ArrayToTreeAdapter();
        Map<String, HandNode> allHandsTree = new HashMap<String, HandNode>();
        for (Hand hand : builder.buildAll()) {
            HandNode root = allHandsTree.get(hand.getName());
            if (root == null) {
                allHandsTree.put(hand.getName(), new HandNode(hand.getName()));
                root = allHandsTree.get(hand.getName());
            }

            arrayToTreeAdapter.adapt(hand.getRequiredTiles(), root);
        }

        TreeToJsonAdapter treeToJsonAdapter = new TreeToJsonAdapter();
        for (String handName : allHandsTree.keySet()) {
            handsJson.put(treeToJsonAdapter.convert(allHandsTree.get(handName)));
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
