package units.options.requirements;

import roster.ValidationResult;
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
	public ValidationResult isMet(SelectionContext context) {
		ValidationResult result = ValidationResult.create();
		ModelInstance model = context.getModel();
		if (!model.hasGear(requiredGear)) {
			result.addIssue(String.format(
					"Model lacks the required wargear: %s", 
					requiredGear.getName()));
		}
		return result;
	}

	@Override
	public ValidationResult validate(SelectionContext context) {
		return isMet(context);
	}
	
}
