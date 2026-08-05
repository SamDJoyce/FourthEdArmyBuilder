package units.instances;

import roster.RosterResult;
import units.options.SelectedOption;
import units.options.SelectionContext;
import units.options.requirements.RequirementResult;

public class ModelValidator {
	private ModelValidator() {};
	
	public RosterResult validate(ModelInstance model) {
		RosterResult result = new RosterResult();
		
		validateChoices(model, result);
		
		return result;
	}
	
	private void validateChoices(
			ModelInstance model,
			RosterResult result) {
		
		for (SelectedOption o : model.getSelectedOptions()) {
			SelectionContext context = SelectionContext.forModel(model, o.getChoice());
			RequirementResult reqResult = o.validate(context);
			if (!reqResult.isValid()) {
				result.addIssue(reqResult.getMessage());
			}
		}
	}
	
	public static ModelValidator create() {
		return new ModelValidator();
	}
}
