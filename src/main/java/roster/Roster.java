package roster;

import java.util.List;

import forceOrg.ForceOrgChart;
import units.descriptions.UnitDescription;

public class Roster {

	private ForceOrgChart chart;
	private List<UnitDescription> units;
	private int pointsLimit;
	
	public Roster(
			ForceOrgChart chart,
			List<UnitDescription> units,
			int pointsLimit
			) {
		this.chart = chart;
		this.units = units;
		this.pointsLimit = pointsLimit;
	}
	
	public ForceOrgChart getChart() {
		return chart;
	}

	public void setChart(ForceOrgChart chart) {
		this.chart = chart;
	}

	public List<UnitDescription> getUnits() {
		return units;
	}

	public void setUnits(List<UnitDescription> units) {
		this.units = units;
	}

	public int getPointsLimit() {
		return pointsLimit;
	}

	public void setPointsLimit(int pointsLimit) {
		this.pointsLimit = pointsLimit;
	}

	public int getPointsTotal() {
		int total = 0;
		for (UnitDescription u : units) {
			total += u.getPoints();
		}
		return total;
	}

}
