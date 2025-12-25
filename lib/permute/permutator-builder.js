/**
 * PermutatorBuilder - Builds permutators based on requirement types
 */
import { PairPermutator } from './pair-permutator.js';
import { PungPermutator } from './pung-permutator.js';
import { StandardSequencePermutator } from './standard-sequence-permutator.js';

export class PermutatorBuilder {
  build(requirement, suit) {
    const type = requirement.type;

    if (type === 'pair') {
      return new PairPermutator(requirement.tiles, suit);
    } else if (type === 'pung') {
      return new PungPermutator(requirement.tiles, suit);
    } else if (type === 'run') {
      return new StandardSequencePermutator(requirement.from, requirement.to, suit);
    }

    // Add more permutator types as needed
    throw new Error(`Unknown permutator type: ${type}`);
  }
}
