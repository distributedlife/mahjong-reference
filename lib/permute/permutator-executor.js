/**
 * PermutatorExecutor - Executes permutators on candidates
 */

export class PermutatorExecutor {
  runPermutatorsOnCandidate(candidate, permutators) {
    let candidates = new Set([candidate]);

    for (const permutator of permutators) {
      candidates = permutator.permute(candidates);
    }

    return Array.from(candidates);
  }
}
