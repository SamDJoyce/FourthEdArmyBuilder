package roster;

public class RosterValidator {
	private Roster roster;
	
	public RosterValidator(Roster roster) {
		this.roster = roster;
	}
	
	public RosterResult validate() {
		
		// Check HQs max
		if (roster.getHQCount() > roster.getMaxHQ()) {
			return RosterResult.invalid(String.format(
					"HQ selections exceed limit of %d (currently %d)",
					roster.getMaxHQ(),
					roster.getHQCount()));
		}
		// Check HQ min
		if (roster.getHQCount() < roster.getMinHQ()) {
			return RosterResult.invalid(String.format(
					"Must make at least %d HQ selections",
					roster.getMinHQ()));
		}
		// Check Elites max
		if (roster.getElitesCount() > roster.getMaxElites()) {
			return RosterResult.invalid(String.format(
					"Elites selections exceed limit of %d (currently %d)",
					roster.getMaxElites(),
					roster.getElitesCount()));
		}
		// Check Elites min
		if (roster.getElitesCount() < roster.getMinElites()) {
			return RosterResult.invalid(String.format(
					"Must make at least %d Elites selections",
					roster.getMinElites()));
		}
		// Check Troops max
		if (roster.getTroopsCount() > roster.getMaxTroops()) {
			return RosterResult.invalid(String.format(
					"Troops selections exceed limit of %d (currently %d)",
					roster.getMaxTroops(),
					roster.getTroopsCount()));
		}
		// Check Troops min
		if (roster.getTroopsCount() < roster.getMinTroops()) {
			return RosterResult.invalid(String.format(
					"Must make at least %d Troops selections",
					roster.getMinTroops()));
		}
		// Check Fast Attack max
		if (roster.getFastAttackCount() > roster.getMaxFastAttack()) {
			return RosterResult.invalid(String.format(
					"Fast Attack selections exceed limit of %d (currently %d)",
					roster.getMaxFastAttack(),
					roster.getFastAttackCount()));
		}
		// Check Fast Attack min
		if (roster.getFastAttackCount() < roster.getMinFastAttack()) {
			return RosterResult.invalid(String.format(
					"Must make at least %d Fast Attack selections",
					roster.getMinFastAttack()));
		}
		// Check Heavy Support max
		if (roster.getHeavySupportCount() > roster.getMaxHeavySupport()) {
			return RosterResult.invalid(String.format(
					"Heavy Support selections exceed limit of %d (currently %d)",
					roster.getMaxHeavySupport(),
					roster.getHeavySupportCount()));
		}
		// Check Heavy Support min
		if (roster.getHeavySupportCount() < roster.getMinHeavySupport()) {
			return RosterResult.invalid(String.format(
					"Must make at least %d Heavy Support selections",
					roster.getMinHeavySupport()));
		}
		// Check Points
		if (roster.getCurrentPoints() > roster.getPointsLimit()) {
			return RosterResult.invalid(String.format(
					"%d point limit exceeded (currently %d)",
					roster.getPointsLimit(),
					roster.getCurrentPoints()));
		}
		
		return RosterResult.valid("Roster is valid.");
	}
	
	public static RosterValidator createFor(Roster roster) {
		return new RosterValidator(roster);
	}
}
