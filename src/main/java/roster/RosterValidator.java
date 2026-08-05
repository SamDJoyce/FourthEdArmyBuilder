package roster;

import java.util.ArrayList;
import java.util.List;

public class RosterValidator {
	private Roster roster;
	
	public RosterValidator(Roster roster) {
		this.roster = roster;
	}
	

	
	public RosterResult validate() {
		
		List<RosterResult> results = new ArrayList<>();
		
		// Check HQs max
		if (!hqMaxIsValid()) {
			results.add(RosterResult.invalid(String.format(
					"HQ selections exceed limit of %d (currently %d)",
					roster.getMaxHQ(),
					roster.getHQCount()
					))) ;
		}
		// Check HQ min
		if (!hqMinIsValid()) {
			results.add(RosterResult.invalid(String.format(
					"Must make at least %d HQ selections (currently %d)",
					roster.getMinHQ(),
					roster.getHQCount()
					)));
		}
		// Check Elites max
		if (!elitesMaxIsValid()) {
			results.add(RosterResult.invalid(String.format(
					"Elites selections exceed limit of %d (currently %d)",
					roster.getMaxElites(),
					roster.getElitesCount()
					)));
		}
		// Check Elites min
		if (!elitesMinIsValid()) {
			results.add(RosterResult.invalid(String.format(
					"Must make at least %d Elites selections (currently %d)",
					roster.getMinElites(),
					roster.getElitesCount()
					)));
		}
		// Check Troops max
		if (!troopsMaxIsValid()) {
			results.add(RosterResult.invalid(String.format(
					"Troops selections exceed limit of %d (currently %d)",
					roster.getMaxTroops(),
					roster.getTroopsCount()
					)));
		}
		// Check Troops min
		if (!troopsMinIsValid()) {
			results.add( RosterResult.invalid(String.format(
					"Must make at least %d Troops selections (currently %d)",
					roster.getMinTroops(),
					roster.getTroopsCount()
					)));
		}
		// Check Fast Attack max
		if (!fastAttackMaxIsValid()) {
			results.add( RosterResult.invalid(String.format(
					"Fast Attack selections exceed limit of %d (currently %d)",
					roster.getMaxFastAttack(),
					roster.getFastAttackCount()
					)));
		}
		// Check Fast Attack min
		if (!fastAttackMinIsValid()) {
			results.add( RosterResult.invalid(String.format(
					"Must make at least %d Fast Attack selections (currently %d)",
					roster.getMinFastAttack(),
					roster.getFastAttackCount()
					)));
		}
		// Check Heavy Support max
		if (!heavySupportMaxIsValid()) {
			results.add( RosterResult.invalid(String.format(
					"Heavy Support selections exceed limit of %d (currently %d)",
					roster.getMaxHeavySupport(),
					roster.getHeavySupportCount()
					)));
		}
		// Check Heavy Support min
		if (!heavySupportMinIsValid()) {
			results.add( RosterResult.invalid(String.format(
					"Must make at least %d Heavy Support selections (currently %d)",
					roster.getMinHeavySupport(),
					roster.getHeavySupportCount()
					)));
		}
		// Check Points
		if (!pointsAreValid()) {
			results.add( RosterResult.invalid(String.format(
					"%d point limit exceeded (currently %d)",
					roster.getPointsLimit(),
					roster.getCurrentPoints()
					)));
		}
		
		return consolidateResults(results);
	}
	
	public static RosterValidator createFor(Roster roster) {
		return new RosterValidator(roster);
	}
	
	private RosterResult consolidateResults(List<RosterResult> results) {
		if (noRosterErrors(results)) {
			return RosterResult.valid("Roster is valid");
		}
		
		RosterResult invalid = RosterResult.invalid("");
		for (RosterResult r : results) {
			invalid.setMessage(String.format(
					"%s\n%s", 
					invalid.getMessage(),
					r.getMessage()
					));
		}
		return invalid;
	}
	
	private boolean noRosterErrors(List<RosterResult> results) {
		return results == null 
			|| results.isEmpty();
	}
	
	private boolean hqMaxIsValid() {
		return roster.getHQCount() <= roster.getMaxHQ();
	}
	
	private boolean hqMinIsValid() {
		return roster.getHQCount() >= roster.getMinHQ();
	}
	
	private boolean elitesMaxIsValid() {
		return roster.getElitesCount() <= roster.getMaxElites();
	}
	
	private boolean elitesMinIsValid() {
		return roster.getElitesCount() >= roster.getMinElites();
	}
	
	private boolean  troopsMaxIsValid() {
		return roster.getTroopsCount() <= roster.getMaxTroops();
	}
	
	private boolean troopsMinIsValid() {
		return roster.getTroopsCount() >= roster.getMinTroops();
	}
	
	private boolean fastAttackMaxIsValid() {
		return roster.getFastAttackCount() <= roster.getMaxFastAttack();
	}
	
	private boolean fastAttackMinIsValid() {
		return roster.getFastAttackCount() >= roster.getMinFastAttack();
	}
	
	private boolean heavySupportMaxIsValid() {
		return roster.getHeavySupportCount() <= roster.getMaxHeavySupport();
	}
	
	private boolean heavySupportMinIsValid() {
		return roster.getHeavySupportCount() >= roster.getMinHeavySupport();
	}
	
	private boolean pointsAreValid() {
		return roster.getCurrentPoints() <= roster.getPointsLimit();
	}
}
