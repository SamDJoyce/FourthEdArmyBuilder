package units.options.requirements;

import units.descriptions.models.ModelDescription;
import units.instances.UnitInstance;
import units.options.OptionChoice;
import units.options.SelectionContext;

public class MaxPerModelCountReq implements Requirement {

	private final int rate;
	private final ModelDescription model;
	private String message;
	
	public MaxPerModelCountReq(
			ModelDescription model,
			int rate
			) {
		this.rate = rate;
		this.model = model;
	}
	
	@Override
	public RequirementResult validate(SelectionContext context) {
		if (!context.hasUnit()) {
			message = "ForEachMultipleRequirement needs an UnitInstance.";
			return RequirementResult.failure(message);
		}
		if (!context.hasChoice()) {
			message = "ForEachMultipleRequirement needs a SelectedOption";
		}
		
        UnitInstance unit = context.getUnit();
        OptionChoice choice = context.getChoice();
        // Get number of models in the unit
        int modelCount = unit.getModelCount(model);
        // Number of times this choice can be selected per number of models
        int allowed = modelCount / rate;
        // Number of times this choice has been selected in this unit
        int current = unit.getOptionCount(choice);
        if (current + 1 <= allowed) {
        	return RequirementResult.success(String.format(
                    "%d/%d selections used.",
                    current,allowed));
        }
        return RequirementResult.failure(
                String.format(
                        "Only %d '%s' may be selected for %d %s%s.",
                        allowed,
                        choice.getName(),
                        modelCount,
                        model.getName(),
                        modelCount == 1 ? "" : "s"));
	}

}
