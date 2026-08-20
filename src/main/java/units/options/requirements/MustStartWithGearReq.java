package units.options.requirements;

import roster.ValidationResult;
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
	public ValidationResult isMet(SelectionContext context) {
		ValidationResult result = ValidationResult.create();
		ModelDescription model = context.getModel().getDescription();
		if(!model.startsWithGear(requiredGear)) {
			result.addIssue(String.format(
							"Model must be equipped with %s by default to select %s", 
							requiredGear.getName(),
							context.getChoice().getName()));
		};
		return result;
	}

	@Override
	public ValidationResult validate(SelectionContext context) {
		return isMet(context);
	}

}
