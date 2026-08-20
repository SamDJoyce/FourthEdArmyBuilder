package units.options.requirements;

import java.util.HashSet;
import java.util.Set;

import roster.ValidationResult;
import units.instances.UnitInstance;
import units.options.OptionChoice;
import units.options.SelectionContext;

public class MutualExclusionReq implements Requirement {

	private final String name;
	private Set<OptionChoice> excludedChoiceNames;
	
	public MutualExclusionReq(String name){
		this.name = name;
	}
	
	public MutualExclusionReq(
			String name,
			Set<OptionChoice> excludedChoiceNames){
		this.name = name;
		this.excludedChoiceNames = new HashSet<>(excludedChoiceNames);
	}
	
	public String getName() {
		return name;
	}

	public Set<OptionChoice> getExcluded() {
		return excludedChoiceNames;
	}

	public void setExcluded(Set<OptionChoice> excludedChoiceNames) {
		this.excludedChoiceNames = excludedChoiceNames;
	}

	@Override
	public RequirementResult isMet(SelectionContext context) {
		if (context.hasUnit()) {
			return RequirementResult.failure("MutualExclusionReq requires a unit instance.");
		}
		
	    UnitInstance unit = context.getUnit();

	    for (OptionChoice excludedChoice : excludedChoiceNames) {
	        if (unit.hasSelection(excludedChoice)) {
	            return RequirementResult.failure(
	                excludedChoice.getName() + " is already selected."
	            );
	        }
	    }

	    return RequirementResult.success("Option may be selected.");
	}
	
	
	@Override
	public ValidationResult validate(SelectionContext context) {
		ValidationResult result = ValidationResult.create();
		// TODO should this still exist?
		result.addIssue("NOT IMPLEMENTED");
		
		return result;
	}

}
