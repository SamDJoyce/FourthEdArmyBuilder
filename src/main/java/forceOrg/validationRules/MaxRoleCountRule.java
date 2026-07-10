package forceOrg.validationRules;

import forceOrg.ForceOrgLimit;

public class MaxRoleCountRule implements ValidationRule {
	
	private int selections;
	private ForceOrgLimit limit;
	
	public MaxRoleCountRule(
			int selections,
			ForceOrgLimit limit) {
	}

	@Override
	public Boolean validate() {
		return selections <= limit.getMax();
	}

}
