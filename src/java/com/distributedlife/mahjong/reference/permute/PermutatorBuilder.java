package com.distributedlife.mahjong.reference.permute;

public class PermutatorBuilder {
    public Permutator build(PermutatorBuilderOptions options) {
        Permutator permutator = null;

        if (options.getType().equals("run")) {
            if (options.getLength() > 0) {
                permutator = new CombinationSequencePermutator(options.getLength());
            } else {
                permutator = new StandardSequencePermutator(options.getFrom(), options.getTo());
            }
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
        if (options.getType().equals("single")) {
            permutator = new SingleTilePermutator(options.getTile(), "1st");
        }
        if (options.getType().equals("subset")) {
            permutator = new SubsetPermutator(options.getTiles(), options.getLength());
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
