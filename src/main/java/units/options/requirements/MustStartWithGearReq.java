package units.options.requirements;

import roster.RosterResult;
import units.descriptions.models.ModelDescription;
import units.descriptions.wargear.WargearDescription;
import units.options.SelectionContext;

public class MustStartWithGearReq implements Requirement {

	final String name;
	private WargearDescription requiredGear;
	
	public MustStartWithGearReq(String name) {
		this.name = name;
		
	}
	
	public MustStartWithGearReq(String name, WargearDescription requiredGear) {
		this.name = name;
		this.requiredGear = requiredGear;
	}
	
	public WargearDescription getRequiredGear() {
		return requiredGear;
	}

	public void setRequiredGear(WargearDescription requiredGear) {
		this.requiredGear = requiredGear;
	}

	public String getName() {
		return name;
	}

	@Override
	public RequirementResult isMet(SelectionContext context) {
		ModelDescription model = context.getModel().getDescription();
		if(model.startsWithGear(requiredGear)) {
			return RequirementResult.success(String.format(
					"Required %s is present", 
					requiredGear.getName()));
		}
		return RequirementResult.failure(String.format(
				"Model must be equipped with %s by default to select %s", 
				requiredGear.getName(),
				context.getChoice().getName()));
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
