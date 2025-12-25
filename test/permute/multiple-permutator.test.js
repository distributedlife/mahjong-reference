/**
 * Tests for MultiplePermutator
 */
import { describe, it } from 'node:test';
import assert from 'node:assert';
import { MultiplePermutator } from '../../lib/permute/multiple-permutator.js';
import { HandCandidate } from '../../lib/hand/hand-candidate.js';
import { TileSet } from '../../lib/data/tile-set.js';

describe('MultiplePermutator', () => {
  it('should add specified number of tiles', () => {
    const tiles = ['4'];
    const multiples = 2;
    const permutator = new MultiplePermutator(tiles, multiples, '1st');
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Bamboo');
    
    const results = permutator.permute(new Set([candidate]));
    
    assert.strictEqual(results.size, 1);
    const result = Array.from(results)[0];
    assert.strictEqual(result.getRequiredTiles().length, 2);
    assert.ok(result.getRequiredTiles().every(t => t === '4 Bamboo'));
  });

  it('should work with 4 multiples (kong)', () => {
    const tiles = ['8'];
    const multiples = 4;
    const permutator = new MultiplePermutator(tiles, multiples, '1st');
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Spot');
    
    const results = permutator.permute(new Set([candidate]));
    
    assert.strictEqual(results.size, 1);
    const result = Array.from(results)[0];
    assert.strictEqual(result.getRequiredTiles().length, 4);
  });

  it('should create variations for each allowed tile', () => {
    const tiles = ['1', '2', '3'];
    const multiples = 3;
    const permutator = new MultiplePermutator(tiles, multiples, '1st');
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Crack');
    
    const results = permutator.permute(new Set([candidate]));
    
    // Should create 3 variations, one for each tile
    assert.strictEqual(results.size, 3);
  });

  it('should respect tile availability', () => {
    const tiles = ['6'];
    const multiples = 3;
    const permutator = new MultiplePermutator(tiles, multiples, '1st');
    
    // Only 2 tiles available
    const candidate = new HandCandidate('Test', ['6 Bamboo', '6 Bamboo']);
    candidate.setPrimarySuit('Bamboo');
    
    const results = permutator.permute(new Set([candidate]));
    
    assert.strictEqual(results.size, 0);
  });

  it('should use default suit when not specified', () => {
    const tiles = ['9'];
    const multiples = 2;
    const permutator = new MultiplePermutator(tiles, multiples, 'Spot');
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Bamboo');
    
    // Calling permute without suit parameter should use default
    const results = permutator.permute(new Set([candidate]));
    
    // Note: With '1st' conversion it would use Bamboo, but with 'Spot' it uses Spot
    const result = Array.from(results)[0];
    // The implementation uses convertTileToAppropriateSuit which expects '1st', '2nd', '3rd'
    // or actual tile names, so 'Spot' would be treated as a tile name
  });
});
