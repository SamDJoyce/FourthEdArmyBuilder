package units.options.requirements;

import units.descriptions.models.ModelDescription;
import units.options.ValidationContext;

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
	public ValidationResult validate(ValidationContext context) {
		if (!context.hasUnit()) {
			message = "ForEachMultipleRequirement needs an UnitInstance.";
			return ValidationResult.failure(message);
		}
		if (!context.hasOption()) {
			message = "ForEachMultipleRequirement needs a SelectedOption";
		}
		
        int modelCount = context.getUnit().getModelCount(model);
		int allowedSelections = modelCount / rate;
		int selected = context.getOption().getNumSelected();
		
       if (selected <= allowedSelections) {
            return ValidationResult.success(
                "Selection limit satisfied."
            );
       }
       return ValidationResult.failure(
               String.format(
                   "Only %d selections allowed for %d %s.",
                   allowedSelections,
                   modelCount,
                   model.getName()
               ));
	}
	
	

}
