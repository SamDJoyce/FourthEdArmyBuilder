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

}
