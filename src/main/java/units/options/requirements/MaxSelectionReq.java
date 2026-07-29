package units.options.requirements;

import units.instances.ModelInstance;
import units.instances.UnitInstance;
import units.options.OptionGroup;
import units.options.SelectedOption;
import units.options.SelectionContext;

public class MaxSelectionReq implements Requirement {

	private final String name;
	private int maxSelection;
	private String message;
	
	public MaxSelectionReq(
			String name,
			int maxSelection
			) {
		this.name = name;
		this.maxSelection = maxSelection;
	}
	
	public int getMaxSelection() {
		return maxSelection;
	}

	public void setMaxSelection(int maxSelection) {
		this.maxSelection = maxSelection;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}


	@Override
	public RequirementResult validate(SelectionContext context) {
		UnitInstance unit = context.getUnit();
		OptionGroup group = context.getChoice().getParentGroup();
		
		int count = 0;
		for (ModelInstance m : unit.getModels()) {
			for (SelectedOption s : m.getSelectedOptions()) {
				if (group.getChoices().contains(s.getChoice())) {
					count++;
				}
			}
		}
		
		boolean valid = count <= maxSelection;
		if (valid) {
			return RequirementResult.success("Valid");
		}
		return RequirementResult.failure(String.format(
				"To many selections. Maximum: %d",
				maxSelection));
	}
}
