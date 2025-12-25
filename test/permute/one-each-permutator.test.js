/**
 * Tests for OneEachPermutator
 */
import { describe, it } from 'node:test';
import assert from 'node:assert';
import { OneEachPermutator } from '../../lib/permute/one-each-permutator.js';
import { HandCandidate } from '../../lib/hand/hand-candidate.js';
import { TileSet } from '../../lib/data/tile-set.js';

describe('OneEachPermutator', () => {
  it('should add one of each tile in set', () => {
    const set = ['Red', 'Green', 'White'];
    const permutator = new OneEachPermutator(set);
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Bamboo');
    
    const results = permutator.permute(new Set([candidate]));
    
    // OneEach generates all permutations (orders) of the tiles: 3! = 6
    assert.strictEqual(results.size, 6);
    
    for (const result of results) {
      // Should have all 3 tiles
      assert.strictEqual(result.getRequiredTiles().length, 3);
      assert.ok(result.getRequiredTiles().includes('Red'));
      assert.ok(result.getRequiredTiles().includes('Green'));
      assert.ok(result.getRequiredTiles().includes('White'));
    }
  });

  it('should work with wind tiles', () => {
    const set = ['North', 'East', 'South'];
    const permutator = new OneEachPermutator(set);
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Spot');
    
    const results = permutator.permute(new Set([candidate]));
    
    // 3! = 6 permutations
    assert.strictEqual(results.size, 6);
    
    for (const result of results) {
      assert.strictEqual(result.getRequiredTiles().length, 3);
      assert.ok(result.getRequiredTiles().includes('North'));
      assert.ok(result.getRequiredTiles().includes('East'));
      assert.ok(result.getRequiredTiles().includes('South'));
    }
  });

  it('should not create result if tiles unavailable', () => {
    const set = ['North', 'East', 'South', 'West'];
    const permutator = new OneEachPermutator(set);
    
    // Missing West wind
    const candidate = new HandCandidate('Test', ['North', 'East', 'South']);
    candidate.setPrimarySuit('Bamboo');
    
    const results = permutator.permute(new Set([candidate]));
    
    assert.strictEqual(results.size, 0);
  });

  it('should handle smaller sets', () => {
    const set = ['Red', 'Green'];
    const permutator = new OneEachPermutator(set);
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Crack');
    
    const results = permutator.permute(new Set([candidate]));
    
    // 2! = 2 permutations
    assert.strictEqual(results.size, 2);
    
    for (const result of results) {
      assert.strictEqual(result.getRequiredTiles().length, 2);
      assert.ok(result.getRequiredTiles().includes('Red'));
      assert.ok(result.getRequiredTiles().includes('Green'));
    }
  });
});
