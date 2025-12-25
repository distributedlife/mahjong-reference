/**
 * StandardSequencePermutator - Handles permutations for runs/chows (sequences of tiles)
 */
import { Permutator } from './permutator.js';
import { convertTileToAppropriateSuit } from '../data/tile-set.js';

export class StandardSequencePermutator extends Permutator {
  constructor(from, to, defaultSuit) {
    super();
    this.from = from;
    this.to = to;
    this.defaultSuit = defaultSuit;
  }

  permute(candidates, suit = null) {
    const useSuit = suit || this.defaultSuit;
    const handCandidates = new Set();

    for (const candidate of candidates) {
      for (let i = this.from; i <= this.to; i++) {
        const tile = convertTileToAppropriateSuit(useSuit, i.toString(), candidate);

        if (candidate.canAdd(tile)) {
          candidate.add(tile);
        }
      }

      handCandidates.add(candidate);
    }

    return handCandidates;
  }
}
