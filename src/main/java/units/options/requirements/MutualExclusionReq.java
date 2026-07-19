package units.options.requirements;

import java.util.ArrayList;
import java.util.List;

import units.instances.UnitInstance;
import units.options.OptionChoice;
import units.options.SelectionContext;

public class MutualExclusionReq implements Requirement {

	private List<OptionChoice> excluded;
	private String message;
	
	public MutualExclusionReq(
			List<OptionChoice> excluded){
		this.excluded = new ArrayList<>( excluded);
	}
	
	@Override
	public RequirementResult validate(SelectionContext context) {
		UnitInstance unit = context.getUnit();
		
		for (OptionChoice choice : excluded) {
			if (unit.getOptionCount(choice) > 0) {
				message = String.format("%s cannot currently be selected", choice.getName());
				return RequirementResult.failure(message);
			}
		}
		message = "Option can be selected.";
		return RequirementResult.success(message);
	}

}
