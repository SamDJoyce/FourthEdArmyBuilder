package units.options.requirements;

import units.options.OptionChoice;
import units.options.OptionGroup;

public class MutualExclusionReq implements Requirement {

	private OptionGroup group;
	private OptionChoice excluded;
	
	public MutualExclusionReq(
			OptionGroup group, 
			OptionChoice excluded){
		this.group = group;
		this.excluded = excluded;
	}
	
	@Override
	public Boolean isSatisfied() {
		return !group.getChoices().contains(excluded);
	}

}
