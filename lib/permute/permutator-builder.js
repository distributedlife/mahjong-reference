/**
 * PermutatorBuilder - Builds permutators based on requirement types
 */
import { PairPermutator } from './pair-permutator.js';
import { PungPermutator } from './pung-permutator.js';
import { StandardSequencePermutator } from './standard-sequence-permutator.js';
import { SingleTilePermutator } from './single-tile-permutator.js';
import { MixedChowSequencePermutator } from './mixed-chow-sequence-permutator.js';
import { MixedPungPermutator } from './mixed-pung-permutator.js';
import { MixedPairPermutator } from './mixed-pair-permutator.js';
import { SubsetPermutator } from './subset-permutator.js';
import { OneEachPermutator } from './one-each-permutator.js';
import { KnittedPairPermutator } from './knitted-pair-permutator.js';
import { AnyPairedPermutator } from './any-paired-permutator.js';

export class PermutatorBuilder {
  build(requirement, suit) {
    const type = requirement.type;

    if (type === 'pair') {
      return new PairPermutator(requirement.tiles, suit);
    } else if (type === 'pung') {
      return new PungPermutator(requirement.tiles, suit);
    } else if (type === 'run') {
      return new StandardSequencePermutator(requirement.from, requirement.to, suit);
    } else if (type === 'single') {
      return new SingleTilePermutator(requirement.tile, suit);
    } else if (type === 'mixed-chow') {
      return new MixedChowSequencePermutator();
    } else if (type === 'mixed-pung') {
      return new MixedPungPermutator(requirement.tiles);
    } else if (type === 'mixed-pair') {
      return new MixedPairPermutator(requirement.tiles);
    } else if (type === 'subset') {
      return new SubsetPermutator(requirement.tiles, requirement.length);
    } else if (type === 'one-each') {
      return new OneEachPermutator(requirement.tiles);
    } else if (type === 'knitted-pair') {
      return new KnittedPairPermutator(requirement.tiles);
    } else if (type === 'any-paired') {
      return new AnyPairedPermutator();
    }

    throw new Error(`Unknown permutator type: ${type}`);
  }
}
