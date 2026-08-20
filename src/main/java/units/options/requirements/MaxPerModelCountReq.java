package units.options.requirements;

import roster.ValidationResult;
import units.descriptions.models.ModelDescription;
import units.instances.UnitInstance;
import units.options.OptionChoice;
import units.options.SelectionContext;

public class MaxPerModelCountReq implements Requirement {

	private final String name;
	private final int rate;
	private ModelDescription model;
	private String message;
	
	public MaxPerModelCountReq(
			String name, int rate) {
		this.name = name;
		this.rate = rate;
	}
	
	public MaxPerModelCountReq(
			String name,
			ModelDescription model,
			int rate
			) {
		this.name = name;
		this.rate = rate;
		this.model = model;
	}
	
	public String getName() {
		return name;
	}

	public void setModel(ModelDescription model) {
		this.model = model;
	}

	@Override
	public ValidationResult isMet(SelectionContext context) {
		ValidationResult result = ValidationResult.create();
		
		if (!context.hasUnit()) {
			result.addIssue("ForEachMultipleRequirement needs an UnitInstance.");
			return result;
		}
		if (!context.hasChoice()) {
			result.addIssue("ForEachMultipleRequirement needs a SelectedOption");
			return result;
		}
		
        UnitInstance unit = context.getUnit();
        OptionChoice choice = context.getChoice();
        // Get number of models in the unit
        int modelCount = unit.getModelCount(model);
        // Number of times this choice can be selected per number of models
        int allowed = modelCount / rate;
		// Number of times this choice has been selected in this unit
		int current = unit.getOptionCount(choice);
        
        if (current +1 > allowed) {
        	result.addIssue(String.format(
	                "Only %d '%s' may be selected for %d %s%s.",
	                allowed,
	                choice.getName(),
	                modelCount,
	                model.getName(),
	                modelCount == 1 ? "" : "s"));
        }
        return result;
	}
	
	@Override
	public ValidationResult validate(SelectionContext context) {
		ValidationResult result = ValidationResult.create();
        UnitInstance unit = context.getUnit();
        OptionChoice choice = context.getChoice();
        // Get number of models in the unit
        int modelCount = unit.getModelCount(model);
        // Number of times this choice can be selected per number of models
        int allowed = modelCount / rate;
		// Number of times this choice has been selected in this unit
		int current = unit.getOptionCount(choice);
		
        if (current > allowed) {
        	result.addIssue(String.format(
	                "Only %d '%s' may be selected for %d %s%s.",
	                allowed,
	                choice.getName(),
	                modelCount,
	                model.getName(),
	                modelCount == 1 ? "" : "s"));
        }
		
		return result;
	}

}
