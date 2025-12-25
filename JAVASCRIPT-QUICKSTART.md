# JavaScript Implementation - Quick Start Guide

This guide will help you get started with the modern JavaScript implementation of the Mahjong Reference Generator.

## Prerequisites

- Node.js 18.0.0 or higher

## Installation

No external dependencies are required! Simply clone the repository and you're ready to go.

```bash
git clone https://github.com/distributedlife/mahjong-reference.git
cd mahjong-reference
```

## Running Tests

```bash
npm test
```

All tests should pass:
- 12 unit tests covering TileSet, Hand, and HandCandidate
- 3 test suites
- 100% pass rate

## Quick Examples

### 1. Run the Minimal Generation Test

This demonstrates the library working with a simple hand:

```bash
node lib/console/test-minimal-generation.js
```

Expected output:
```
Testing minimal hand generation...
Building hands...
Generated 1 unique hand combinations in 1ms
Sample hands:
  1. Test Simple Hand: 1 Bamboo,2 Bamboo,3 Bamboo...
Test PASSED! The JavaScript implementation works correctly.
```

### 2. Use the Library Programmatically

```javascript
import { TileSet, HandDefinition, HandLibraryBuilder } from './lib/index.js';
import { StandardSequencePermutator, PungPermutator, PairPermutator } from './lib/index.js';
import { InvalidHandCandidateFilter, DuplicateHandCandidateFilter } from './lib/index.js';
import { PermutatorExecutor } from './lib/index.js';
import { HandCandidateToHandConverter } from './lib/index.js';

// Define a simple hand pattern
const requirements = [
  new StandardSequencePermutator(1, 9, '1st'),  // 1-9 run
  new PungPermutator(['1'], '1st'),              // Pung of 1s
  new PairPermutator(['2'], '1st')               // Pair of 2s
];

const handDefinition = new HandDefinition('My Hand', ['Bamboo'], requirements);

// Set up the builder
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

// Generate all valid combinations
const hands = await builder.buildAll();
console.log(`Generated ${hands.length} hands`);

// Display the hands
for (const hand of hands) {
  console.log(`${hand.getName()}: ${hand.toString()}`);
}
```

## Permutator Types Available

The implementation includes all permutator types:

1. **pair** - Two identical tiles
2. **pung** - Three identical tiles  
3. **run** - Consecutive sequence of tiles
4. **single** - Single tile
5. **mixed-chow** - Sequences across multiple suits
6. **mixed-pung** - Pungs from same rank across multiple suits
7. **mixed-pair** - Pairs from same rank across different suits
8. **subset** - A subset of specified tiles
9. **one-each** - One tile from each specified type
10. **knitted-pair** - Special pairing pattern
11. **any-paired** - Pair any tile already in hand

## Project Structure

```
lib/
├── adapter/                    # Converters
│   └── hand-candidate-to-hand-converter.js
├── console/                    # Console applications
│   ├── generate-hands.js       # Full generator (requires significant memory)
│   ├── test-minimal-generation.js
│   └── test-simple-generation.js
├── data/                       # Tile definitions
│   └── tile-set.js
├── filter/                     # Filters
│   ├── duplicate-hand-candidate-filter.js
│   └── invalid-hand-candidate-filter.js
├── hand/                       # Hand classes
│   ├── hand.js
│   ├── hand-candidate.js
│   ├── hand-definition.js
│   └── hand-library-builder.js
├── json/                       # JSON parsing
│   └── json-to-hand-definition.js
├── permute/                    # Permutators (15 files)
│   ├── permutator.js          # Base class
│   ├── multiple-permutator.js
│   ├── pair-permutator.js
│   ├── pung-permutator.js
│   ├── standard-sequence-permutator.js
│   ├── single-tile-permutator.js
│   ├── mixed-chow-sequence-permutator.js
│   ├── mixed-pung-permutator.js
│   ├── mixed-pair-permutator.js
│   ├── subset-permutator.js
│   ├── one-each-permutator.js
│   ├── knitted-pair-permutator.js
│   ├── any-paired-permutator.js
│   ├── permutator-builder.js
│   └── permutator-executor.js
└── index.js                    # Main export file
```

## Modern JavaScript Features Used

- **ES6 Modules**: `import`/`export` syntax
- **Async/Await**: Asynchronous processing
- **Classes**: Object-oriented design
- **Arrow Functions**: Concise syntax
- **Template Literals**: String interpolation
- **Destructuring**: Clean variable extraction
- **Spread Operator**: Array operations
- **Array Methods**: `map()`, `filter()`, list comprehensions
- **BigInt**: 64-bit integer support for bit operations
- **Set**: Unique collections
- **for...of**: Modern iteration

## Performance Notes

The full generation of all 67 hand definitions can generate millions of combinations and requires significant memory (4GB+). For production use:

1. Process hands in smaller batches
2. Increase Node.js heap: `node --max-old-space-size=8192 lib/console/generate-hands.js`
3. Cache results to disk
4. Consider streaming/pagination for large datasets

## Troubleshooting

**Out of Memory Error**: The full generation requires significant memory. Use the test scripts with simpler hands instead, or increase Node's heap size.

**Module Not Found**: Ensure you're using Node.js 18+ with ES modules support.

**Tests Failing**: Run `npm test` to verify your environment is set up correctly.

## Next Steps

- Explore the test files in `test/` for more examples
- Read the comprehensive README.md for detailed documentation
- Check out the Java implementation in `src/java/` for comparison
- Review hand definitions in `src/resources/all.json`

## Support

For issues or questions, please create an issue on the GitHub repository.
