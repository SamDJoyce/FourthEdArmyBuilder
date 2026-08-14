package units.options;

import roster.RosterResult;
import units.instances.ModelInstance;
import units.instances.UnitInstance;

public class OptionValidator {

	private OptionValidator() {}
	
	public void validate(
			OptionOwner owner,
			RosterResult result) {
		
		for (SelectedOption o : owner.getSelectedOptions()) {
			SelectionContext context;
			if (owner.isModel()) {
				ModelInstance model = (ModelInstance) owner;
				context = SelectionContext.forModel(model,o.getChoice());
			}
			else {
				UnitInstance unit = (UnitInstance) owner;
				context = SelectionContext.forUnit(unit,o.getChoice());
			}
			
			RosterResult r = o.getChoice().validate(context);
			if (r.hasIssues()) {
				result.addIssues(r.getIssues());
			}
		}
	}
	
	public static OptionValidator create() {
		return new OptionValidator();
	}
}
