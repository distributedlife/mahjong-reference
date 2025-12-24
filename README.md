# Mahjong Reference

A Java library for generating and validating Mahjong hands. This repository contains a comprehensive reference implementation for permuting and identifying valid Mahjong hands based on predefined hand patterns.

## Overview

This library provides tools to:
- Define Mahjong hand patterns using JSON configurations
- Generate all valid tile combinations for specific hand types
- Validate whether a set of tiles matches a known hand pattern
- Work with different Mahjong tile suits (Bamboo, Spot, Crack) and honor tiles (Winds and Dragons)

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

## Structure

```
src/
├── java/com/distributedlife/mahjong/reference/
│   ├── hand/              # Hand definitions and library builder
│   ├── permute/           # Permutation algorithms for different tile patterns
│   ├── adapter/           # Converters between hand representations
│   ├── data/              # Tile set definitions
│   └── filter/            # Hand candidate filters
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
- `knitted-pair` - Special pairing pattern
- `subset` - A subset of specified tiles
- `one-each` - One tile from each specified type

## Tiles

The library supports the standard Mahjong tile set:

- **Number tiles**: 1-9 in three suits (Bamboo, Spot, Crack)
- **Honor tiles**: 
  - Winds: North, East, South, West
  - Dragons: Red, Green, White

## Core Classes

- **Hand**: Represents a complete Mahjong hand using bit-field compression
- **HandDefinition**: Defines the pattern/requirements for a specific hand type
- **HandLibraryBuilder**: Generates all valid tile combinations for defined hands
- **HandCandidate**: Intermediate representation during hand generation
- **Permutator**: Abstract class for different tile permutation strategies

## Use Cases

This library is useful for:
- Building Mahjong scoring applications
- Creating Mahjong learning tools
- Validating player hands in digital Mahjong games
- Generating reference data for Mahjong hand patterns
- Understanding the mathematical combinations in Mahjong

## License

This project appears to be open source. Please check with the repository owner for specific licensing terms.
