/**
 * MixedPairPermutator - Handles pairs from same rank across different suits
 */
import { Permutator } from './permutator.js';
import { createTile } from '../data/tile-set.js';

export class MixedPairPermutator extends Permutator {
  constructor(tiles) {
    super();
    this.tiles = tiles;
  }

  permute(candidates) {
    const newCandidates = new Set();
    const mixedPairs = this.produceListOfMixedPairs();

    for (const mixedPair of mixedPairs) {
      for (const candidate of candidates) {
        const variation = candidate.fork();

        for (const tile of mixedPair) {
          if (variation.canAdd(tile)) {
            variation.add(tile);
          }
        }

        if (variation.getRequiredTiles().length === candidate.getRequiredTiles().length + 2) {
          newCandidates.add(variation);
        }
      }
    }

    return newCandidates;
  }

  produceListOfMixedPairs() {
    const listOfMixedPairs = [];

    for (const tile of this.tiles) {
      const bamboo = createTile(tile, 'Bamboo');
      const crack = createTile(tile, 'Crack');
      const spot = createTile(tile, 'Spot');

      listOfMixedPairs.push([bamboo, crack]);
      listOfMixedPairs.push([bamboo, spot]);
      listOfMixedPairs.push([crack, spot]);
    }

    return listOfMixedPairs;
  }
}
