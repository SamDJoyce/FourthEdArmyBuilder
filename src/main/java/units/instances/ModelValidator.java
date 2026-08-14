package units.instances;

import roster.RosterResult;
import units.options.OptionValidator;

public class ModelValidator {
	
	private final OptionValidator optValidator;
	
	private ModelValidator() {
		optValidator = OptionValidator.create();
	};
	
	public RosterResult validate(ModelInstance model) {
		RosterResult result = new RosterResult();
		// Shouldn't there be something else here?
		validateChoices(model, result);
		
		return result;
	}
	
	private void validateChoices(
			ModelInstance model,
			RosterResult result) {
		optValidator.validate(model, result);
	}
	
	public static ModelValidator create() {
		return new ModelValidator();
	}
}
