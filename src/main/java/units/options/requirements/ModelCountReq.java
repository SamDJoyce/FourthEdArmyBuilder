package units.options.requirements;

import units.descriptions.models.ModelDescription;
import units.options.SelectionContext;

public class ModelCountReq implements Requirement {

    private final ModelDescription model;
    private final int minimum;
    private final int maximum;
    private String message;
    
    public ModelCountReq(
    		ModelDescription model,
    		int minimum,
    		int maximum
    		) {
    	this.model = model;
    	this.minimum = minimum;
    	this.maximum = maximum;
    }
	
	@Override
	public RequirementResult validate(SelectionContext context) {
		if (!context.hasUnit()) {
			message = "ModelCountRequirement needs an UnitInstance";
			return RequirementResult.failure(message);
		}
		
		int count = context.getUnit().getModelCount(model);
		boolean valid = count >= minimum && count <= maximum;
		if (valid) {
			message = String.format(
		            "%s count is valid (%d).",
		            model.getName(),
		            count
		        );
			return RequirementResult.success(message);
		}
		else {
	        message = String.format(
	                "%s count must be between %d and %d (currently %d).",
	                model.getName(),
	                minimum,
	                maximum,
	                count
	            );
	        return RequirementResult.failure(message);
		}
		
	}

}
