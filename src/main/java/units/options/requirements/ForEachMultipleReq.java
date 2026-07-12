package units.options.requirements;

import units.UnitDescription;

public class ForEachMultipleReq implements Requirement {


	private int divisor;
	private int modelCount;
	
	public ForEachMultipleReq(UnitDescription unit, int divisor) {
		this.divisor = divisor;
		this.modelCount = unit.getCurrentSize();
	}
	
	@Override
	public Boolean isSatisfied() {
		return modelCount / divisor > 0;
	}

}
