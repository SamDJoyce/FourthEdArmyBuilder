package units.instances;

import roster.RosterResult;
import units.options.SelectedOption;
import units.options.SelectionContext;
import units.options.requirements.RequirementResult;

public class UnitValidator {
	private UnitValidator() {}
	
	public RosterResult validate(UnitInstance unit) {
		RosterResult result = new RosterResult();
		
		validateSize(unit,result);
		validateChoices(unit,result);
		validateModels(unit,result);
		
		return result;
	}
	
	private void validateModels(			
			UnitInstance unit,
			RosterResult result) {
		for (ModelInstance m : unit.getModels()) {
			RosterResult r = m.validate();
			if (r.hasIssues()) {
				result.addIssues(r.getIssues());
			}
		}
	}
	
	private void validateChoices(
			UnitInstance unit,
			RosterResult result) {
		
		for (SelectedOption o : unit.getSelectedOptions()) {
			SelectionContext context = SelectionContext.forUnit(unit,o.getChoice());
			RequirementResult reqResult = o.validate(context);
			if (!reqResult.isValid()) {
				result.addIssue(reqResult.getMessage());
			}
		}
	}
	
	private void validateSize(
			UnitInstance unit,
			RosterResult result) {
		int min 	= unit.getMinSize();
		int max     = unit.getMaxSize();
		int current = unit.getCurrentSize();
		
		if (current < min) {
			result.addIssue(String.format(
					"Unit must contain at least %d models (currently %d)", 
					min,
					current
					));
		}
		if (current > max) {
			result.addIssue(String.format(
					"Unit may contain at most %d models (currently %d)", 
					max,
					current
					));
		}
	}
	
	public static UnitValidator create(){
		return new UnitValidator();
	}
}
