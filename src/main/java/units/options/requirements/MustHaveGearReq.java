package units.options.requirements;

import units.descriptions.wargear.WargearDescription;
import units.instances.ModelInstance;

public class MustHaveGearReq implements Requirement {

	private final WargearDescription requiredGear;
	
	public MustHaveGearReq(WargearDescription requiredGear){
		this.requiredGear = requiredGear;
	}
	
	@Override
	public RequirementResult validate(RequirementContext context) {
		ModelInstance model = context.getModel();
		boolean valid = model.hasGear(requiredGear);
		String message;
		if (valid) {
			message = "Model has the required gear.";
			return RequirementResult.success(message);
		}
		message = "Model lacks the required equipment";
		return RequirementResult.failure(message);
	}

}
