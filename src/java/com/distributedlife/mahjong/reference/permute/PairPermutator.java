package com.distributedlife.mahjong.reference.permute;

import java.util.List;

public class PairPermutator extends MultiplePermutator {
    public PairPermutator(List<String> tilesPairIsAllowedIn, String suit) {
        super(tilesPairIsAllowedIn, 2, suit);
    }
}
