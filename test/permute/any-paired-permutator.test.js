/**
 * Tests for AnyPairedPermutator
 */
import { describe, it } from 'node:test';
import assert from 'node:assert';
import { AnyPairedPermutator } from '../../lib/permute/any-paired-permutator.js';
import { HandCandidate } from '../../lib/hand/hand-candidate.js';
import { TileSet } from '../../lib/data/tile-set.js';

describe('AnyPairedPermutator', () => {
  it('should pair any tile already in hand', () => {
    const permutator = new AnyPairedPermutator();
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Bamboo');
    candidate.add('1 Bamboo');
    candidate.add('2 Bamboo');
    
    const results = permutator.permute(new Set([candidate]));
    
    // Should create 2 variations: one pairing 1 Bamboo, one pairing 2 Bamboo
    assert.strictEqual(results.size, 2);
    
    for (const result of results) {
      // Original 2 tiles + 1 paired tile = 3 total
      assert.strictEqual(result.getRequiredTiles().length, 3);
    }
  });

  it('should pair each unique tile in hand', () => {
    const permutator = new AnyPairedPermutator();
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Spot');
    candidate.add('Red');
    candidate.add('Green');
    candidate.add('White');
    
    const results = permutator.permute(new Set([candidate]));
    
    // Should create 3 variations
    assert.strictEqual(results.size, 3);
  });

  it('should not create variations if tiles unavailable', () => {
    const permutator = new AnyPairedPermutator();
    
    // 4 copies of same tile already used
    const candidate = new HandCandidate('Test', ['5 Crack', '5 Crack', '5 Crack', '5 Crack']);
    candidate.setPrimarySuit('Crack');
    candidate.add('5 Crack');
    candidate.add('5 Crack');
    candidate.add('5 Crack');
    candidate.add('5 Crack');
    
    const results = permutator.permute(new Set([candidate]));
    
    // No more 5 Crack available
    assert.strictEqual(results.size, 0);
  });

  it('should work with mixed tiles', () => {
    const permutator = new AnyPairedPermutator();
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Bamboo');
    candidate.add('1 Bamboo');
    candidate.add('1 Bamboo');
    candidate.add('2 Bamboo');
    candidate.add('North');
    
    const results = permutator.permute(new Set([candidate]));
    
    // Can pair: another 1 Bamboo (2 remaining), another 2 Bamboo (3 remaining), or North (3 remaining)
    // But we already have 2x 1 Bamboo, so unique tiles are: 1 Bamboo, 2 Bamboo, North
    assert.ok(results.size > 0);
  });

  it('should handle empty hand', () => {
    const permutator = new AnyPairedPermutator();
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Crack');
    
    const results = permutator.permute(new Set([candidate]));
    
    // No tiles in hand to pair
    assert.strictEqual(results.size, 0);
  });
});
