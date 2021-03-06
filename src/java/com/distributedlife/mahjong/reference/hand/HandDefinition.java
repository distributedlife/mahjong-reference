package com.distributedlife.mahjong.reference.hand;

import com.distributedlife.mahjong.reference.permute.Permutator;

import java.util.ArrayList;
import java.util.List;

public class HandDefinition {
    private final String name;
    private final List<String> suits;
    private final List<Permutator> requirements;

    public HandDefinition(String name) {
        this.name = name;
        this.suits = new ArrayList<String>();
        this.requirements = new ArrayList<Permutator>();
    }

    public String getName() {
        return name;
    }

    public List<String> getSuits() {
        return suits;
    }

    public void addSuit(String suit) {
        suits.add(suit);
    }

    public List<Permutator> getRequirements() {
        return requirements;
    }

    public void addRequirement(Permutator permutator) {
        requirements.add(permutator);
    }
}
