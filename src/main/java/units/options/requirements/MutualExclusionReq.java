package units.options.requirements;

import java.util.ArrayList;
import java.util.List;

import units.instances.UnitInstance;
import units.options.OptionChoice;
import units.options.SelectionContext;

public class MutualExclusionReq implements Requirement {

	private final String name;
	private List<OptionChoice> excluded;
	
	public MutualExclusionReq(
			String name,
			List<OptionChoice> excluded){
		this.name = name;
		this.excluded = new ArrayList<>(excluded);
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
