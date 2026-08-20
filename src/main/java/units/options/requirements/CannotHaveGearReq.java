package units.options.requirements;

import roster.ValidationResult;
import units.descriptions.wargear.WargearDescription;
import units.instances.ModelInstance;
import units.options.OptionChoice;
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
	public ValidationResult isMet(SelectionContext context) {
		
		ModelInstance model = context.getModel();
		OptionChoice choice = context.getChoice();
		ValidationResult result = ValidationResult.create();
		
		if (model.hasGear(blockingGear)) {
			result.addIssue(String.format(
					"%s cannot be selected while model is equipped with %s",
					choice.getName(),
					blockingGear.getName()));
		}
		
		return result;
	}
	
	@Override
	public ValidationResult validate(SelectionContext context) {
		ValidationResult result = ValidationResult.create();
		ModelInstance model = context.getModel();
		OptionChoice choice = context.getChoice();
		
		if (model.hasGear(blockingGear)) {
			result.addIssue(String.format(
					"%s cannot be selected while model is equipped with %s",
					choice.getName(),
					blockingGear.getName()));
		}
		
		return result;
	}

}
