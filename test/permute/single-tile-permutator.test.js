/**
 * Tests for SingleTilePermutator
 */
import { describe, it } from 'node:test';
import assert from 'node:assert';
import { SingleTilePermutator } from '../../lib/permute/single-tile-permutator.js';
import { HandCandidate } from '../../lib/hand/hand-candidate.js';
import { TileSet } from '../../lib/data/tile-set.js';

describe('SingleTilePermutator', () => {
  it('should add single tile', () => {
    const permutator = new SingleTilePermutator('3', '1st');
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Bamboo');
    
    const results = permutator.permute(new Set([candidate]));
    
    assert.strictEqual(results.size, 1);
    const result = Array.from(results)[0];
    assert.strictEqual(result.getRequiredTiles().length, 1);
    assert.strictEqual(result.getRequiredTiles()[0], '3 Bamboo');
  });

  it('should work with honor tiles', () => {
    const permutator = new SingleTilePermutator('White', '1st');
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Spot');
    
    const results = permutator.permute(new Set([candidate]));
    
    assert.strictEqual(results.size, 1);
    const result = Array.from(results)[0];
    assert.strictEqual(result.getRequiredTiles()[0], 'White');
  });

  it('should respect tile availability', () => {
    const permutator = new SingleTilePermutator('9', '1st');
    
    // No 9 Crack available
    const candidate = new HandCandidate('Test', ['1 Crack']);
    candidate.setPrimarySuit('Crack');
    
    const results = permutator.permute(new Set([candidate]));
    
    assert.strictEqual(results.size, 0);
  });
});
