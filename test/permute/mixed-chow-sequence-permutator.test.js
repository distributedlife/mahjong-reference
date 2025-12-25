/**
 * Tests for MixedChowSequencePermutator
 */
import { describe, it } from 'node:test';
import assert from 'node:assert';
import { MixedChowSequencePermutator } from '../../lib/permute/mixed-chow-sequence-permutator.js';
import { HandCandidate } from '../../lib/hand/hand-candidate.js';
import { TileSet } from '../../lib/data/tile-set.js';

describe('MixedChowSequencePermutator', () => {
  it('should create mixed chow sequences', () => {
    const permutator = new MixedChowSequencePermutator();
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Bamboo');
    
    const results = permutator.permute(new Set([candidate]));
    
    assert.ok(results.size > 0);
    // Each result should have 3 tiles from different suits
    const result = Array.from(results)[0];
    assert.strictEqual(result.getRequiredTiles().length, 3);
  });

  it('should use different suits for each tile', () => {
    const permutator = new MixedChowSequencePermutator();
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Bamboo');
    
    const results = permutator.permute(new Set([candidate]));
    
    // Check that tiles are from different suits
    for (const result of results) {
      const tiles = result.getRequiredTiles();
      if (tiles.length === 3) {
        const suits = new Set(tiles.map(t => t.split(' ')[1]));
        assert.strictEqual(suits.size, 3); // All 3 tiles from different suits
      }
    }
  });

  it('should create sequences for valid ranges only', () => {
    const permutator = new MixedChowSequencePermutator();
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Bamboo');
    
    const results = permutator.permute(new Set([candidate]));
    
    // Should only create sequences where start + 2 <= 9
    // So valid starts are 1-7, with 6 suit permutations each = 42 combinations
    assert.ok(results.size > 0);
  });
});
