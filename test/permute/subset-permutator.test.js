/**
 * Tests for SubsetPermutator
 */
import { describe, it } from 'node:test';
import assert from 'node:assert';
import { SubsetPermutator } from '../../lib/permute/subset-permutator.js';
import { HandCandidate } from '../../lib/hand/hand-candidate.js';
import { TileSet } from '../../lib/data/tile-set.js';

describe('SubsetPermutator', () => {
  it('should create subsets of specified length', () => {
    const set = ['Red', 'Green', 'White'];
    const permutator = new SubsetPermutator(set, 2);
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Bamboo');
    
    const results = permutator.permute(new Set([candidate]));
    
    assert.ok(results.size > 0);
    
    for (const result of results) {
      assert.strictEqual(result.getRequiredTiles().length, 2);
    }
  });

  it('should generate permutations (order matters)', () => {
    const set = ['North', 'East', 'South'];
    const permutator = new SubsetPermutator(set, 2);
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Spot');
    
    const results = permutator.permute(new Set([candidate]));
    
    // With order mattering: P(3,2) = 3*2 = 6 permutations
    assert.strictEqual(results.size, 6);
  });

  it('should work with length 3 subset', () => {
    const set = ['Red', 'Green', 'White'];
    const permutator = new SubsetPermutator(set, 3);
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Bamboo');
    
    const results = permutator.permute(new Set([candidate]));
    
    // All permutations of 3 items taken 3 at a time: 3! = 6
    assert.strictEqual(results.size, 6);
    
    for (const result of results) {
      assert.strictEqual(result.getRequiredTiles().length, 3);
    }
  });

  it('should only add valid combinations', () => {
    const set = ['North', 'East'];
    const permutator = new SubsetPermutator(set, 2);
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Crack');
    
    const results = permutator.permute(new Set([candidate]));
    
    // 2 permutations: North+East and East+North (order matters)
    assert.strictEqual(results.size, 2);
    
    for (const result of results) {
      const tiles = result.getRequiredTiles();
      assert.ok(tiles.includes('North'));
      assert.ok(tiles.includes('East'));
    }
  });
});
