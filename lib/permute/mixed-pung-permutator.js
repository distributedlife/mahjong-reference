/**
 * MixedPungPermutator - Handles pungs from same rank across multiple suits
 */
import { Permutator } from './permutator.js';
import { createTile } from '../data/tile-set.js';

export class MixedPungPermutator extends Permutator {
  constructor(tiles) {
    super();
    this.tiles = tiles;
  }

  permute(candidates) {
    const newCandidates = new Set();
    const mixedPungs = this.produceListOfMixedPungs();

    for (const mixedPung of mixedPungs) {
      for (const candidate of candidates) {
        const variation = candidate.fork();

        for (const tile of mixedPung) {
          if (variation.canAdd(tile)) {
            variation.add(tile);
          }
        }

        if (variation.getRequiredTiles().length === candidate.getRequiredTiles().length + 3) {
          newCandidates.add(variation);
        }
      }
    }

    return newCandidates;
  }

  produceListOfMixedPungs() {
    const listOfMixedPungs = [];

    for (const tile of this.tiles) {
      const bamboo = createTile(tile, 'Bamboo');
      const crack = createTile(tile, 'Crack');
      const spot = createTile(tile, 'Spot');

      listOfMixedPungs.push([bamboo, crack, spot]);
    }

    return listOfMixedPungs;
  }
}
