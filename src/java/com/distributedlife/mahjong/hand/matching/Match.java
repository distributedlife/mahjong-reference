package com.distributedlife.mahjong.hand.matching;

public class Match {
    private final String name;
    private final Integer count;

    public Match(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public Integer getCount() {
        return count;
    }
}
