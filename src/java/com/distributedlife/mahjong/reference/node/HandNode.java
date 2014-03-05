package com.distributedlife.mahjong.reference.node;

import java.util.ArrayList;
import java.util.List;

public class HandNode {
    private String name;
    private List<HandNode> children = new ArrayList<HandNode>();

    public HandNode(String name) {
        this.name = name;
    }

    public boolean hasChild(String name) {
        for (HandNode child : children) {
            if (child.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    public void addChild(String name) {
        children.add(new HandNode(name));
    }

    public String getName() {
        return name;
    }

    public HandNode getChild(String name) {
        for (HandNode child : children) {
            if (child.getName().equals(name)) {
                return child;
            }
        }

        return null;
    }

    public List<HandNode> getChildren() {
        return children;
    }
}
