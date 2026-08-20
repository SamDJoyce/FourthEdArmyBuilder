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
	public ValidationResult isMet(SelectionContext context) {
		ValidationResult result = ValidationResult.create();
		
		if (!context.hasUnit()) {
			result.addIssue("ModelCountRequirement needs an UnitInstance");
			return result;
		}
		
		int count = context.getUnit().getCurrentSize();
		boolean valid = count >= minimum && count <= maximum;
		if (!valid) {
			result.addIssue(String.format(
	                "Unit size must be between %d and %d (currently %d).",
	                minimum,
	                maximum,
	                count
	            ));
		}
		 return result;
	}
	@Override
	public ValidationResult validate(SelectionContext context) {
		return isMet(context);
	}

}
