/**
 * Tests for PungPermutator
 */
import { describe, it } from 'node:test';
import assert from 'node:assert';
import { PungPermutator } from '../../lib/permute/pung-permutator.js';
import { HandCandidate } from '../../lib/hand/hand-candidate.js';
import { TileSet } from '../../lib/data/tile-set.js';

describe('PungPermutator', () => {
  it('should create pungs from allowed tiles', () => {
    const tiles = ['4', '5', '6'];
    const permutator = new PungPermutator(tiles, '1st');
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Bamboo');
    
    const results = permutator.permute(new Set([candidate]));
    
    assert.ok(results.size > 0);
    // Should create variations with pungs of 4, 5, or 6 Bamboo
    const resultsArray = Array.from(results);
    assert.ok(resultsArray.some(c => c.getRequiredTiles().length === 3));
  });

  it('should add exactly 3 tiles per variation', () => {
    const tiles = ['7'];
    const permutator = new PungPermutator(tiles, '1st');
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Crack');
    
    const results = permutator.permute(new Set([candidate]));
    
    assert.strictEqual(results.size, 1);
    const result = Array.from(results)[0];
    assert.strictEqual(result.getRequiredTiles().length, 3);
    assert.ok(result.getRequiredTiles().every(t => t === '7 Crack'));
  });

  it('should respect tile availability for pungs', () => {
    const tiles = ['2'];
    const permutator = new PungPermutator(tiles, '1st');
    
    // Create candidate with exactly 3 tiles
    const candidate = new HandCandidate('Test', ['2 Spot', '2 Spot', '2 Spot']);
    candidate.setPrimarySuit('Spot');
    
    const results = permutator.permute(new Set([candidate]));
    
    assert.strictEqual(results.size, 1);
    const result = Array.from(results)[0];
    assert.strictEqual(result.getRequiredTiles().length, 3);
  });

  it('should not create variations when insufficient tiles', () => {
    const tiles = ['1'];
    const permutator = new PungPermutator(tiles, '1st');
    
    // Only 2 tiles available, need 3 for pung
    const candidate = new HandCandidate('Test', ['1 Bamboo', '1 Bamboo']);
    candidate.setPrimarySuit('Bamboo');
    
    const results = permutator.permute(new Set([candidate]));
    
    assert.strictEqual(results.size, 0);
  });

  it('should work with honor tiles', () => {
    const tiles = ['Red', 'Green'];
    const permutator = new PungPermutator(tiles, '1st');
    
    const candidate = new HandCandidate('Test', new TileSet().getTiles());
    candidate.setPrimarySuit('Bamboo');
    
    const results = permutator.permute(new Set([candidate]));
    
    // Should create pungs of Red and Green dragons
    assert.strictEqual(results.size, 2);
  });
});
