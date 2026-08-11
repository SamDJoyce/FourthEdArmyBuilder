package units.options.requirements;

import roster.RosterResult;
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
	public RequirementResult isMet(SelectionContext context) {
		int count = getSelectionCount(context);
		
		if (count + 1 > maxSelection) {
			return RequirementResult.failure(String.format(
					"To many selections from %s. Maximum: %d",
					context.getChoice().getParentGroup().getName(),
					maxSelection));
		}
		return RequirementResult.success("Valid");
	}
	
	@Override
	public RosterResult validate(SelectionContext context) {
		RosterResult result = new RosterResult();
		int count = getSelectionCount(context);
		
		if (count > maxSelection) {
			result.addIssue((String.format(
					"To many selections from %s. Maximum: %d",
					context.getChoice().getParentGroup().getName(),
					maxSelection)));
		}
		return result;
	}
	
	public int getSelectionCount(SelectionContext context){
		UnitInstance unit  = context.getUnit();
		OptionGroup  group = context.getChoice().getParentGroup();
		int count = 0;
		for (ModelInstance m : unit.getModels()) {
			for (SelectedOption s : m.getSelectedOptions()) {
				if (group.getChoices().contains(s.getChoice())) {
					count++;
				}
			}
		}
		return count;
	}
	
}
