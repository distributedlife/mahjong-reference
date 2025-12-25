/**
 * InvalidHandCandidateFilter - Filters out invalid hand candidates
 */

export class InvalidHandCandidateFilter {
  apply(candidates) {
    return candidates.filter(candidate => candidate.isValid());
  }
}
