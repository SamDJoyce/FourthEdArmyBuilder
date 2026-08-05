package forceOrg;

import java.util.Map;

import units.UnitRole;

public class ForceOrgChart {

	private String name;
	private Map<UnitRole,ForceOrgLimit> limits;
	
	public ForceOrgChart(
			String name,
			Map<UnitRole,ForceOrgLimit> limits) {
		this.name = name;
		this.limits = limits;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<UnitRole,ForceOrgLimit> getLimits() {
		return limits;
	}

	public void setLimits(
			Map<UnitRole,ForceOrgLimit> limits) {
		this.limits = limits;
	}
	
	public void addLimit(ForceOrgLimit limit) {
		limits.put(limit.getRole(),limit);
	}
	
	public void removeLimit(ForceOrgLimit limit) {
		limits.remove(limit.getRole());
	}
	
	public int getMaxRole(UnitRole role) {
		return limits.get(role).getMax();
	}
	
	public int getMinRole(UnitRole role) {
		return limits.get(role).getMin();
	}
	
	
	
}
