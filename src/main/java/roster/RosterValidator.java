package roster;

import java.util.Map;

import forceOrg.ForceOrgLimit;
import units.UnitRole;

public class RosterValidator {
	private static final UnitRole HQ = UnitRole.HQ;
	private static final UnitRole ELITES = UnitRole.ELITES;
	private static final UnitRole TROOPS = UnitRole.TROOPS;
	private static final UnitRole FAST_ATTACK = UnitRole.FAST_ATTACK;
	private static final UnitRole HEAVY_SUPPORT = UnitRole.HEAVY_SUPPORT;
	
	public RosterValidator() {;
	}
	
	public static RosterResponse validate(Roster roster) {
		Map<UnitRole,ForceOrgLimit> limits = roster.getChart().getLimits();
		
		// Check HQs max
		if (roster.getHQCount() > limits.get(HQ).getMax()) {
			return RosterResponse.invalid(String.format(
					"HQ selections exceed limit of %d",
					limits.get(HQ).getMax()));
		}
		// Check HQ min
		if (roster.getHQCount() < limits.get(HQ).getMin()) {
			return RosterResponse.invalid(String.format(
					"Must make at least %d HQ selections",
					limits.get(HQ).getMin()));
		}
		// Check Elites max
		if (roster.getElitesCount() > limits.get(ELITES).getMax()) {
			return RosterResponse.invalid(String.format(
					"Elites selections exceed limit of %d",
					limits.get(ELITES).getMax()));
		}
		// Check Elites min
		if (roster.getElitesCount() < limits.get(ELITES).getMin()) {
			return RosterResponse.invalid(String.format(
					"Must make at least %d Elites selections",
					limits.get(ELITES).getMin()));
		}
		// Check Troops max
		if (roster.getTroopsCount() > limits.get(TROOPS).getMax()) {
			return RosterResponse.invalid(String.format(
					"Troops selections exceed limit of %d",
					limits.get(TROOPS).getMax()));
		}
		// Check Troops min
		if (roster.getTroopsCount() < limits.get(TROOPS).getMin()) {
			return RosterResponse.invalid(String.format(
					"Must make at least %d Troops selections",
					limits.get(TROOPS).getMin()));
		}
		// Check Fast Attack max
		if (roster.getFastAttackCount() > limits.get(FAST_ATTACK).getMax()) {
			return RosterResponse.invalid(String.format(
					"Fast Attack selections exceed limit of %d",
					limits.get(FAST_ATTACK).getMax()));
		}
		// Check Fast Attack min
		if (roster.getFastAttackCount() < limits.get(FAST_ATTACK).getMin()) {
			return RosterResponse.invalid(String.format(
					"Must make at least %d Fast Attack selections",
					limits.get(FAST_ATTACK).getMin()));
		}
		// Check Heavy Support max
		if (roster.getHeavySupportCount() > limits.get(HEAVY_SUPPORT).getMax()) {
			return RosterResponse.invalid(String.format(
					"Heavy Support selections exceed limit of %d",
					limits.get(HEAVY_SUPPORT).getMax()));
		}
		// Check Heavy Support min
		if (roster.getHeavySupportCount() < limits.get(HEAVY_SUPPORT).getMin()) {
			return RosterResponse.invalid(String.format(
					"Must make at least %d Heavy Support selections",
					limits.get(HEAVY_SUPPORT).getMin()));
		}
		return RosterResponse.valid("Selections valid.");
	}
}
