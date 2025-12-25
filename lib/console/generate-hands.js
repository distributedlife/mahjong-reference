/**
 * generate-hands.js - Main entry point for generating Mahjong hands
 */
import { readFile, writeFile } from 'fs/promises';
import { TileSet } from '../data/tile-set.js';
import { HandLibraryBuilder } from '../hand/hand-library-builder.js';
import { InvalidHandCandidateFilter } from '../filter/invalid-hand-candidate-filter.js';
import { DuplicateHandCandidateFilter } from '../filter/duplicate-hand-candidate-filter.js';
import { HandCandidateToHandConverter } from '../adapter/hand-candidate-to-hand-converter.js';
import { PermutatorExecutor } from '../permute/permutator-executor.js';
import { PermutatorBuilder } from '../permute/permutator-builder.js';
import { JsonToHandDefinition } from '../json/json-to-hand-definition.js';
import { fileURLToPath } from 'url';
import { dirname, join } from 'path';

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

async function main() {
  console.log('Loading hand definitions from all.json...');
  
  // Load the JSON configuration
  const jsonPath = join(__dirname, '../../src/resources/all.json');
  const jsonContent = await readFile(jsonPath, 'utf-8');
  
  // Parse JSON (handling non-standard format with property names without quotes)
  const jsonObject = eval(`(${jsonContent})`);
  
  console.log(`Loaded ${jsonObject.hands?.length || 0} hand definitions`);

  // Set up filters
  const filters = [
    new InvalidHandCandidateFilter(),
    new DuplicateHandCandidateFilter()
  ];

  // Set up builder
  const permutatorBuilder = new PermutatorBuilder();
  const jsonToHandDefinition = new JsonToHandDefinition(permutatorBuilder);
  const handDefinitions = jsonToHandDefinition.getHandDefinitions(jsonObject);

  const builder = new HandLibraryBuilder(
    new TileSet(),
    handDefinitions,
    filters,
    new PermutatorExecutor(),
    new HandCandidateToHandConverter()
  );

  console.log('Building all hand combinations...');
  const hands = await builder.buildAll();
  
  console.log(`Generated ${hands.length} unique hand combinations`);

  // Write to CSV file
  console.log('Writing to data.csv...');
  const csvLines = ['Name,P1,P2,P3,P4'];
  
  for (const hand of hands) {
    csvLines.push(
      `${hand.getName()},${hand.getPart1()},${hand.getPart2()},${hand.getPart3()},${hand.getPart4()}`
    );
  }

  await writeFile('data.csv', csvLines.join('\n'), 'utf-8');
  console.log('Done! Results written to data.csv');
}

main().catch(err => {
  console.error('Error:', err);
  process.exit(1);
});
