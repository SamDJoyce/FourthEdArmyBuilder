package units.options.requirements;

import units.descriptions.UnitDescription;
import units.options.OptionChoice;
import units.options.OptionGroup;

public class ForEachMultipleReq implements Requirement {


	private int divisor;
	private int modelCount;
	private int numSelected;
	private UnitDescription unit;
	private OptionChoice choice;
	
	public ForEachMultipleReq(
			UnitDescription unit,
			OptionChoice choice, 
			int divisor) {
		this.divisor = divisor;
		this.unit = unit;
		this.choice = choice;
		this.modelCount = unit.getCurrentSize();
		this.numSelected = getCurrentSelectionCount();
	}
	
	@Override
	public Boolean isSatisfied() {
		return (modelCount / divisor) - numSelected > 0;
	}
	
	private int getCurrentSelectionCount() {
		int count = 0;
		for (OptionGroup g : unit.getOptions()) {
			count += g.getCurrentSelectionCount(choice);
		}
		return count;
	}
	

}
