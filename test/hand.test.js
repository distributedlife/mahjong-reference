/**
 * Tests for Hand
 */
import { describe, it } from 'node:test';
import assert from 'node:assert';
import { Hand } from '../lib/hand/hand.js';

describe('Hand', () => {
  it('should create a hand with name', () => {
    const hand = new Hand('Test Hand');
    assert.strictEqual(hand.getName(), 'Test Hand');
  });

  it('should create a hand from tiles', () => {
    const tiles = [
      '1 Bamboo', '1 Bamboo', '1 Bamboo',
      '2 Bamboo', '2 Bamboo', '2 Bamboo',
      '3 Bamboo', '3 Bamboo', '3 Bamboo',
      '4 Bamboo', '4 Bamboo', '4 Bamboo',
      '5 Bamboo', '5 Bamboo'
    ];
    const hand = new Hand('All Bamboo', tiles);
    
    assert.strictEqual(hand.getName(), 'All Bamboo');
    assert.ok(hand.getPart1() > 0n);
  });

  it('should compare hands correctly', () => {
    const hand1 = new Hand('Hand A');
    const hand2 = new Hand('Hand B');
    
    assert.strictEqual(hand1.compareTo(hand2), -1);
    assert.strictEqual(hand2.compareTo(hand1), 1);
    assert.strictEqual(hand1.compareTo(hand1), 0);
  });

  it('should check equality correctly', () => {
    const hand1 = new Hand('Test', [], 1n, 2n, 3n, 4n);
    const hand2 = new Hand('Test', [], 1n, 2n, 3n, 4n);
    const hand3 = new Hand('Test', [], 1n, 2n, 3n, 5n);
    
    assert.ok(hand1.equals(hand2));
    assert.ok(!hand1.equals(hand3));
  });
});
