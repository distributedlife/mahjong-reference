/**
 * Tests for StandardSequencePermutator
 */
import { describe, it } from 'node:test';
import assert from 'node:assert';
import { StandardSequencePermutator } from '../../lib/permute/standard-sequence-permutator.js';
import { HandCandidate } from '../../lib/hand/hand-candidate.js';
import { TileSet } from '../../lib/data/tile-set.js';

describe('StandardSequencePermutator', () => {
  it('should create sequence from range', () => {
    const permutator = new StandardSequencePermutator(1, 3, '1st');
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Bamboo');
    
    const results = permutator.permute(new Set([candidate]));
    
    assert.strictEqual(results.size, 1);
    const result = Array.from(results)[0];
    
    // Should have 1, 2, 3 Bamboo
    const tiles = result.getRequiredTiles();
    assert.strictEqual(tiles.length, 3);
    assert.ok(tiles.includes('1 Bamboo'));
    assert.ok(tiles.includes('2 Bamboo'));
    assert.ok(tiles.includes('3 Bamboo'));
  });

  it('should create full 1-9 sequence', () => {
    const permutator = new StandardSequencePermutator(1, 9, '1st');
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Spot');
    
    const results = permutator.permute(new Set([candidate]));
    
    assert.strictEqual(results.size, 1);
    const result = Array.from(results)[0];
    
    // Should have all 9 tiles
    assert.strictEqual(result.getRequiredTiles().length, 9);
    for (let i = 1; i <= 9; i++) {
      assert.ok(result.getRequiredTiles().includes(`${i} Spot`));
    }
  });

  it('should work with partial ranges', () => {
    const permutator = new StandardSequencePermutator(5, 7, '1st');
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Crack');
    
    const results = permutator.permute(new Set([candidate]));
    
    const result = Array.from(results)[0];
    const tiles = result.getRequiredTiles();
    
    assert.strictEqual(tiles.length, 3);
    assert.ok(tiles.includes('5 Crack'));
    assert.ok(tiles.includes('6 Crack'));
    assert.ok(tiles.includes('7 Crack'));
  });

  it('should fork candidates before modification', () => {
    const permutator = new StandardSequencePermutator(1, 2, '1st');
    
    const original = new HandCandidate('Test', new TileSet().getTiles());
    original.setPrimarySuit('Bamboo');
    
    const results = permutator.permute(new Set([original]));
    
    // Original should not be modified
    assert.strictEqual(original.getRequiredTiles().length, 0);
    
    // Result should have tiles
    const result = Array.from(results)[0];
    assert.strictEqual(result.getRequiredTiles().length, 2);
  });
});
