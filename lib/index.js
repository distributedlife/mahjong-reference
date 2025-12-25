/**
 * Main export file for the mahjong-reference library
 */

// Data
export { TileSet, Tile, BIT_TILES, Winds, Dragons, Suits, getNameForBitTile, getValueForTile, createTile, convertTileToAppropriateSuit } from './data/tile-set.js';

// Hand
export { Hand } from './hand/hand.js';
export { HandCandidate, times } from './hand/hand-candidate.js';
export { HandDefinition } from './hand/hand-definition.js';
export { HandLibraryBuilder } from './hand/hand-library-builder.js';

// Permutators
export { Permutator } from './permute/permutator.js';
export { MultiplePermutator } from './permute/multiple-permutator.js';
export { PairPermutator } from './permute/pair-permutator.js';
export { PungPermutator } from './permute/pung-permutator.js';
export { StandardSequencePermutator } from './permute/standard-sequence-permutator.js';
export { PermutatorExecutor } from './permute/permutator-executor.js';
export { PermutatorBuilder } from './permute/permutator-builder.js';

// Filters
export { InvalidHandCandidateFilter } from './filter/invalid-hand-candidate-filter.js';
export { DuplicateHandCandidateFilter } from './filter/duplicate-hand-candidate-filter.js';

// Adapters
export { HandCandidateToHandConverter } from './adapter/hand-candidate-to-hand-converter.js';

// JSON
export { JsonToHandDefinition } from './json/json-to-hand-definition.js';
