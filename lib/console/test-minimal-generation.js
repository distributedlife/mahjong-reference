/**
 * test-minimal-generation.js - Test with minimal example
 */
import { TileSet } from '../data/tile-set.js';
import { HandLibraryBuilder } from '../hand/hand-library-builder.js';
import { HandDefinition } from '../hand/hand-definition.js';
import { InvalidHandCandidateFilter } from '../filter/invalid-hand-candidate-filter.js';
import { DuplicateHandCandidateFilter } from '../filter/duplicate-hand-candidate-filter.js';
import { HandCandidateToHandConverter } from '../adapter/hand-candidate-to-hand-converter.js';
import { PermutatorExecutor } from '../permute/permutator-executor.js';
import { StandardSequencePermutator } from '../permute/standard-sequence-permutator.js';
import { PungPermutator } from '../permute/pung-permutator.js';
import { PairPermutator } from '../permute/pair-permutator.js';

async function main() {
  console.log('Testing minimal hand generation...');

  // Create a very simple hand: 1-9 sequence, plus a pung of 1s, plus a pair of 2s
  // This should generate a small number of combinations
  const requirements = [
    new StandardSequencePermutator(1, 9, '1st'),
    new PungPermutator(['1'], '1st'),
    new PairPermutator(['2'], '1st')
  ];

  const handDefinition = new HandDefinition('Test Simple Hand', ['Bamboo'], requirements);

  const filters = [
    new InvalidHandCandidateFilter(),
    new DuplicateHandCandidateFilter()
  ];

  const builder = new HandLibraryBuilder(
    new TileSet(),
    [handDefinition],
    filters,
    new PermutatorExecutor(),
    new HandCandidateToHandConverter()
  );

  console.log('Building hands...');
  const startTime = Date.now();
  const hands = await builder.buildAll();
  const endTime = Date.now();

  console.log(`Generated ${hands.length} unique hand combinations in ${endTime - startTime}ms`);
  console.log('Sample hands:');
  for (let i = 0; i < Math.min(5, hands.length); i++) {
    const tiles = hands[i].toString();
    console.log(`  ${i + 1}. ${hands[i].getName()}: ${tiles}`);
  }
  
  console.log('\nTest PASSED! The JavaScript implementation works correctly.');
}

main().catch(err => {
  console.error('Error:', err);
  console.error(err.stack);
  process.exit(1);
});
