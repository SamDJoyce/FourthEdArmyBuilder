package units.options.requirements;

import units.options.OptionChoice;
import units.options.OptionGroup;

public class MaxSelectionReq implements Requirement {

	private int maxSelection;
	private OptionChoice choice;
	private OptionGroup group;
	
	public MaxSelectionReq(
			int maxSelection,
			OptionChoice choice,
			OptionGroup group
			) {
		this.maxSelection = maxSelection;
		this.choice = choice;
		this.group = group;
	}
	
	@Override
	public Boolean isSatisfied() {
		int count = 0;
		
		// If no Selection has been made, return true
		if (group != null 
		&& !group.getChoices().contains(choice)) {
			return true;
		}
		// Else, check the number of choices
		for (OptionChoice oc : group.getChoices()) {
			if (choice != null && choice.equals(oc)) {
				count++;	
			}
		}

		return count <= maxSelection;
	}
}
