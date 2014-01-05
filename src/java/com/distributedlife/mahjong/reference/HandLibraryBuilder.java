package com.distributedlife.mahjong.reference;

import com.distributedlife.mahjong.game.TileSet;
import com.distributedlife.mahjong.hand.Hand;
import com.distributedlife.mahjong.reference.permute.Permutator;

import java.util.ArrayList;
import java.util.List;

public class HandLibraryBuilder {
    private TileSet tileSet;
    private List<HandDefinition> definitions;

    public HandLibraryBuilder(TileSet tileSet, List<HandDefinition> definitions) {
        this.tileSet = tileSet;
        this.definitions = definitions;
    }

    public List<Hand> buildAll() {
        List<HandCandidate> candidates = new ArrayList<HandCandidate>();

        for (HandDefinition definition : definitions) {
            candidates.addAll(build(definition));
        }


        return filterInvalidCandidates(candidates);
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

    private List<HandCandidate> build(HandDefinition definition) {
        List<HandCandidate> candidates = new ArrayList<HandCandidate>();

        for (String suit : definition.getSuits()) {
            HandCandidate candidate = new HandCandidate(definition.getName(), tileSet.getTiles());
            candidate.setPrimarySuit(suit);

            candidates.addAll(runPermutatorsOnCandidate(candidate, definition.getRequirements()));

        }

        return candidates;
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
