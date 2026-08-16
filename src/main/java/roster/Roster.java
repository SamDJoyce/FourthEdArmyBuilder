package roster;

import java.util.ArrayList;
import java.util.List;

import forceOrg.ForceOrgChart;
import forceOrg.OrgChartFactory;
import units.UnitFactory;
import units.UnitRole;
import units.descriptions.UnitDescription;
import units.instances.ModelInstance;
import units.instances.UnitInstance;

public class Roster {

	String name;
	private ForceOrgChart 		chart;
	private List<UnitInstance> 	units;
	private int 				pointsLimit;
	private RosterValidator 	validator;
	
	public Roster(
			String name,
			ForceOrgChart chart,
			List<UnitInstance> units,
			int pointsLimit
			) {
		this.name  = name;
		this.chart = chart;
		this.units = new ArrayList<>(units);
		setParentRoster(units);
		this.pointsLimit = pointsLimit;
		this.validator   = RosterValidator.create();
	}
	
	public Roster() {
		this.name = "New Roster";
		this.chart = OrgChartFactory.createStandard();
		this.units = new ArrayList<>();
		this.pointsLimit = 0;
		this.validator   = RosterValidator.create();
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
	
	public UnitInstance getUnitById(String id) {
		for (UnitInstance unit : units) {
			if (unit.getId().equals(id)) {
				return unit;
			}
		}
		return null;
	}
	
	public ModelInstance getModelById(String id) {
		for (UnitInstance unit : units) {
			for (ModelInstance model : unit.getModels()) {
				if (model.getId().equals(id)) {
					return model;
				}
			}
		}
		return null;
	}

	public List<UnitInstance> getUnits() {
		return units;
	}

	public RosterResult setUnits(List<UnitInstance> units) {
		this.units = units;
		setParentRoster(this.units);
		return validate();
	}
	
	public String addUnit(UnitDescription unit) {
		UnitInstance instance = UnitFactory.createInstance(unit);
		units.add(instance);
		instance.setParentRoster(this);
		return instance.getId();
	}
	
	public RosterResult removeUnit(String id) {
		
		UnitInstance u = getUnitById(id);
		units.remove(u);
		u.setParentRoster(null);
		return validate();
		
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
