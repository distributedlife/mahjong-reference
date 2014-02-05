package com.distributedlife.mahjong.reference.permute;

import java.util.List;

public class OneEachPermutator extends SubsetPermutator {
    public OneEachPermutator(List<String> set) {
        super(set, set.size());
    }
}
