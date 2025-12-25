/**
 * Tests for MixedPungPermutator
 */
import { describe, it } from 'node:test';
import assert from 'node:assert';
import { MixedPungPermutator } from '../../lib/permute/mixed-pung-permutator.js';
import { HandCandidate } from '../../lib/hand/hand-candidate.js';
import { TileSet } from '../../lib/data/tile-set.js';

describe('MixedPungPermutator', () => {
  it('should create mixed pungs from same rank', () => {
    const tiles = ['1'];
    const permutator = new MixedPungPermutator(tiles);
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Bamboo');
    
    const results = permutator.permute(new Set([candidate]));
    
    assert.strictEqual(results.size, 1);
    const result = Array.from(results)[0];
    
    // Should have 1 Bamboo, 1 Crack, 1 Spot
    assert.strictEqual(result.getRequiredTiles().length, 3);
    const tiles_result = result.getRequiredTiles().sort();
    assert.ok(tiles_result.includes('1 Bamboo'));
    assert.ok(tiles_result.includes('1 Crack'));
    assert.ok(tiles_result.includes('1 Spot'));
  });

  it('should create mixed pungs for multiple tiles', () => {
    const tiles = ['5', '6'];
    const permutator = new MixedPungPermutator(tiles);
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Bamboo');
    
    const results = permutator.permute(new Set([candidate]));
    
    assert.strictEqual(results.size, 2);
    // One for 5s, one for 6s
  });

  it('should verify all 3 tiles are added', () => {
    const tiles = ['9'];
    const permutator = new MixedPungPermutator(tiles);
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Spot');
    
    const results = permutator.permute(new Set([candidate]));
    
    const result = Array.from(results)[0];
    const requiredTiles = result.getRequiredTiles();
    
    assert.strictEqual(requiredTiles.length, 3);
    assert.ok(requiredTiles.includes('9 Bamboo'));
    assert.ok(requiredTiles.includes('9 Crack'));
    assert.ok(requiredTiles.includes('9 Spot'));
  });

  it('should not create pung if tiles unavailable', () => {
    const tiles = ['8'];
    const permutator = new MixedPungPermutator(tiles);
    
    // Only have 2 of the 3 needed suits
    const candidate = new HandCandidate('Test', ['8 Bamboo', '8 Spot']);
    candidate.setPrimarySuit('Bamboo');
    
    const results = permutator.permute(new Set([candidate]));
    
    assert.strictEqual(results.size, 0);
  });
});
