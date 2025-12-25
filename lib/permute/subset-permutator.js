/**
 * SubsetPermutator - Handles permutations for subsets of tiles
 */
import { Permutator } from './permutator.js';

export class SubsetPermutator extends Permutator {
  constructor(set, subsetLength) {
    super();
    this.subsetLength = subsetLength;
    
    // Initialize with single-element subsets
    let subsets = set.map(element => [element]);
    
    // Generate all subsets of the specified length
    this.subsets = this.generateSubsets(subsets, set, subsetLength - 1);
  }

  generateSubsets(subsets, set, length) {
    if (length === 0) {
      return subsets;
    }

    const newSubsets = [];
    for (const subset of subsets) {
      for (const element of set) {
        if (subset.includes(element)) {
          continue;
        }

        const newSubset = [...subset, element];
        newSubsets.push(newSubset);
      }
    }

    return this.generateSubsets(newSubsets, set, length - 1);
  }

  permute(candidates) {
    const newCandidates = new Set();

    for (const subset of this.subsets) {
      for (const candidate of candidates) {
        const variation = candidate.fork();

        for (const tile of subset) {
          if (variation.canAdd(tile)) {
            variation.add(tile);
          }
        }

        if (candidate.getRequiredTiles().length + this.subsetLength === variation.getRequiredTiles().length) {
          newCandidates.add(variation);
        }
      }
    }

    return newCandidates;
  }
}
