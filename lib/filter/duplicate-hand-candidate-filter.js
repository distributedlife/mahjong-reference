/**
 * DuplicateHandCandidateFilter - Filters out duplicate hand candidates
 */

export class DuplicateHandCandidateFilter {
  apply(candidates) {
    const seen = new Set();
    const unique = [];

    for (const candidate of candidates) {
      const key = `${candidate.name}|${candidate.primarySuit}|${candidate.secondSuit}|${candidate.thirdSuit}|${JSON.stringify(candidate.requiredTiles)}`;
      
      if (!seen.has(key)) {
        seen.add(key);
        unique.push(candidate);
      }
    }

    return unique;
  }
}
