/**
 * JsonToHandDefinition - Parses JSON to create HandDefinitions
 */
import { HandDefinition } from '../hand/hand-definition.js';

export class JsonToHandDefinition {
  constructor(permutatorBuilder) {
    this.permutatorBuilder = permutatorBuilder;
  }

  getHandDefinitions(jsonObject) {
    const hands = jsonObject.hands || [];
    return hands.map(handJson => {
      const name = handJson.name;
      const suits = handJson.suits || [];
      const requirements = (handJson.requirements || []).map(req => 
        this.permutatorBuilder.build(req, '1st')
      );

      return new HandDefinition(name, suits, requirements);
    });
  }
}
