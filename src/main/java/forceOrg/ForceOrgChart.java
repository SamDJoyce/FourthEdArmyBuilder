package forceOrg;

import java.util.ArrayList;
import java.util.List;

import forceOrg.validationRules.ValidationRule;
import units.UnitRole;
import units.descriptions.UnitDescription;

public class ForceOrgChart {

	private String id;
	private String name;
	private List<ForceOrgLimit> limits;
	private List<ValidationRule> rules;
	private List<UnitDescription> units;
	
	public ForceOrgChart(
			String id,
			String name,
			List<ForceOrgLimit> limits,
			List<ValidationRule> rules) {
		this.id = id;
		this.name = name;
		this.limits = limits;
		this.rules = rules;
		units = new ArrayList<>();
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
	
	public int getMaxElites() {
		for (ForceOrgLimit l : limits) {
			if (UnitRole.ELITES.equals(l.getRole())) {
				return l.getMax();
			}
		}
		return 0;
	}
	
	public int getMinElites() {
		for (ForceOrgLimit l : limits) {
			if (UnitRole.ELITES.equals(l.getRole())) {
				return l.getMin();
			}
		}
		return 0;
	}
	
	public int getMaxTroops() {
		for (ForceOrgLimit l : limits) {
			if (UnitRole.TROOPS.equals(l.getRole())) {
				return l.getMax();
			}
		}
		return 0;
	}
	
	public int getMinTroops() {
		for (ForceOrgLimit l : limits) {
			if (UnitRole.TROOPS.equals(l.getRole())) {
				return l.getMin();
			}
		}
		return 0;
	}
	
	public int getMaxFastAttack() {
		for (ForceOrgLimit l : limits) {
			if (UnitRole.FAST_ATTACK.equals(l.getRole())) {
				return l.getMax();
			}
		}
		return 0;
	}
	
	public int getMinFastAttack() {
		for (ForceOrgLimit l : limits) {
			if (UnitRole.FAST_ATTACK.equals(l.getRole())) {
				return l.getMin();
			}
		}
		return 0;
	}
	
	public int getMaxHeavySupport() {
		for (ForceOrgLimit l : limits) {
			if (UnitRole.HEAVY_SUPPORT.equals(l.getRole())) {
				return l.getMax();
			}
		}
		return 0;
	}
	
	public int getMinHeavySupport() {
		for (ForceOrgLimit l : limits) {
			if (UnitRole.HEAVY_SUPPORT.equals(l.getRole())) {
				return l.getMin();
			}
		}
		return 0;
	}
	
	public int getMaxRole(UnitRole role) {
		for (ForceOrgLimit l : limits) {
			if (l.getRole().equals(role)) {
				return l.getMax();
			}
		}
		return 0;
	}
	
	public int getMinRole(UnitRole role) {
		for (ForceOrgLimit l : limits) {
			if (l.getRole().equals(role)) {
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
	
	public int getHQCount(){
		int total = 0;
		for (UnitDescription u : units) {
			if(UnitRole.HQ.equals(u.getRole())) {
				total++;
			}
		}
		return total;
	}
	
	public int getElitesCount(){
		int total = 0;
		for (UnitDescription u : units) {
			if(UnitRole.ELITES.equals(u.getRole())) {
				total++;
			}
		}
		return total;
	}
	
	public int getTroopsCount(){
		int total = 0;
		for (UnitDescription u : units) {
			if(UnitRole.TROOPS.equals(u.getRole())) {
				total++;
			}
		}
		return total;
	}
	
	public int getFastAttackCount(){
		int total = 0;
		for (UnitDescription u : units) {
			if(UnitRole.FAST_ATTACK.equals(u.getRole())) {
				total++;
			}
		}
		return total;
	}
	
	public int getHeavySupportCount(){
		int total = 0;
		for (UnitDescription u : units) {
			if(UnitRole.HEAVY_SUPPORT.equals(u.getRole())) {
				total++;
			}
		}
		return total;
	}
	
	public int getRoleCount(UnitRole role){
		int total = 0;
		for (UnitDescription u : units) {
			if(u.getRole().equals(role)) {
				total++;
			}
		}
		return total;
	}
	
}
