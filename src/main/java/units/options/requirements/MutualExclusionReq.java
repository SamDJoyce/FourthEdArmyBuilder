package units.options.requirements;

import java.util.ArrayList;
import java.util.List;

import units.instances.UnitInstance;
import units.options.OptionChoice;
import units.options.SelectionContext;

public class MutualExclusionReq implements Requirement {

	private List<OptionChoice> excluded;
	
	public MutualExclusionReq(
			List<OptionChoice> excluded){
		this.excluded = new ArrayList<>(excluded);
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
