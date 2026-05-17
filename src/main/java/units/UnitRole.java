package units;

public enum UnitRole {
	HQ("HQ"),
	ELITES("Elites"),
	TROOPS("Troops"),
	FAST_ATTACK("Fast Attack"),
	HEAVY_SUPPORT("Heavy Support"),
	NONE("None");
	
	private final String role;
	
	UnitRole(String role) {
		this.role = role;
	}
	
	public Boolean isRole(UnitRole role) {
		return this == role;
	}
	
	public Boolean isHq() {
		return this == UnitRole.HQ;
	}
	
	public Boolean isElites() {
		return this == UnitRole.ELITES;
	}
	
	public Boolean isTroops() {
		return this == UnitRole.TROOPS;
	}
	
	public Boolean isFastAttack() {
		return this == UnitRole.FAST_ATTACK;
	}
	
	public Boolean isHeavySupport() {
		return this == UnitRole.HEAVY_SUPPORT;
	}
	
	public static UnitRole fromString(String s) {
		for (UnitRole r : UnitRole.values()) {
			if (r.toString().equalsIgnoreCase(s)) {
				return r;
			}
		}
		// Return None by default
		return NONE;
	}
	
	@Override
	public String toString() {
		return this.role;
	}
}
