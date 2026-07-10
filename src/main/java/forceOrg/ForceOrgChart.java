package forceOrg;

import java.util.List;

import forceOrg.validationRules.ValidationRule;
import units.UnitRole;

public class ForceOrgChart {

	private String id;
	private String name;
	private List<ForceOrgLimit> limits;
	private List<ValidationRule> rules;
	
	public ForceOrgChart(
			String id,
			String name,
			List<ForceOrgLimit> limits,
			List<ValidationRule> rules) {
		this.id = id;
		this.name = name;
		this.limits = limits;
		this.rules = rules;
	}
	
	public Boolean validate() {
		for (ValidationRule r : rules) {
			if (!r.validate()) {
				return false;
			}
		}
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ForceOrgLimit> getLimits() {
		return limits;
	}

	public void setLimits(List<ForceOrgLimit> limits) {
		this.limits = limits;
	}
	
	public Boolean addLimit(ForceOrgLimit limit) {
		return limits.add(limit);
	}
	
	public Boolean removeLimit(ForceOrgLimit limit) {
		return limits.remove(limit);
	}
	
	public int getMaxHQ() {
		for (ForceOrgLimit l : limits) {
			if (UnitRole.HQ.equals(l.getRole())) {
				return l.getMax();
			}
		}
		return 0;
	}
	
	public int getMinHQ() {
		for (ForceOrgLimit l : limits) {
			if (UnitRole.HQ.equals(l.getRole())) {
				return l.getMin();
			}
		}
		return 0;
	}

	public List<ValidationRule> getRules() {
		return rules;
	}

	public void setRules(List<ValidationRule> rules) {
		this.rules = rules;
	}

	public Boolean addRule(ValidationRule rule) {
		return this.rules.add(rule);
	}
	
	public Boolean removeRule(ValidationRule rule) {
		return this.rules.remove(rule);
	}
	
}
