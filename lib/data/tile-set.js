/**
 * TileSet - Defines all Mahjong tiles and provides utility functions
 */

// Tile bit values enum
export const Tile = {
  S3: 0n, S7: 1n, B3: 2n, B7: 3n, C3: 4n, C7: 5n,
  S4: 6n, S6: 7n, S5: 8n, B4: 9n, B6: 10n, B5: 11n, C4: 12n, C6: 13n, C5: 14n,
  S2: 15n, S8: 16n, B2: 17n, B8: 18n, C2: 19n, C8: 20n,
  S1: 21n, B1: 22n, S9: 23n, B9: 24n, C1: 25n, C9: 26n,
  WD: 27n, GD: 28n, RD: 29n,
  NW: 30n, EW: 31n, SW: 32n, WW: 33n, OW: 34n
};

// Convert tile enum to bit value
Object.keys(Tile).forEach(key => {
  Tile[key] = 1n << Tile[key];
});

export const BIT_TILES = [
  Tile.B1, Tile.B2, Tile.B3, Tile.B4, Tile.B5, Tile.B6, Tile.B7, Tile.B8, Tile.B9,
  Tile.C1, Tile.C2, Tile.C3, Tile.C4, Tile.C5, Tile.C6, Tile.C7, Tile.C8, Tile.C9,
  Tile.S1, Tile.S2, Tile.S3, Tile.S4, Tile.S5, Tile.S6, Tile.S7, Tile.S8, Tile.S9,
  Tile.RD, Tile.GD, Tile.WD,
  Tile.NW, Tile.EW, Tile.SW, Tile.WW, Tile.OW
];

export const Winds = ['East', 'North', 'West', 'South', 'OwnWind'];
export const Dragons = ['White', 'Red', 'Green'];
export const Suits = ['Bamboo', 'Spot', 'Crack'];

const tileNameMap = new Map([
  [Tile.B1, '1 Bamboo'], [Tile.B2, '2 Bamboo'], [Tile.B3, '3 Bamboo'],
  [Tile.B4, '4 Bamboo'], [Tile.B5, '5 Bamboo'], [Tile.B6, '6 Bamboo'],
  [Tile.B7, '7 Bamboo'], [Tile.B8, '8 Bamboo'], [Tile.B9, '9 Bamboo'],
  [Tile.C1, '1 Crack'], [Tile.C2, '2 Crack'], [Tile.C3, '3 Crack'],
  [Tile.C4, '4 Crack'], [Tile.C5, '5 Crack'], [Tile.C6, '6 Crack'],
  [Tile.C7, '7 Crack'], [Tile.C8, '8 Crack'], [Tile.C9, '9 Crack'],
  [Tile.S1, '1 Spot'], [Tile.S2, '2 Spot'], [Tile.S3, '3 Spot'],
  [Tile.S4, '4 Spot'], [Tile.S5, '5 Spot'], [Tile.S6, '6 Spot'],
  [Tile.S7, '7 Spot'], [Tile.S8, '8 Spot'], [Tile.S9, '9 Spot'],
  [Tile.RD, 'Red'], [Tile.GD, 'Green'], [Tile.WD, 'White'],
  [Tile.NW, 'North'], [Tile.EW, 'East'], [Tile.SW, 'South'],
  [Tile.WW, 'West'], [Tile.OW, 'OwnWind']
]);

const nameToValueMap = new Map(
  Array.from(tileNameMap.entries()).map(([k, v]) => [v, k])
);

export function getNameForBitTile(tile) {
  return tileNameMap.get(tile) || 'WRONG';
}

export function getValueForTile(tileName) {
  return nameToValueMap.get(tileName) || 0n;
}

export function createTile(num, suit) {
  return `${num} ${suit}`;
}

export function convertTileToAppropriateSuit(suit, tile, candidate) {
  let tileSuit = '';
  if (suit === '1st') {
    tileSuit = candidate.primarySuit;
  } else if (suit === '2nd') {
    tileSuit = candidate.secondSuit;
  } else if (suit === '3rd') {
    tileSuit = candidate.thirdSuit;
  }

  // If it's a number, convert to appropriate suit
  if (/^\d+$/.test(tile)) {
    return createTile(tile, tileSuit);
  }

  return tile;
}

export class TileSet {
  constructor() {
    this.tiles = [];
    
    // Add winds (4 of each)
    for (const wind of Winds) {
      for (let i = 0; i < 4; i++) {
        this.tiles.push(wind);
      }
    }
    
    // Add dragons (4 of each)
    for (const dragon of Dragons) {
      for (let i = 0; i < 4; i++) {
        this.tiles.push(dragon);
      }
    }
    
    // Add numbered tiles (4 of each, 1-9, in each suit)
    for (const suit of Suits) {
      for (let num = 1; num <= 9; num++) {
        for (let i = 0; i < 4; i++) {
          this.tiles.push(createTile(num, suit));
        }
      }
    }
  }

  getTiles() {
    return this.tiles;
  }
}
