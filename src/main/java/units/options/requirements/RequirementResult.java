package units.options.requirements;

public class RequirementResult {
	private final boolean valid;
	private final String message;
	
	public RequirementResult(boolean valid, String message) {
		this.valid = valid;
		this.message = message;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public String getMessage() {
		return message;
	}
	
	public static RequirementResult success(String message) {
	    return new RequirementResult(true, message);
	}

	public static RequirementResult failure(String message) {
	    return new RequirementResult(false, message);
	}
}
