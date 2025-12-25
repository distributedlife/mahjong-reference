/**
 * Tests for TileSet
 */
import { describe, it } from 'node:test';
import assert from 'node:assert';
import { TileSet, createTile, getNameForBitTile, Tile } from '../lib/data/tile-set.js';

describe('TileSet', () => {
  it('should create a complete tile set', () => {
    const tileSet = new TileSet();
    const tiles = tileSet.getTiles();
    
    // Should have 140 tiles total (4 of each: 9 numbered x 3 suits + 5 winds + 3 dragons)
    // 27 numbered (9*3) * 4 = 108
    // 5 winds * 4 = 20
    // 3 dragons * 4 = 12
    // Total = 140
    assert.strictEqual(tiles.length, 140);
  });

  it('should create a tile with correct format', () => {
    const tile = createTile(5, 'Bamboo');
    assert.strictEqual(tile, '5 Bamboo');
  });

  it('should get correct name for bit tile', () => {
    const name = getNameForBitTile(Tile.B5);
    assert.strictEqual(name, '5 Bamboo');
    
    const redName = getNameForBitTile(Tile.RD);
    assert.strictEqual(redName, 'Red');
  });
});
