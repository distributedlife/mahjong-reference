/**
 * Tests for MixedPairPermutator
 */
import { describe, it } from 'node:test';
import assert from 'node:assert';
import { MixedPairPermutator } from '../../lib/permute/mixed-pair-permutator.js';
import { HandCandidate } from '../../lib/hand/hand-candidate.js';
import { TileSet } from '../../lib/data/tile-set.js';

describe('MixedPairPermutator', () => {
  it('should create mixed pairs from same rank', () => {
    const tiles = ['2'];
    const permutator = new MixedPairPermutator(tiles);
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Bamboo');
    
    const results = permutator.permute(new Set([candidate]));
    
    // Should create 3 combinations: Bamboo+Crack, Bamboo+Spot, Crack+Spot
    assert.strictEqual(results.size, 3);
    
    for (const result of results) {
      assert.strictEqual(result.getRequiredTiles().length, 2);
    }
  });

  it('should create all suit combinations', () => {
    const tiles = ['7'];
    const permutator = new MixedPairPermutator(tiles);
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Spot');
    
    const results = permutator.permute(new Set([candidate]));
    
    const resultsArray = Array.from(results);
    assert.strictEqual(resultsArray.length, 3);
    
    // Check all three combinations exist
    const tileCombos = resultsArray.map(r => r.getRequiredTiles().sort().join(','));
    assert.ok(tileCombos.some(c => c.includes('7 Bamboo') && c.includes('7 Crack')));
    assert.ok(tileCombos.some(c => c.includes('7 Bamboo') && c.includes('7 Spot')));
    assert.ok(tileCombos.some(c => c.includes('7 Crack') && c.includes('7 Spot')));
  });

  it('should work with multiple tiles', () => {
    const tiles = ['3', '4'];
    const permutator = new MixedPairPermutator(tiles);
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Crack');
    
    const results = permutator.permute(new Set([candidate]));
    
    // 3 combinations for each tile = 6 total
    assert.strictEqual(results.size, 6);
  });

  it('should not create pair if tiles unavailable', () => {
    const tiles = ['1'];
    const permutator = new MixedPairPermutator(tiles);
    
    // Only one 1 available
    const candidate = new HandCandidate('Test', ['1 Bamboo']);
    candidate.setPrimarySuit('Bamboo');
    
    const results = permutator.permute(new Set([candidate]));
    
    assert.strictEqual(results.size, 0);
  });
});
