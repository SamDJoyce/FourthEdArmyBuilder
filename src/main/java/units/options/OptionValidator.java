package units.options;

import java.util.HashSet;
import java.util.Set;

import roster.ValidationResult;
import units.instances.ModelInstance;
import units.instances.UnitInstance;

public class OptionValidator {

	private OptionValidator() {}
	
	public void validate(
			OptionOwner owner,
			ValidationResult result) {
		
		result.addIssues(validateSelectedOptions(owner).getIssues());
		result.addIssues(validateGroupSelectionCount(owner).getIssues());
	}
	
	private ValidationResult validateSelectedOptions(OptionOwner owner) {
		ValidationResult result = ValidationResult.create();
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
			
			ValidationResult r = o.getChoice().validate(context);
			if (r.hasIssues()) {
				result.addIssues(r.getIssues());
			}
		}
		return result;
	}
	
	private ValidationResult validateGroupSelectionCount(OptionOwner owner) {
		ValidationResult result = ValidationResult.create();
		Set<OptionGroup> groups = getGroups(owner);
		
		for (OptionGroup g : groups) {
			// TODO count how many times choices from this group have been selected by the owner
		}
		
		return result;
	}
	
	private Set<OptionGroup> getGroups(OptionOwner owner) {
		Set<OptionGroup> groups = new HashSet<>();
		for (SelectedOption s : owner.getSelectedOptions()) {
			groups.add(s.getChoice().getParentGroup());
		}
		return groups;
	}
	
	public static OptionValidator create() {
		return new OptionValidator();
	}
}
