package forceOrg.validationRules;

import forceOrg.ForceOrgLimit;

public class MinRoleSelectionRule implements ValidationRule {

	private int selections;
	private ForceOrgLimit limit;
	
	public MinRoleSelectionRule(
			int selections,
			ForceOrgLimit limit) {
		this.selections = selections;
		this.limit = limit;
	}

	@Override
	public Boolean validate() {
		return selections >= limit.getMin();
	}

}
