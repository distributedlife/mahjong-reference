/**
 * HandCandidate - Intermediate representation during hand generation
 */

export const times = (i) => i;

export class HandCandidate {
  constructor(name, availableTiles, other = null) {
    if (other) {
      // Copy constructor
      this.name = other.name;
      this.primarySuit = other.primarySuit;
      this.availableTiles = [...other.availableTiles];
      this.requiredTiles = [...other.requiredTiles];
      this.secondSuit = other.secondSuit;
      this.thirdSuit = other.thirdSuit;
    } else {
      this.name = name;
      this.availableTiles = availableTiles;
      this.requiredTiles = [];
      this.secondSuit = '';
      this.thirdSuit = '';
    }
  }

  setPrimarySuit(primarySuit) {
    this.primarySuit = primarySuit;
  }

  isValid() {
    return this.requiredTiles.length === 14;
  }

  getName() {
    return this.name;
  }

  getRequiredTiles() {
    return this.requiredTiles;
  }

  getPrimarySuit() {
    return this.primarySuit;
  }

  getSecondSuit() {
    return this.secondSuit;
  }

  setSecondSuit(secondSuit) {
    this.secondSuit = secondSuit;
  }

  getThirdSuit() {
    return this.thirdSuit;
  }

  setThirdSuit(thirdSuit) {
    this.thirdSuit = thirdSuit;
  }

  canAdd(tile, count = times(1)) {
    const copyOfRemainingTiles = [...this.availableTiles];

    for (let i = 0; i < count; i++) {
      const index = copyOfRemainingTiles.indexOf(tile);
      if (index === -1) {
        return false;
      }
      copyOfRemainingTiles.splice(index, 1);
    }

    return true;
  }

  add(tile, count = times(1)) {
    for (let i = 0; i < count; i++) {
      const index = this.availableTiles.indexOf(tile);
      if (index !== -1) {
        this.availableTiles.splice(index, 1);
      }
      this.requiredTiles.push(tile);
    }

    this.requiredTiles.sort();
  }

  equals(obj) {
    if (!obj || !(obj instanceof HandCandidate)) {
      return false;
    }

    return this.name === obj.name &&
           this.primarySuit === obj.primarySuit &&
           this.secondSuit === obj.secondSuit &&
           this.thirdSuit === obj.thirdSuit &&
           JSON.stringify(this.requiredTiles) === JSON.stringify(obj.requiredTiles);
  }

  hashCode() {
    let hash = 17;
    hash = hash * 31 + this.name.split('').reduce((acc, c) => acc + c.charCodeAt(0), 0);
    hash = hash * 31 + this.primarySuit.split('').reduce((acc, c) => acc + c.charCodeAt(0), 0);
    hash = hash * 31 + this.secondSuit.split('').reduce((acc, c) => acc + c.charCodeAt(0), 0);
    hash = hash * 31 + this.thirdSuit.split('').reduce((acc, c) => acc + c.charCodeAt(0), 0);
    hash = hash * 31 + JSON.stringify(this.requiredTiles).split('').reduce((acc, c) => acc + c.charCodeAt(0), 0);
    return hash;
  }

  fork() {
    return new HandCandidate(null, null, this);
  }
}
