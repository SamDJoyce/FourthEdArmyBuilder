package units.options.requirements;

import units.UnitDescription;

public class MinSquadSizeReq implements Requirement {
	private int minSize;
	private UnitDescription unit;

	public MinSquadSizeReq(
			UnitDescription unit,
			int minSize) {
		this.unit = unit;
		this.minSize = minSize;
	}
	
	@Override
	public Boolean isSatisfied() {
		return unit.sizeIsValid()
		    && unit.getCurrentSize() >= this.minSize;
	}
}
