package roster;

import java.util.ArrayList;
import java.util.List;

import forceOrg.ForceOrgChart;
import forceOrg.OrgChartFactory;
import units.UnitRole;
import units.instances.UnitInstance;

public class Roster {

	String name;
	private ForceOrgChart chart;
	private List<UnitInstance> units;
	private int pointsLimit;
	private RosterValidator validator;
	
	public Roster(
			String name,
			ForceOrgChart chart,
			List<UnitInstance> units,
			int pointsLimit
			) {
		this.name = name;
		this.chart = chart;
		this.units = units;
		setParentRoster(units);
		this.pointsLimit = pointsLimit;
		validator = RosterValidator.create();
	}
	
	public Roster() {
		this.name = "New Roster";
		this.chart = OrgChartFactory.createStandard();
		this.units = new ArrayList<>();
		this.pointsLimit = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
		setParentRoster(this.units);
	}
	
	public RosterResult addUnit(UnitInstance unit) {
		units.add(unit);
		unit.setParentRoster(this);
		return validator.validate(this);
	}
	
	public RosterResult removeUnit(UnitInstance unit) {
		units.remove(unit);
		unit.setParentRoster(null);
		return validator.validate(this);
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
	
	public int getMax(UnitRole role) {
		return chart.getMaxRole(role);
	}
	
	public int getMin(UnitRole role) {
		return chart.getMinRole(role);
	}
	
	public RosterResult validate() {
		return validator.validate(this);
		
	}
	
	public static Roster create(
			String name,
			ForceOrgChart chart,
			List<UnitInstance> units,
			int pointsLimit) {
		return new Roster(name, chart, units, pointsLimit);
	}
	
	public static Roster createEmpty() {
		return new Roster();
	}
	
}
