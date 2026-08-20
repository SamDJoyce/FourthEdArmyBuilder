package units.options.requirements;

import roster.ValidationResult;
import units.options.SelectionContext;

public class ModelCountReq implements Requirement {

	private final String name;
    private final int minimum;
    private final int maximum;
    private String message;
    
    public ModelCountReq(
    		String name,
    		int minimum,
    		int maximum
    		) {
    	this.name = name;
    	this.minimum = minimum;
    	this.maximum = maximum;
    }
	
	public String getName() {
		return name;
	}

	@Override
	public RequirementResult isMet(SelectionContext context) {
		if (!context.hasUnit()) {
			message = "ModelCountRequirement needs an UnitInstance";
			return RequirementResult.failure(message);
		}
		
		int count = context.getUnit().getCurrentSize();
		boolean valid = count >= minimum && count <= maximum;
		if (valid) {
			message = String.format(
		            "Unit count is valid (%d).",
		            count
		        );
			return RequirementResult.success(message);
		}
		else {
	        message = String.format(
	                "Unit size must be between %d and %d (currently %d).",
	                minimum,
	                maximum,
	                count
	            );
	        return RequirementResult.failure(message);
		}
		
	}
	@Override
	public ValidationResult validate(SelectionContext context) {
		RequirementResult req = isMet(context);
		ValidationResult result = ValidationResult.create();
		
		if (!req.isValid()) {
			result.addIssue(req.getMessage());
		}
		
		return result;
	}

}
