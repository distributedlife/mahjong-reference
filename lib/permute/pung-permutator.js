/**
 * PungPermutator - Handles permutations for pungs (3 tiles)
 */
import { MultiplePermutator } from './multiple-permutator.js';

export class PungPermutator extends MultiplePermutator {
  constructor(tilesPungIsAllowedIn, suit) {
    super(tilesPungIsAllowedIn, 3, suit);
  }
}
