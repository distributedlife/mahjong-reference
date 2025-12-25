/**
 * HandDefinition - Defines the pattern/requirements for a specific hand type
 */

export class HandDefinition {
  constructor(name, suits, requirements) {
    this.name = name;
    this.suits = suits;
    this.requirements = requirements;
  }

  getName() {
    return this.name;
  }

  getSuits() {
    return this.suits;
  }

  getRequirements() {
    return this.requirements;
  }
}
