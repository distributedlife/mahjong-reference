/**
 * OneEachPermutator - Handles permutations for one of each tile in a set
 */
import { SubsetPermutator } from './subset-permutator.js';

export class OneEachPermutator extends SubsetPermutator {
  constructor(set) {
    super(set, set.length);
  }
}
