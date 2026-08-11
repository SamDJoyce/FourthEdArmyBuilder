package units.instances;

import roster.RosterResult;
import units.options.SelectedOption;
import units.options.SelectionContext;

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
			SelectionContext context = SelectionContext.forModel(model,o.getChoice());
			
			RosterResult r = o.getChoice().validate(context);
			if (r.hasIssues()) {
				result.addIssues(r.getIssues());
			}
		}
	}
	
	public static ModelValidator create() {
		return new ModelValidator();
	}
}
