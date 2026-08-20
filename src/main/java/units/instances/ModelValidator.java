package units.instances;

import roster.ValidationResult;
import units.options.OptionValidator;

public class ModelValidator {
	
	private final OptionValidator optValidator;
	
	private ModelValidator() {
		optValidator = OptionValidator.create();
	};
	
	public ValidationResult validate(ModelInstance model) {
		ValidationResult result = ValidationResult.create();
		// Shouldn't there be something else here?
		validateChoices(model, result);
		
		return result;
	}
	
	private void validateChoices(
			ModelInstance model,
			ValidationResult result) {
		optValidator.validate(model, result);
	}
	
	public static ModelValidator create() {
		return new ModelValidator();
	}
}
