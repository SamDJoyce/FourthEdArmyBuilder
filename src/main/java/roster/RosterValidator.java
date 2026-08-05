package roster;

import forceOrg.ForceOrgLimit;
import units.UnitRole;

public class RosterValidator {
	
	private RosterValidator() {
	}
	
	public RosterResult validate(Roster roster) {
		
		RosterResult result = new RosterResult();
		
		validateRoles(roster, result);
		validatePoints(roster, result);
		
		return result;
	}
	
	private void validateRoles(Roster roster, RosterResult results) {
		for (ForceOrgLimit limit : roster.getChart().getLimits().values()) {

		    UnitRole role = limit.getRole();

		    int count = roster.getCountByRole(role);

		    if (count < limit.getMin()) {
		    	results.addIssue(String.format(
		    			"Must make at least %d %s selections (currently %d)",
						limit.getMin(),
						role,
						count
						)) ;
		    }

		    if (count > limit.getMax()) {
		    	results.addIssue(String.format(
		    			"%s selections exceed limit of %d (currently %d)",
						role,
						limit.getMax(),
						count
						));
		    }
		}
	}
	
	private void validatePoints(Roster roster, RosterResult results) {
		if (roster.getCurrentPoints() > roster.getPointsLimit()) {
			results.addIssue(String.format(
					"Roster exceeds limit of %d (currently %d)", 
					roster.getPointsLimit(),
					roster.getCurrentPoints()
					));
		}
	}
	
	public static RosterValidator create() {
		return new RosterValidator();
	}
}
