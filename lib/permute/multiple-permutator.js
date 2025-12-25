/**
 * MultiplePermutator - Handles permutations for pairs, pungs, and kongs
 */
import { Permutator } from './permutator.js';
import { convertTileToAppropriateSuit } from '../data/tile-set.js';
import { times } from '../hand/hand-candidate.js';

export class MultiplePermutator extends Permutator {
  constructor(tilesMultipleIsAllowedIn, multiples, defaultSuit) {
    super();
    this.tilesMultipleIsAllowedIn = tilesMultipleIsAllowedIn;
    this.multiples = multiples;
    this.defaultSuit = defaultSuit;
  }

  permute(candidates, suit = null) {
    const useSuit = suit || this.defaultSuit;
    const handCandidates = new Set();

    for (const candidate of candidates) {
      for (const tile of this.tilesMultipleIsAllowedIn) {
        const tileInSuit = convertTileToAppropriateSuit(useSuit, tile, candidate);

        if (candidate.canAdd(tileInSuit, times(this.multiples))) {
          const variation = candidate.fork();
          variation.add(tileInSuit, times(this.multiples));
          handCandidates.add(variation);
        }
      }
    }

    return handCandidates;
  }
}
