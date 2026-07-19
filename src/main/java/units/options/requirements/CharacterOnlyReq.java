package units.options.requirements;

import units.UnitType;
import units.options.SelectionContext;

public class CharacterOnlyReq implements Requirement {

	private String message;

	public CharacterOnlyReq() {
	}
	
	@Override
	public RequirementResult validate(SelectionContext context) {
		if (!context.hasModel()) {
			message = "CharacterOnlyRequirement needs a ModelInstance";
			return RequirementResult.failure(message);
		}
		Boolean valid = context.getModel().isType(UnitType.CHARACTER);
		String name = context.getModel().getName();
		if (valid) {
			message = String.format("%s is a Character",name);
			return RequirementResult.success(message);
		}
		message = String.format("%s is not a Character",name);
		return RequirementResult.failure(message);
	}

}
