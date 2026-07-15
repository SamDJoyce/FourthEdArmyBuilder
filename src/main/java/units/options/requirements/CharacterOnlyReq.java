package units.options.requirements;

import units.UnitType;
import units.options.ValidationContext;

public class CharacterOnlyReq implements Requirement {

	private String message;

	public CharacterOnlyReq() {
	}
	
	@Override
	public ValidationResult validate(ValidationContext context) {
		if (!context.hasModel()) {
			message = "CharacterOnlyRequirement needs a ModelInstance";
			return ValidationResult.failure(message);
		}
		Boolean valid = context.getModel().isType(UnitType.CHARACTER);
		String name = context.getModel().getName();
		if (valid) {
			message = String.format("%s is a Character",name);
			return ValidationResult.success(message);
		}
		message = String.format("%s is not a Character",name);
		return ValidationResult.failure(message);
	}

}
