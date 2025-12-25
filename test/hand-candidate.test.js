/**
 * Tests for HandCandidate
 */
import { describe, it } from 'node:test';
import assert from 'node:assert';
import { HandCandidate, times } from '../lib/hand/hand-candidate.js';

describe('HandCandidate', () => {
  it('should create a hand candidate', () => {
    const tiles = ['1 Bamboo', '2 Bamboo', '3 Bamboo'];
    const candidate = new HandCandidate('Test', tiles);
    
    assert.strictEqual(candidate.getName(), 'Test');
    assert.strictEqual(candidate.getRequiredTiles().length, 0);
  });

  it('should check if tile can be added', () => {
    const tiles = ['1 Bamboo', '2 Bamboo', '3 Bamboo'];
    const candidate = new HandCandidate('Test', tiles);
    
    assert.ok(candidate.canAdd('1 Bamboo'));
    assert.ok(!candidate.canAdd('5 Bamboo'));
  });

  it('should add tiles correctly', () => {
    const tiles = ['1 Bamboo', '1 Bamboo', '1 Bamboo', '2 Bamboo'];
    const candidate = new HandCandidate('Test', tiles);
    
    candidate.add('1 Bamboo', times(3));
    assert.strictEqual(candidate.getRequiredTiles().length, 3);
    assert.ok(!candidate.canAdd('1 Bamboo'));
  });

  it('should fork correctly', () => {
    const tiles = ['1 Bamboo', '2 Bamboo'];
    const candidate = new HandCandidate('Test', tiles);
    candidate.setPrimarySuit('Bamboo');
    candidate.add('1 Bamboo');
    
    const fork = candidate.fork();
    assert.strictEqual(fork.getName(), candidate.getName());
    assert.strictEqual(fork.getPrimarySuit(), candidate.getPrimarySuit());
    assert.strictEqual(fork.getRequiredTiles().length, 1);
  });

  it('should validate hand size', () => {
    const tiles = Array(20).fill('1 Bamboo');
    const candidate = new HandCandidate('Test', tiles);
    
    assert.ok(!candidate.isValid());
    
    for (let i = 0; i < 14; i++) {
      candidate.add('1 Bamboo');
    }
    
    assert.ok(candidate.isValid());
  });
});
