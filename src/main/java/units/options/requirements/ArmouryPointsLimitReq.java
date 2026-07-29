package units.options.requirements;

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
	public RequirementResult validate(SelectionContext context) {
		WargearDescription gear = context.getWargear();
		ModelInstance model = context.getModel();
		int armouryTotal = 0;
		
		for (WargearInstance i : model.getGear()) {
			if (isFromArmoury(i)) {
				armouryTotal += i.getPoints();
			}
		}
		
		boolean valid = armouryTotal + gear.getPoints() <= limit;
		if (valid) {
			return RequirementResult.success("Gear points value meets the limit of " + limit);
		}
		return RequirementResult.failure("Gear points value exceeds the limit of " + limit);
	}
	
	private boolean isFromArmoury(WargearInstance gear) {
		return gear.getName().contains("armoury");
	}

}
