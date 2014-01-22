package com.distributedlife.mahjong.reference.hand;

import com.distributedlife.mahjong.reference.data.TileSet;
import com.distributedlife.mahjong.reference.adapter.HandCandidateToAHandConverter;
import com.distributedlife.mahjong.reference.filter.HandCandidateFilter;
import com.distributedlife.mahjong.reference.permute.PermutatorExecutor;

import java.util.ArrayList;
import java.util.List;

class HandLibraryBuilder {
    private List<HandCandidateFilter> filters;
    private final PermutatorExecutor permutatorExecutor;
    private final TileSet tileSet;
    private final List<HandDefinition> definitions;
    private HandCandidateToAHandConverter converter;

    public HandLibraryBuilder(TileSet tileSet, List<HandDefinition> definitions, List<HandCandidateFilter> filters, PermutatorExecutor permutatorExecutor, HandCandidateToAHandConverter converter) {
        this.tileSet = tileSet;
        this.definitions = definitions;
        this.filters = filters;
        this.permutatorExecutor = permutatorExecutor;
        this.converter = converter;
    }

    public List<Hand> buildAll() {
        List<HandCandidate> candidates = new ArrayList<HandCandidate>();

        for (HandDefinition definition : definitions) {
            candidates.addAll(build(definition));
        }

        for (HandCandidateFilter filter : filters) {
            candidates =  filter.apply(candidates);
        }

        return converter.convert(candidates);
    }

    private List<HandCandidate> build(HandDefinition definition) {
        List<HandCandidate> candidates = new ArrayList<HandCandidate>();

        for (String suit : definition.getSuits()) {
            HandCandidate candidate = new HandCandidate(definition.getName(), new ArrayList<String>(tileSet.getTiles()));
            candidate.setPrimarySuit(suit);

            candidates.addAll(permutatorExecutor.runPermutatorsOnCandidate(candidate, definition.getRequirements()));
        }

        return candidates;
    }
}
