/**
 * SingleTilePermutator - Handles permutations for single tiles
 */
import { MultiplePermutator } from './multiple-permutator.js';

export class SingleTilePermutator extends MultiplePermutator {
  constructor(tile, suit) {
    super([tile], 1, suit);
  }
}
