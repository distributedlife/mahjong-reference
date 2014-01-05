package com.distributedlife.mahjong.reference.permute;

import java.util.Arrays;

public class SingleTilePermutator extends MultiplePermutator {
    public SingleTilePermutator(String tile, String suit) {
        super(Arrays.asList(tile), 1, suit);
    }
}
