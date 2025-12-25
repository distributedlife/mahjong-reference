/**
 * Tests for PairPermutator
 */
import { describe, it } from 'node:test';
import assert from 'node:assert';
import { PairPermutator } from '../../lib/permute/pair-permutator.js';
import { HandCandidate } from '../../lib/hand/hand-candidate.js';
import { TileSet } from '../../lib/data/tile-set.js';

describe('PairPermutator', () => {
  it('should create pairs from allowed tiles', () => {
    const tiles = ['1', '2', '3'];
    const permutator = new PairPermutator(tiles, '1st');
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Bamboo');
    
    const results = permutator.permute(new Set([candidate]));
    
    assert.ok(results.size > 0);
    // Should create variations with pairs of 1, 2, or 3 Bamboo
    const resultsArray = Array.from(results);
    assert.ok(resultsArray.some(c => c.getRequiredTiles().length === 2));
  });

  it('should add exactly 2 tiles per variation', () => {
    const tiles = ['5'];
    const permutator = new PairPermutator(tiles, '1st');
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Spot');
    
    const results = permutator.permute(new Set([candidate]));
    
    for (const result of results) {
      assert.strictEqual(result.getRequiredTiles().length, 2);
      assert.ok(result.getRequiredTiles().every(t => t === '5 Spot'));
    }
  });

  it('should respect tile availability', () => {
    const tiles = ['9'];
    const permutator = new PairPermutator(tiles, '1st');
    
    // Create candidate with limited tiles
    const candidate = new HandCandidate('Test', ['9 Crack', '9 Crack']);
    candidate.setPrimarySuit('Crack');
    
    const results = permutator.permute(new Set([candidate]));
    
    assert.strictEqual(results.size, 1);
    const result = Array.from(results)[0];
    assert.strictEqual(result.getRequiredTiles().length, 2);
  });

  it('should not create variations when tiles unavailable', () => {
    const tiles = ['8'];
    const permutator = new PairPermutator(tiles, '1st');
    
    // Create candidate without the needed tiles
    const candidate = new HandCandidate('Test', ['1 Bamboo']);
    candidate.setPrimarySuit('Bamboo');
    
    const results = permutator.permute(new Set([candidate]));
    
    assert.strictEqual(results.size, 0);
  });
});
