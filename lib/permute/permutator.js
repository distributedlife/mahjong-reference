/**
 * Permutator - Abstract base class for different tile permutation strategies
 */

export class Permutator {
  permute(candidates, suit = null) {
    throw new Error('permute must be implemented by subclass');
  }
}
