package units.options.requirements;

import units.UnitType;
import units.instances.ModelInstance;

public class MustHaveTypeReq implements Requirement {

	private final UnitType type;
	
	public MustHaveTypeReq(UnitType type) {
		this.type = type;
	}
	
	@Override
	public RequirementResult validate(RequirementContext context) {
		ModelInstance model = context.getModel();
		boolean valid = model.isType(type);
		String message;
		if (valid) {
			message = "Model has the required type.";
			return RequirementResult.success(message);
		}
		message = "Model lacks the required type.";
		return RequirementResult.failure(message);
	}

}
