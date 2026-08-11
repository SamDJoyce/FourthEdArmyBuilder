package units.options.requirements;

import roster.RosterResult;
import units.UnitType;
import units.instances.ModelInstance;
import units.options.SelectionContext;

public class MustHaveTypeReq implements Requirement {

	private final String name;
	private UnitType requiredType;
	
	public MustHaveTypeReq(String name, UnitType requiredType) {
		this.name = name;
		this.requiredType = requiredType;
	}
	
	public String getRequiredType() {
		return this.requiredType.toString();
	}
	
	public String getName() {
		return name;
	}

	public void setRequiredType(UnitType requiredType) {
		this.requiredType = requiredType;
	}

	@Override
	public RequirementResult isMet(SelectionContext context) {
		ModelInstance model = context.getModel();
		boolean valid = model.isType(requiredType);
		if (valid) {
			return RequirementResult.success("Model has the required Type.");
		}
		return RequirementResult.failure(String.format(
				"Model lacks the required Type: %s.", 
				requiredType.toString()));
	}

	@Override
	public RosterResult validate(SelectionContext context) {
		RosterResult result = new RosterResult();
		RequirementResult req = isMet(context);
		
		if (!req.isValid()) {
			result.addIssue(req.getMessage());
		}
		
		return result;
	}
	
}
