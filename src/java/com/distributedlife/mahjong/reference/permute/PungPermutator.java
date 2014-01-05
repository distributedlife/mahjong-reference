package com.distributedlife.mahjong.reference.permute;

import java.util.List;

class PungPermutator extends MultiplePermutator {
    public PungPermutator(List<String> tilesPungIsAllowedIn, String suit) {
        super(tilesPungIsAllowedIn, 3, suit);
    }
}
