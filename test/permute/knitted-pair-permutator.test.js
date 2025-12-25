/**
 * Tests for KnittedPairPermutator
 */
import { describe, it } from 'node:test';
import assert from 'node:assert';
import { KnittedPairPermutator } from '../../lib/permute/knitted-pair-permutator.js';
import { HandCandidate } from '../../lib/hand/hand-candidate.js';
import { TileSet } from '../../lib/data/tile-set.js';

describe('KnittedPairPermutator', () => {
  it('should create knitted pairs across suits', () => {
    const tiles = ['1'];
    const permutator = new KnittedPairPermutator(tiles);
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Bamboo');
    
    const results = permutator.permute(new Set([candidate]));
    
    // Should create 2 variations: Bamboo+Crack and Bamboo+Spot
    assert.strictEqual(results.size, 2);
    
    for (const result of results) {
      assert.strictEqual(result.getRequiredTiles().length, 2);
      // One tile must be from primary suit (Bamboo)
      assert.ok(result.getRequiredTiles().some(t => t.includes('Bamboo')));
    }
  });

  it('should pair primary suit with each other suit', () => {
    const tiles = ['5'];
    const permutator = new KnittedPairPermutator(tiles);
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Spot');
    
    const results = permutator.permute(new Set([candidate]));
    
    assert.strictEqual(results.size, 2);
    const resultsArray = Array.from(results);
    
    // Check combinations
    const tileCombos = resultsArray.map(r => r.getRequiredTiles().sort().join(','));
    assert.ok(tileCombos.some(c => c.includes('5 Spot') && c.includes('5 Bamboo')));
    assert.ok(tileCombos.some(c => c.includes('5 Spot') && c.includes('5 Crack')));
  });

  it('should work with multiple tiles', () => {
    const tiles = ['3', '7'];
    const permutator = new KnittedPairPermutator(tiles);
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Crack');
    
    const results = permutator.permute(new Set([candidate]));
    
    // 2 tiles × 2 other suits = 4 combinations
    assert.strictEqual(results.size, 4);
  });

  it('should respect tile availability', () => {
    const tiles = ['9'];
    const permutator = new KnittedPairPermutator(tiles);
    
    // Only have 9 Bamboo and 9 Crack, missing 9 Spot
    const candidate = new HandCandidate('Test', ['9 Bamboo', '9 Crack']);
    candidate.setPrimarySuit('Bamboo');
    
    const results = permutator.permute(new Set([candidate]));
    
    // Should only create Bamboo+Crack combination
    assert.strictEqual(results.size, 1);
  });
});
