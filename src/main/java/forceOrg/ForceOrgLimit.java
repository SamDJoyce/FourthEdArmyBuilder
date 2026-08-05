package forceOrg;

import units.UnitRole;

public class ForceOrgLimit {

	private UnitRole role;
	private int min;
	private int max;
	
	public ForceOrgLimit(
			UnitRole role, 
			int min, 
			int max) {
		this.role = role;
		this.min  = min;
		this.max  = max;
	}

	public UnitRole getRole() {
		return role;
	}

	public void setRole(UnitRole role) {
		this.role = role;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}
	
	public static ForceOrgLimit forHQ(int min, int max) {
		return new ForceOrgLimit(UnitRole.HQ, min, max);
	}
	
	public static ForceOrgLimit forElites(int min, int max) {
		return new ForceOrgLimit(UnitRole.ELITES, min, max);
	}
	
	public static ForceOrgLimit forTroops(int min, int max) {
		return new ForceOrgLimit(UnitRole.TROOPS, min, max);
	}
	
	public static ForceOrgLimit forFastAttack(int min, int max) {
		return new ForceOrgLimit(UnitRole.FAST_ATTACK, min, max);
	}

	public static ForceOrgLimit forHeavySupport(int min, int max) {
		return new ForceOrgLimit(UnitRole.HEAVY_SUPPORT, min, max);
	}
}
