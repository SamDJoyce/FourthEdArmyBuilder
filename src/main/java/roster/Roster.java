package roster;

import java.util.List;

import forceOrg.ForceOrgChart;
import units.UnitRole;
import units.instances.UnitInstance;

public class Roster {

	private ForceOrgChart chart;
	private List<UnitInstance> units;
	private int pointsLimit;
	private RosterValidator validator;
	
	public Roster(
			ForceOrgChart chart,
			List<UnitInstance> units,
			int pointsLimit
			) {
		this.chart = chart;
		this.units = units;
		setParentRoster(units);
		this.pointsLimit = pointsLimit;
		validator = RosterValidator.createFor(this);
	}
	
	public Roster() {}
	
	public ForceOrgChart getChart() {
		return chart;
	}

	public void setChart(ForceOrgChart chart) {
		this.chart = chart;
	}

	public List<UnitInstance> getUnits() {
		return units;
	}

	public void setUnits(List<UnitInstance> units) {
		this.units = units;
	}

	public int getPointsLimit() {
		return pointsLimit;
	}

	public void setPointsLimit(int pointsLimit) {
		this.pointsLimit = pointsLimit;
	}
	
	public int getCurrentPoints() {
		int total = 0;
		for (UnitInstance u : units) {
			total += u.getTotalPoints();
		}
		return total;
	}

	public void setParentRoster(List<UnitInstance> units) {
		for (UnitInstance unit : units) {
			unit.setParentRoster(this);
		}
	}
	
	public int getCountByRole(UnitRole role) {
		int count = 0;
		for (UnitInstance u : units) {
			if (u.getRole().equals(role) ){
				count++;
			}
		}
		return count;
	}
	
	public int getHQCount() {
		int count = 0;
		for (UnitInstance u : units) {
			if (UnitRole.HQ.equals(u.getRole()) ){
				count++;
			}
		}
		return count;
	}
	
	public int getMaxHQ() {
		return chart.getMaxHQ();
	}
	
	public int getMinHQ() {
		return chart.getMinHQ();
	}
	
	public int getElitesCount() {
		int count = 0;
		for (UnitInstance u : units) {
			if (UnitRole.ELITES.equals(u.getRole()) ){
				count++;
			}
		}
		return count;
	}
	
	public int getMaxElites() {
		return chart.getMaxElites();
	}
	
	public int getMinElites() {
		return chart.getMinElites();
	}
	
	public int getTroopsCount() {
		int count = 0;
		for (UnitInstance u : units) {
			if (UnitRole.TROOPS.equals(u.getRole()) ){
				count++;
			}
		}
		return count;
	}
	
	public int getMaxTroops() {
		return chart.getMaxTroops();
	}
	
	public int getMinTroops() {
		return chart.getMinTroops();
	}
	
	public int getFastAttackCount() {
		int count = 0;
		for (UnitInstance u : units) {
			if (UnitRole.FAST_ATTACK.equals(u.getRole()) ){
				count++;
			}
		}
		return count;
	}
	
	public int getMaxFastAttack() {
		return chart.getMaxFastAttack();
	}
	
	public int getMinFastAttack() {
		return chart.getMinFastAttack();
	}
	
	public int getHeavySupportCount() {
		int count = 0;
		for (UnitInstance u : units) {
			if (UnitRole.HEAVY_SUPPORT.equals(u.getRole()) ){
				count++;
			}
		}
		return count;
	}
	
	public int getMaxHeavySupport() {
		return chart.getMaxHeavySupport();
	}
	
	public int getMinHeavySupport() {
		return chart.getMinHeavySupport();
	}
	
	public RosterResult validate() {
		return validator.validate();
		
	}
	
}
