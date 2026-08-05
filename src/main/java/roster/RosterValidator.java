package roster;

import forceOrg.ForceOrgLimit;
import units.UnitRole;
import units.instances.UnitInstance;

public class RosterValidator {
	
	private RosterValidator() {
	}
	
	public RosterResult validate(Roster roster) {
		
		RosterResult result = new RosterResult();
		
		validateRoles(roster, result);
		validatePoints(roster, result);
		validateUnits(roster, result);
		
		return result;
	}
	
	private void validateRoles(Roster roster, RosterResult result) {
		for (ForceOrgLimit limit : roster.getChart().getLimits().values()) {

		    UnitRole role = limit.getRole();

		    int count = roster.getCountByRole(role);

		    if (count < limit.getMin()) {
		    	result.addIssue(String.format(
		    			"Must make at least %d %s selections (currently %d)",
						limit.getMin(),
						role,
						count
						)) ;
		    }

		    if (count > limit.getMax()) {
		    	result.addIssue(String.format(
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
	
	private void validateUnits(Roster roster, RosterResult result) {
		for (UnitInstance u : roster.getUnits()) {
			RosterResult r = u.validate();
			if (r.hasIssues()) {
				result.addIssues(r.getIssues());
			}
		}
	}
	
	public static RosterValidator create() {
		return new RosterValidator();
	}
}
