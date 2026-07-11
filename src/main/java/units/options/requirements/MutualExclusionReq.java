package units.options.requirements;

import java.util.List;

import units.options.OptionChoice;
import units.options.OptionGroup;

public class MutualExclusionReq implements Requirement {

	private List<OptionChoice> blockers;
	private OptionGroup group;
	
	public MutualExclusionReq(
			List<OptionChoice> blockers,
			OptionGroup group){
		this.blockers = blockers;
		this.group = group;
	}
	
	@Override
	public Boolean isSatisfied() {
		for (OptionChoice c : group.getChoices()) {
			if (c.isSelected() 
			&&  blockers.contains(c)) {
				return false;
			}
		}
		return true;
	}

}
