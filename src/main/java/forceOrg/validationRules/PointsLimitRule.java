package forceOrg.validationRules;

public class PointsLimitRule implements ValidationRule {

	private int currentPoints;
	private int limit;
	
	public PointsLimitRule(
			int currentPoints,
			int limit) {
		this.limit = limit;
		this.currentPoints = currentPoints;
	}

	@Override
	public Boolean validate() {
		return currentPoints <= limit;
	}

}
