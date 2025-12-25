/**
 * Hand - Represents a complete Mahjong hand using bit-field compression
 */
import { BIT_TILES, getNameForBitTile } from '../data/tile-set.js';

export class Hand {
  constructor(name, requiredTiles = null, part1 = 0n, part2 = 0n, part3 = 0n, part4 = 0n) {
    this.name = name;
    this.part1 = part1;
    this.part2 = part2;
    this.part3 = part3;
    this.part4 = part4;

    if (requiredTiles) {
      this.convertHandToBitFields(requiredTiles);
    }
  }

  convertHandToBitFields(requiredTiles) {
    const remainingRequiredTiles = [...requiredTiles];

    for (const bitTile of BIT_TILES) {
      const tile = getNameForBitTile(bitTile);

      if (remainingRequiredTiles.includes(tile)) {
        this.part1 += bitTile;
        remainingRequiredTiles.splice(remainingRequiredTiles.indexOf(tile), 1);
      }
      if (remainingRequiredTiles.includes(tile)) {
        this.part2 += bitTile;
        remainingRequiredTiles.splice(remainingRequiredTiles.indexOf(tile), 1);
      }
      if (remainingRequiredTiles.includes(tile)) {
        this.part3 += bitTile;
        remainingRequiredTiles.splice(remainingRequiredTiles.indexOf(tile), 1);
      }
      if (remainingRequiredTiles.includes(tile)) {
        this.part4 += bitTile;
        remainingRequiredTiles.splice(remainingRequiredTiles.indexOf(tile), 1);
      }
    }
  }

  toString() {
    const tiles = [];

    for (const bitTile of BIT_TILES) {
      if ((this.part1 & bitTile) === bitTile) {
        tiles.push(getNameForBitTile(bitTile));
      }
    }
    for (const bitTile of BIT_TILES) {
      if ((this.part2 & bitTile) === bitTile) {
        tiles.push(getNameForBitTile(bitTile));
      }
    }
    for (const bitTile of BIT_TILES) {
      if ((this.part3 & bitTile) === bitTile) {
        tiles.push(getNameForBitTile(bitTile));
      }
    }
    for (const bitTile of BIT_TILES) {
      if ((this.part4 & bitTile) === bitTile) {
        tiles.push(getNameForBitTile(bitTile));
      }
    }

    return tiles.toString();
  }

  compareTo(rhs) {
    if (this.name !== rhs.name) {
      return this.name < rhs.name ? -1 : 1;
    }
    if (this.part1 !== rhs.part1) {
      return this.part1 < rhs.part1 ? -1 : 1;
    }
    if (this.part2 !== rhs.part2) {
      return this.part2 < rhs.part2 ? -1 : 1;
    }
    if (this.part3 !== rhs.part3) {
      return this.part3 < rhs.part3 ? -1 : 1;
    }
    if (this.part4 !== rhs.part4) {
      return this.part4 < rhs.part4 ? -1 : 1;
    }
    return 0;
  }

  equals(obj) {
    if (!obj || !(obj instanceof Hand)) {
      return false;
    }
    return this.name === obj.name &&
           this.part1 === obj.part1 &&
           this.part2 === obj.part2 &&
           this.part3 === obj.part3 &&
           this.part4 === obj.part4;
  }

  hashCode() {
    let hash = 17n;
    hash = hash * 31n + BigInt(this.name.split('').reduce((acc, c) => acc + c.charCodeAt(0), 0));
    hash = hash * 31n + this.part1;
    hash = hash * 31n + this.part2;
    hash = hash * 31n + this.part3;
    hash = hash * 31n + this.part4;
    return hash;
  }

  isPartialMatch(compareTo) {
    const part1 = typeof compareTo === 'bigint' ? compareTo : compareTo.part1;
    return (this.part1 & part1) > 0n;
  }

  getName() {
    return this.name;
  }

  getPart1() {
    return this.part1;
  }

  getPart2() {
    return this.part2;
  }

  getPart3() {
    return this.part3;
  }

  getPart4() {
    return this.part4;
  }
}
