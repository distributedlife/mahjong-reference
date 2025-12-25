/**
 * PairPermutator - Handles permutations for pairs (2 tiles)
 */
import { MultiplePermutator } from './multiple-permutator.js';

export class PairPermutator extends MultiplePermutator {
  constructor(tilesPairIsAllowedIn, suit) {
    super(tilesPairIsAllowedIn, 2, suit);
  }
}
