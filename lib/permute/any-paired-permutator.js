/**
 * AnyPairedPermutator - Adds another of any tile already in the hand
 */
import { Permutator } from './permutator.js';

export class AnyPairedPermutator extends Permutator {
  permute(candidates) {
    const handCandidates = new Set();

    for (const candidate of candidates) {
      for (const tile of candidate.getRequiredTiles()) {
        if (candidate.canAdd(tile)) {
          const variation = candidate.fork();
          variation.add(tile);
          handCandidates.add(variation);
        }
      }
    }

    return handCandidates;
  }
}
