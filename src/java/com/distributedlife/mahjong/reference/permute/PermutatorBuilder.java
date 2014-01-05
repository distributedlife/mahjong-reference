package com.distributedlife.mahjong.reference.permute;

public class PermutatorBuilder {
    public Permutator build(PermutatorBuilderOptions options) {
        Permutator permutator = null;

        if (options.getType().equals("run")) {
            permutator = new SequencePermutator(options.getFrom(), options.getTo());
        }
        if (options.getType().equals("pung")) {
            permutator = new PungPermutator(options.getTiles(), options.getSuit());
        }
        if (options.getType().equals("pair")) {
            permutator = new PairPermutator(options.getTiles(), options.getSuit());
        }
        if (options.getType().equals("any-paired")) {
            permutator = new AnyPairedPermutator();
        }
        if (permutator == null) {
            return new UnknownPermutator(options.getType());
        }

        if (options.getSuit().equals("1st")) {
            return permutator;
        } else if (options.getSuit().equals("2nd")) {
            return new SecondSuitPermutator(permutator);
        } else {
            return new ThirdSuitPermutator(permutator);
        }
    }
}
