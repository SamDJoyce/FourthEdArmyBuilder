package units.options.requirements;

import roster.ValidationResult;
import units.descriptions.wargear.WargearDescription;
import units.instances.ModelInstance;
import units.instances.WargearInstance;
import units.options.SelectionContext;

public class ArmouryPointsLimitReq implements Requirement {

	
	private final String name;
	private final int limit;
	
	public ArmouryPointsLimitReq(String name, int limit) {
		this.name = name;
		this.limit = limit;
	}
	
	public String getName() {
		return name;
	}

	public int getLimit() {
		return limit;
	}

	@Override
	public ValidationResult isMet(SelectionContext context) {
		WargearDescription gear = context.getWargear();
		ModelInstance model = context.getModel();
		ValidationResult result = ValidationResult.create();

		if (!pointsAreValid(currentArmouryPoints(model) + gear.getPoints())) {
			result.addIssue("Gear points exceed the limit of " + limit);
		}
		return result;
	}
	
	@Override
	public ValidationResult validate(SelectionContext context) {
		ValidationResult result = ValidationResult.create();
		if (!pointsAreValid(currentArmouryPoints(context.getModel()))) {
			result.addIssue("Gear points value exceeds the limit of " + limit);
		}
		return result;
	}
	
	private boolean isFromArmoury(WargearInstance gear) {
		return gear.getName().contains("armoury");
	}
	
	private boolean pointsAreValid(int points) {
		return points <= limit;
	}
	
	private int currentArmouryPoints(ModelInstance model) {
		int armouryTotal = 0;
		
		for (WargearInstance i : model.getGear()) {
			if (isFromArmoury(i)) {
				armouryTotal += i.getPoints();
			}
		}
		return armouryTotal;
	}

}
