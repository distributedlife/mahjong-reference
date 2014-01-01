package com.distributedlife.mahjong.reference;

import com.distributedlife.mahjong.game.TileSet;
import com.distributedlife.mahjong.hand.Hand;
import com.distributedlife.mahjong.reference.permute.PairPermutator;
import com.distributedlife.mahjong.reference.permute.Permutator;
import com.distributedlife.mahjong.reference.permute.PungPermutator;
import com.distributedlife.mahjong.reference.permute.SequencePermutator;

import java.util.ArrayList;
import java.util.List;

import static com.distributedlife.mahjong.reference.permute.SequencePermutator.createTile;

public class HandLibraryBuilder {
    private TileSet tileSet;

    public HandLibraryBuilder(TileSet tileSet) {
        this.tileSet = tileSet;
    }

    public List<Hand> buildAll() {
        List<HandCandidate> candidates = new ArrayList<HandCandidate>();


        candidates.addAll(buildRunPungAndAPair(allSuits()));


        return filterInvalidCandidates(candidates);
    }

    private List<TileSet.Suits> allSuits() {
        List<TileSet.Suits> suits = new ArrayList<TileSet.Suits>();
        for(TileSet.Suits suit : TileSet.Suits.values()) {
            suits.add(suit);
        }
        return suits;
    }

    private List<Hand> filterInvalidCandidates(List<HandCandidate> candidates) {
        List<Hand> hands = new ArrayList<Hand>();
        for (HandCandidate candidate : candidates) {
            if (!candidate.isValid()) {
                continue;
            }

            hands.add(new Hand(candidate.getName(), candidate.getRequiredTiles()));
        }

        return hands;
    }

    private List<HandCandidate> buildRunPungAndAPair(List<TileSet.Suits> suits) {
        List<HandCandidate> candidates = new ArrayList<HandCandidate>();

        for (TileSet.Suits suit : suits) {
            HandCandidate candidate = new HandCandidate("Run, Pung and a Pair", tileSet.getTiles());
            candidate.setPrimarySuit(suit);

            candidates.addAll(buildRunPungAndAPairForSuit(candidate));
        }

        return candidates;
    }

    private List<HandCandidate> buildRunPungAndAPairForSuit(HandCandidate candidate) {

        List<String> tilesPungAndPairAreAllowedIn = new ArrayList<String>();
        for (int i = 1; i <= 9; i++) {
            tilesPungAndPairAreAllowedIn.add(createTile(i, candidate.getPrimarySuit()));
        }

        List<Permutator> permutators = new ArrayList<Permutator>();
        permutators.add(new SequencePermutator(1, 9));
        permutators.add(new PungPermutator(tilesPungAndPairAreAllowedIn));
        permutators.add(new PairPermutator(tilesPungAndPairAreAllowedIn));


        return runPermutatorsOnCandidate(candidate, permutators);
    }

    private List<HandCandidate> runPermutatorsOnCandidate(HandCandidate candidate, List<Permutator> permutators) {
        List<HandCandidate> candidates = new ArrayList<HandCandidate>();
        candidates.add(candidate);

        for (Permutator permutator : permutators) {
            candidates = permutator.permute(candidates);
        }

        return candidates;
    }
}
