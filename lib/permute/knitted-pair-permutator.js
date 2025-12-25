/**
 * KnittedPairPermutator - Handles knitted pairs across suits
 */
import { Permutator } from './permutator.js';
import { createTile } from '../data/tile-set.js';

export class KnittedPairPermutator extends Permutator {
  constructor(tiles) {
    super();
    this.tiles = tiles;
  }

  permute(candidates) {
    const newCandidates = new Set();

    for (const candidate of candidates) {
      const suits = ['Bamboo', 'Crack', 'Spot'].filter(s => s !== candidate.getPrimarySuit());

      for (const tile of this.tiles) {
        const first = createTile(tile, candidate.getPrimarySuit());
        const second = createTile(tile, suits[0]);
        const third = createTile(tile, suits[1]);

        const variation1 = candidate.fork();
        if (variation1.canAdd(first) && variation1.canAdd(second)) {
          variation1.add(first);
          variation1.add(second);
          newCandidates.add(variation1);
        }

        const variation2 = candidate.fork();
        if (variation2.canAdd(first) && variation2.canAdd(third)) {
          variation2.add(first);
          variation2.add(third);
          newCandidates.add(variation2);
        }
      }
    }

    return newCandidates;
  }
}
