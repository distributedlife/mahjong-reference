/**
 * MixedChowSequencePermutator - Handles mixed chow sequences across suits
 */
import { Permutator } from './permutator.js';
import { createTile, Suits } from '../data/tile-set.js';

export class MixedChowSequencePermutator extends Permutator {
  permute(candidates) {
    const newCandidates = new Set();
    const combinations = this.produceListOfCombinations();

    for (const run of combinations) {
      for (const candidate of candidates) {
        const variation = candidate.fork();

        const from = parseInt(run.from);
        
        let tile = createTile(from, run.suit1);
        if (variation.canAdd(tile)) {
          variation.add(tile);
        }

        tile = createTile(from + 1, run.suit2);
        if (variation.canAdd(tile)) {
          variation.add(tile);
        }

        tile = createTile(from + 2, run.suit3);
        if (variation.canAdd(tile)) {
          variation.add(tile);
        }

        newCandidates.add(variation);
      }
    }

    return newCandidates;
  }

  produceListOfCombinations() {
    const listOfRuns = [];

    // Generate all valid starting positions (1-7, since we need 3 consecutive tiles)
    for (let start = 1; start <= 7; start++) {
      listOfRuns.push({ from: start.toString() });
    }

    // Generate all 6 permutations of the 3 suits
    const listOfMixedRuns = [];
    const suitPermutations = [
      ['Bamboo', 'Spot', 'Crack'],
      ['Bamboo', 'Crack', 'Spot'],
      ['Spot', 'Bamboo', 'Crack'],
      ['Spot', 'Crack', 'Bamboo'],
      ['Crack', 'Spot', 'Bamboo'],
      ['Crack', 'Bamboo', 'Spot']
    ];

    for (const run of listOfRuns) {
      for (const [suit1, suit2, suit3] of suitPermutations) {
        listOfMixedRuns.push({
          from: run.from,
          suit1,
          suit2,
          suit3
        });
      }
    }

    return listOfMixedRuns;
  }
}
