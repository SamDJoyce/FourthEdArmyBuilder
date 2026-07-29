package units.options.requirements;

import units.descriptions.wargear.WargearDescription;
import units.instances.ModelInstance;
import units.options.SelectionContext;

public class CannotHaveGearReq implements Requirement {

	private final String name;
	private WargearDescription blockingGear;
	
	public CannotHaveGearReq(String name) {
		this.name = name;
	}
	
	public CannotHaveGearReq(String name, WargearDescription blockingGear) {
		this.name = name;
		this.blockingGear = blockingGear;
	}
	
	public WargearDescription getBlockingGear() {
		return blockingGear;
	}

	public void setBlockingGear(WargearDescription blockingGear) {
		this.blockingGear = blockingGear;
	}

	public String getName() {
		return name;
	}

	@Override
	public RequirementResult validate(SelectionContext context) {
		
		if (!context.hasModel()) {
			return RequirementResult.failure("Context must contain a model.");
		}
		
		ModelInstance model = context.getModel();
		if (model.hasGear(blockingGear)) {
			return RequirementResult.failure(String.format(
					"This option cannot be selected while model is equipped with %s",
					blockingGear.getName()));
		}
		
		return RequirementResult.success("Valid");
	}

}
