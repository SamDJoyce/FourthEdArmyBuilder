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
	public ValidationResult isMet(SelectionContext context) {
		
		ValidationResult result = ValidationResult.create();
		
		if (!context.hasUnit()) {
			result.addIssue("MutualExclusionReq requires a unit instance.");
			return result;
		}
		
	    UnitInstance unit = context.getUnit();
	    OptionChoice choice = context.getChoice();

	    for (OptionChoice excludedChoice : excludedChoiceNames) {
	        if (unit.hasSelection(excludedChoice)) {
	        	result.addIssue(String.format(
	        			"%s cannot be selected while %s is selected.", 
	        			choice.getName(),
	        			excludedChoice.getName()));
	        }
	    }
	    return result;
	}
	
	
	@Override
	public ValidationResult validate(SelectionContext context) {
		return isMet(context);
	}

}
