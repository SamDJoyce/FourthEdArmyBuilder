package units.options.requirements;

import roster.ValidationResult;
import units.descriptions.wargear.WargearDescription;
import units.instances.ModelInstance;
import units.instances.WargearInstance;
import units.options.OptionChoice;
import units.options.SelectedOption;
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
		ModelInstance model = context.getModel();
		ValidationResult result = ValidationResult.create();
		OptionChoice choice = context.getChoice();

		if (!pointsAreValid(currentArmouryPoints(model) + choice.getPoints())) {
			result.addIssue("Gear points exceed the limit of " + limit);
		}
		return result;
	}
	
	@Override
	public ValidationResult validate(SelectionContext context) {
		ValidationResult result = ValidationResult.create();
		if (!pointsAreValid(currentArmouryPoints(context.getModel()))) {
			result.addIssue("Gear points value of equipment from the armoury exceeds the limit of " + limit);
		}
		return result;
	}
	
	private boolean isFromArmoury(SelectedOption option) {
		return option.getChoice().getName().contains("armoury");
	}
	
	private boolean pointsAreValid(int points) {
		return points <= limit;
	}
	
	private int currentArmouryPoints(ModelInstance model) {
		int armouryTotal = 0;
		
		for (SelectedOption o : model.getSelectedOptions()) {
			if (isFromArmoury(o)) {
				armouryTotal += o.getPoints();
			}
		}
		
		return armouryTotal;
	}

}
