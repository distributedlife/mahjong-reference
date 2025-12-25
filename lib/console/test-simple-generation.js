/**
 * test-simple-generation.js - Test the generator with a simple hand
 */
import { TileSet } from '../data/tile-set.js';
import { HandLibraryBuilder } from '../hand/hand-library-builder.js';
import { HandDefinition } from '../hand/hand-definition.js';
import { InvalidHandCandidateFilter } from '../filter/invalid-hand-candidate-filter.js';
import { DuplicateHandCandidateFilter } from '../filter/duplicate-hand-candidate-filter.js';
import { HandCandidateToHandConverter } from '../adapter/hand-candidate-to-hand-converter.js';
import { PermutatorExecutor } from '../permute/permutator-executor.js';
import { PairPermutator } from '../permute/pair-permutator.js';
import { PungPermutator } from '../permute/pung-permutator.js';

async function main() {
  console.log('Testing simple hand generation...');

  // Create a simple hand definition: 7 pairs of any tiles
  const requirements = [
    new PairPermutator(['1', '2', '3', '4', '5', '6', '7', '8', '9'], '1st'),
    new PairPermutator(['1', '2', '3', '4', '5', '6', '7', '8', '9'], '1st'),
    new PairPermutator(['1', '2', '3', '4', '5', '6', '7', '8', '9'], '1st'),
    new PairPermutator(['1', '2', '3', '4', '5', '6', '7', '8', '9'], '1st'),
    new PairPermutator(['1', '2', '3', '4', '5', '6', '7', '8', '9'], '1st'),
    new PairPermutator(['1', '2', '3', '4', '5', '6', '7', '8', '9'], '1st'),
    new PairPermutator(['1', '2', '3', '4', '5', '6', '7', '8', '9'], '1st')
  ];

  const handDefinition = new HandDefinition('Test All Pairs', ['Bamboo'], requirements);

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
  const hands = await builder.buildAll();

  console.log(`Generated ${hands.length} unique hand combinations`);
  console.log('Sample hands:');
  for (let i = 0; i < Math.min(5, hands.length); i++) {
    console.log(`  ${i + 1}. ${hands[i].getName()}: ${hands[i].toString()}`);
  }
}

main().catch(err => {
  console.error('Error:', err);
  process.exit(1);
});
