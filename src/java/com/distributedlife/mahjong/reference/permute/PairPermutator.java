package com.distributedlife.mahjong.reference.permute;

import java.util.List;

public class PairPermutator extends MultiplePermutator {
    public PairPermutator(List<String> tilesPairIsAllowedIn) {
        super(tilesPairIsAllowedIn, 2);
    }
}
