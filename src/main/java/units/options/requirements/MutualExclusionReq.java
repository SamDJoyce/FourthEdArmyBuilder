package units.options.requirements;

import java.util.HashSet;
import java.util.Set;

import units.instances.UnitInstance;
import units.options.OptionChoice;
import units.options.SelectionContext;

public class MutualExclusionReq implements Requirement {

	private final String name;
	private Set<OptionChoice> excluded;
	
	public MutualExclusionReq(
			String name,
			Set<OptionChoice> excluded){
		this.name = name;
		this.excluded = new HashSet<>(excluded);
	}
	
	public String getName() {
		return name;
	}

	@Override
	public RequirementResult validate(SelectionContext context) {
		if (!context.hasUnit()) {
			return RequirementResult.failure("MutualExclusionReq requires a unit instance.");
		}
		
	    UnitInstance unit = context.getUnit();

	    for (OptionChoice excludedChoice : excluded) {
	        if (unit.hasSelection(excludedChoice)) {
	            return RequirementResult.failure(
	                excludedChoice.getName() + " is already selected."
	            );
	        }
	    }

	    return RequirementResult.success("Option may be selected.");
	}

}
