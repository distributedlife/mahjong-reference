package com.distributedlife.mahjong.reference.hand;

import com.distributedlife.mahjong.game.TileSet;
import com.distributedlife.mahjong.hand.Hand;
import com.distributedlife.mahjong.reference.filter.InvalidHandCandidateFilter;
import com.distributedlife.mahjong.reference.permute.Permutator;

import java.util.ArrayList;
import java.util.List;

public class HandLibraryBuilder {
    private InvalidHandCandidateFilter filter;
    private TileSet tileSet;
    private List<HandDefinition> definitions;

    public HandLibraryBuilder(TileSet tileSet, List<HandDefinition> definitions, InvalidHandCandidateFilter filter) {
        this.tileSet = tileSet;
        this.definitions = definitions;
        this.filter = filter;
    }

    public List<Hand> buildAll() {
        List<HandCandidate> candidates = new ArrayList<HandCandidate>();

        for (HandDefinition definition : definitions) {
            candidates.addAll(build(definition));
        }

        return filter.apply(candidates);
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

    //todo: extract
    private List<HandCandidate> runPermutatorsOnCandidate(HandCandidate candidate, List<Permutator> permutators) {
        List<HandCandidate> candidates = new ArrayList<HandCandidate>();
        candidates.add(candidate);

        for (Permutator permutator : permutators) {
            candidates = permutator.permute(candidates);
        }

        return candidates;
    }
}
