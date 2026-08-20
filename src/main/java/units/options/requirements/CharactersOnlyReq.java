package units.options.requirements;

import roster.ValidationResult;
import units.UnitType;
import units.instances.ModelInstance;
import units.options.SelectionContext;

public class CharactersOnlyReq implements Requirement {

	private String name;
	private String message;

	public CharactersOnlyReq(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public RequirementResult isMet(SelectionContext context) {
		if (!context.hasModel()) {
			message = "CharacterOnlyRequirement needs a ModelInstance";
			return RequirementResult.failure(message);
		}
		
		String name = context.getModel().getName();
		
		if (isCharacter(context.getModel())) {
			message = String.format("%s is a Character",name);
			return RequirementResult.success(message);
		}
		message = String.format("%s is not a Character",name);
		return RequirementResult.failure(message);
	}
	
	@Override 
	public ValidationResult validate(SelectionContext context) {
		ValidationResult result  = ValidationResult.create();
		String name = context.getModel().getName();
		
		if (!isCharacter(context.getModel())) {
			result.addIssue(String.format(
					"%s cannot select %s because they are not a Character",
					name,
					context.getChoice().getName()));
		}
		
		return result;
	}
	
	private boolean isCharacter(ModelInstance model) {
		return model.isType(UnitType.CHARACTER);
	}

}
