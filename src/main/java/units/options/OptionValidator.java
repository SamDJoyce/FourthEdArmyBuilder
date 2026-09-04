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
		
		if (groups == null|| groups.isEmpty()) {
			return result;
		}
		
		for (OptionGroup g : groups) {
			int count = 0 ;
			for (SelectedOption s : owner.getSelectedOptions()) {
				if (g.containsChoice(s.getChoice())) {
					count ++;
				}
			}
			if (notEnoughSelections(g, count)) {
				result.addIssue(String.format(
						"Too few selections in %s: Minimum %d (currently %d)", 
						g.getName(),
						g.getMinSelections(),
						count));
			}
			if (tooManySelections(g, count)) {
				result.addIssue(String.format(
						"Too many selection in %s: Maximum %d (currently %d)", 
						g.getName(),
						g.getMaxSelections(),
						count));
			}
		}
		return result;
	}
	
	private boolean notEnoughSelections(OptionGroup group, int count) {
		return count < group.getMinSelections();
	}
	
	private boolean tooManySelections(OptionGroup group, int count) {
		return count > group.getMaxSelections();
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
