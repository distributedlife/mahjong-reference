package com.distributedlife.mahjong.reference.node;

import java.util.List;

public class ArrayToTreeAdapter {
    public HandNode adapt(List<String> tiles) {
        HandNode root = new HandNode("root");

        HandNode currentNode = root;
        for (String tile : tiles) {
            if(!currentNode.hasChild(tile)) {
                currentNode.addChild(tile);
            }

            currentNode = currentNode.getChild(tile);
        }

        return root;
    }

    public HandNode adapt(List<String> tiles, HandNode root) {
        HandNode currentNode = root;

        for (String tile : tiles) {
            if(!currentNode.hasChild(tile)) {
                currentNode.addChild(tile);
            }

            currentNode = currentNode.getChild(tile);
        }

        return root;
    }
}
