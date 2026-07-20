package roster;

import java.util.List;

import forceOrg.ForceOrgChart;
import units.descriptions.UnitDescription;
import units.instances.UnitInstance;

public class Roster {

	private ForceOrgChart chart;
	private List<UnitInstance> units;
	private int pointsLimit;
	
	public Roster(
			ForceOrgChart chart,
			List<UnitInstance> units,
			int pointsLimit
			) {
		this.chart = chart;
		this.units = units;
		setParentRoster(units);
		this.pointsLimit = pointsLimit;
	}
	
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

	public void setParentRoster(List<UnitInstance> units) {
		for (UnitInstance unit : units) {
			unit.setParentRoster(this);
		}
	}
}
