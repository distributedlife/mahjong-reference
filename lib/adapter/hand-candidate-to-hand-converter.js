/**
 * HandCandidateToHandConverter - Converts HandCandidates to Hands
 */
import { Hand } from '../hand/hand.js';

export class HandCandidateToHandConverter {
  convert(candidates) {
    return candidates.map(candidate => 
      new Hand(candidate.getName(), candidate.getRequiredTiles())
    );
  }
}
