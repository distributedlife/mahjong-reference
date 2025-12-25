# Mahjong Reference

A library for generating and validating Mahjong hands. This repository contains comprehensive reference implementations in both **Java** and **modern JavaScript (ES6+)** for permuting and identifying valid Mahjong hands based on predefined hand patterns.

## Overview

This library provides tools to:
- Define Mahjong hand patterns using JSON configurations
- Generate all valid tile combinations for specific hand types
- Validate whether a set of tiles matches a known hand pattern
- Work with different Mahjong tile suits (Bamboo, Spot, Crack) and honor tiles (Winds and Dragons)

## Implementations

### Java Implementation

The original Java implementation is located in `src/java/`. See the Java-specific documentation below for build and usage instructions.

### JavaScript Implementation (NEW!)

A modern JavaScript implementation using ES6+ features is now available in `lib/`. This implementation features:

- **ES6 Modules**: Clean, modern module system
- **Async/Await**: Asynchronous processing support
- **BigInt**: For efficient bit-field operations
- **Modern Syntax**: Classes, arrow functions, template literals, destructuring, spread operators
- **List Comprehensions**: Using `map()`, `filter()`, and array methods
- **No External Dependencies**: Pure JavaScript implementation

#### Getting Started with JavaScript

```bash
# Install Node.js 18+ if not already installed

# Run tests
npm test

# Run a minimal generation example
node lib/console/test-minimal-generation.js

# Note: Full generation (all 67 hands) requires significant memory
# For production use, consider processing hands in batches
```

#### JavaScript API Example

```javascript
import { TileSet, HandLibraryBuilder, HandDefinition } from './lib/index.js';
import { StandardSequencePermutator, PungPermutator, PairPermutator } from './lib/index.js';

// Create a hand definition
const requirements = [
  new StandardSequencePermutator(1, 9, '1st'),  // 1-9 run
  new PungPermutator(['1'], '1st'),              // Pung of 1s
  new PairPermutator(['2'], '1st')               // Pair of 2s
];

const handDefinition = new HandDefinition('My Hand', ['Bamboo'], requirements);

// Build all valid combinations
const builder = new HandLibraryBuilder(
  new TileSet(),
  [handDefinition],
  filters,
  new PermutatorExecutor(),
  new HandCandidateToHandConverter()
);

const hands = await builder.buildAll();
console.log(`Generated ${hands.length} hands`);
```

#### JavaScript Project Structure

```
lib/
├── adapter/          # Converters between hand representations
├── console/          # Console applications and test scripts
├── data/             # Tile set definitions
├── filter/           # Hand candidate filters
├── hand/             # Hand definitions and library builder
├── json/             # JSON parsing
├── permute/          # Permutation algorithms
└── index.js          # Main export file

test/
├── tile-set.test.js
├── hand.test.js
└── hand-candidate.test.js
```

## Features

- **Hand Library**: Contains 60+ different Mahjong hand patterns including:
  - Traditional Chinese hands (e.g., "The Thirteen Unique Wonders", "Three Great Scholars")
  - British variations
  - Special hands like "Imperial Jade", "Gates of Heaven", "Dragon's Tail", etc.

- **Permutation System**: Advanced permutation algorithms that can generate all valid tile combinations based on hand requirements:
  - Standard sequences (runs/chows)
  - Pungs (triplets)
  - Pairs
  - Mixed combinations across multiple suits
  - Special patterns (knitted, subset, one-each)

- **Efficient Storage**: Uses bit-field compression to store hand configurations efficiently, allowing fast matching and comparison

## Java Structure

```
src/
├── java/com/distributedlife/mahjong/reference/
│   ├── adapter/           # Converters between hand representations
│   ├── console/           # Console application components
│   ├── data/              # Tile set definitions
│   ├── filter/            # Hand candidate filters
│   ├── hand/              # Hand definitions and library builder
│   ├── json/              # JSON parsing and serialization
│   ├── node/              # Node-related structures
│   └── permute/           # Permutation algorithms for different tile patterns
└── resources/
    ├── all.json           # Master configuration file with all hand definitions
    └── hands/             # Individual JSON files for each hand pattern

test/
├── java/                  # Unit tests
└── resource/              # Test fixtures for specific hands
```

## Hand Definition Format

Hands are defined in JSON format with the following structure:

```javascript
{
    name: "Hand Name",
    suits: ["Bamboo", "Spot", "Crack"],
    requirements: [
        {type: "run", from: 1, to: 9},
        {type: "pung", tiles: ["Red", "Green", "White"]},
        {type: "pair", tiles: ["North", "East", "West", "South"]}
    ]
}
```

### Requirement Types

- `run` - Consecutive sequence of tiles (chow)
- `pung` - Three identical tiles
- `pair` - Two identical tiles
- `mixed-chow` - Sequences across multiple suits
- `mixed-pung` - Pungs from the same rank across multiple suits
- `mixed-pair` - Pairs from the same rank across different suits
- `knitted-pair` - Special pairing pattern
- `subset` - A subset of specified tiles
- `one-each` - One tile from each specified type
- `any-paired` - Pair any tile already in hand
- `single` - Single tile

## Tiles

The library supports the standard Mahjong tile set:

- **Number tiles**: 1-9 in three suits (Bamboo, Spot, Crack)
- **Honor tiles**: 
  - Winds: North, East, South, West, OwnWind
  - Dragons: Red, Green, White

## Core Classes

### JavaScript
- **Hand**: Represents a complete Mahjong hand using bit-field compression
- **HandDefinition**: Defines the pattern/requirements for a specific hand type
- **HandLibraryBuilder**: Generates all valid tile combinations for defined hands
- **HandCandidate**: Intermediate representation during hand generation
- **Permutator**: Abstract class for different tile permutation strategies

### Java
- Same core classes as JavaScript with similar functionality

## Use Cases

This library is useful for:
- Building Mahjong scoring applications
- Creating Mahjong learning tools
- Validating player hands in digital Mahjong games
- Generating reference data for Mahjong hand patterns
- Understanding the mathematical combinations in Mahjong

## Performance Considerations

The full generation of all 67 hands can produce millions of combinations and requires significant memory. For production use:

- Process hands in smaller batches
- Use streaming or pagination for large datasets
- Consider running with increased Node.js heap size: `node --max-old-space-size=8192`
- Cache generated results to disk

## Testing

**JavaScript:**
```bash
npm test
```

**Java:**
Use your preferred Java test runner (JUnit, etc.)

## License

This project appears to be open source. Please check with the repository owner for specific licensing terms.
