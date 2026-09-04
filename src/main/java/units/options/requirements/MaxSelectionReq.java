package units.options.requirements;

import roster.ValidationResult;
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
	public ValidationResult isMet(SelectionContext context) {
		ValidationResult result = ValidationResult.create();
		int count = getSelectionCount(context);
		System.out.printf("!!! Selection Count = %d !!!\n", count);
		if (count + 1 > maxSelection) {
			result.addIssue(String.format(
					"Too many selections from %s. Maximum: %d",
					context.getChoice().getParentGroup().getName(),
					maxSelection));
		}
		return result;
	}
	
	@Override
	public ValidationResult validate(SelectionContext context) {
		ValidationResult result = ValidationResult.create();
		int count = getSelectionCount(context);
		System.out.printf("!!! Selection Count = %d !!!\n", count);
		if (count > maxSelection) {
			result.addIssue(String.format(
					"Too many selections from %s. Maximum: %d",
					context.getChoice().getParentGroup().getName(),
					maxSelection));
		}
		return result;
	}
	
	public int getSelectionCount(SelectionContext context){
		UnitInstance unit = context.getUnit();
		OptionGroup  group = context.getChoice().getParentGroup();
		int count = 0;
		for (ModelInstance m : unit.getModels()) {
			for (SelectedOption s : m.getSelectedOptions()) {
				if (group.containsChoice(s.getChoice())) {
					System.out.println("!!! Found a matching choice !!!");
					count++;
				}
			}
		}
		return count;
	}
	
}
