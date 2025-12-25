/**
 * HandLibraryBuilder - Generates all valid tile combinations for defined hands
 */
import { HandCandidate } from './hand-candidate.js';

export class HandLibraryBuilder {
  constructor(tileSet, definitions, filters, permutatorExecutor, converter) {
    this.tileSet = tileSet;
    this.definitions = definitions;
    this.filters = filters;
    this.permutatorExecutor = permutatorExecutor;
    this.converter = converter;
  }

  async buildAll() {
    let candidates = [];

    for (const definition of this.definitions) {
      const handCandidates = await this.build(definition);
      candidates = candidates.concat(handCandidates);
    }

    for (const filter of this.filters) {
      candidates = filter.apply(candidates);
    }

    let hands = this.converter.convert(candidates);
    
    // Ensure unique hands
    const uniqueHands = new Map();
    for (const hand of hands) {
      const key = `${hand.name}|${hand.part1}|${hand.part2}|${hand.part3}|${hand.part4}`;
      uniqueHands.set(key, hand);
    }
    
    hands = Array.from(uniqueHands.values());
    
    // Sort hands
    hands.sort((a, b) => a.compareTo(b));

    return hands;
  }

  async build(definition) {
    const candidates = [];

    for (const suit of definition.getSuits()) {
      const candidate = new HandCandidate(
        definition.getName(),
        [...this.tileSet.getTiles()]
      );
      candidate.setPrimarySuit(suit);

      const results = this.permutatorExecutor.runPermutatorsOnCandidate(
        candidate,
        definition.getRequirements()
      );
      candidates.push(...results);
    }

    return candidates;
  }
}
