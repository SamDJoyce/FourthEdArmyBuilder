package units.options.requirements;

import roster.RosterResult;
import units.descriptions.wargear.WargearDescription;
import units.instances.ModelInstance;
import units.options.SelectionContext;

public class MustHaveGearReq implements Requirement {

	private final String name;
	private WargearDescription requiredGear;
	
	public MustHaveGearReq(String name){
		this.name = name;
	}
	
	public MustHaveGearReq(String name, WargearDescription requiredGear){
		this.name = name;
		this.requiredGear = requiredGear;
	}
	
	public String getName() {
		return name;
	}

	public void setRequiredGear(WargearDescription requiredGear) {
		this.requiredGear = requiredGear;
	}

	@Override
	public RequirementResult isMet(SelectionContext context) {
		ModelInstance model = context.getModel();
		boolean valid = model.hasGear(requiredGear);
		String message;
		if (valid) {
			message = "Model has the required gear.";
			return RequirementResult.success(message);
		}
		message = String.format(
				"Model lacks the required wargear: %s", 
				requiredGear.getName());
		return RequirementResult.failure(message);
	}

	@Override
	public RosterResult validate(SelectionContext context) {
		RosterResult result = RosterResult.create();
		RequirementResult req = isMet(context);
		
		if(!req.isValid()) {
			result.addIssue(req.getMessage());
		}
		
		return result;
	}
	
}
