package units.options.requirements;

import units.UnitType;
import units.instances.ModelInstance;
import units.options.SelectionContext;

public class MustHaveTypeReq implements Requirement {

	private final String name;
	private final UnitType requiredType;
	
	public MustHaveTypeReq(String name, UnitType requiredType) {
		this.name = name;
		this.requiredType = requiredType;
	}
	
	public String getRequiredType() {
		return this.requiredType.toString();
	}
	
	@Override
	public RequirementResult validate(SelectionContext context) {
		ModelInstance model = context.getModel();
		boolean valid = model.isType(requiredType);
		String message;
		if (valid) {
			message = "Model has the required requiredType.";
			return RequirementResult.success(message);
		}
		message = "Model lacks the required requiredType.";
		return RequirementResult.failure(message);
	}

}
