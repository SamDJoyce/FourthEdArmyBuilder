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
import units.options.OptionChoice;
import units.options.OptionOwner;
import units.options.requirements.RequirementResult;

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
	
	public OptionOwner getOwnerById(String id) {
		UnitInstance unit = getUnitById(id);
		if (unit != null) {
			return unit;
		}
		return getModelById(id);
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
	
	public List<UnitInstance> getUnitsByRole(UnitRole role){
		List<UnitInstance> unitsOfRole = new ArrayList<>();
		for (UnitInstance unit : units) {
			if (unit.getRole().equals(role)) {
				unitsOfRole.add(unit);
			}
		}
		return unitsOfRole;
	}

	public List<UnitInstance> getUnits() {
		return units;
	}

	public ValidationResult setUnits(List<UnitInstance> units) {
		this.units = units;
		setParentRoster(this.units);
		return validate();
	}
	
	public ValidationResult addUnit(UnitDescription unit) {
		ValidationResult result = ValidationResult.create();
		UnitInstance instance = UnitFactory.createInstance(unit);
		if (!units.add(instance)){
			result.addIssue("Unit could not be added to the roster.");
		} else {
			instance.setParentRoster(this);
		}
		result.addIssues(validate().getIssues());
		return result;
	}
	
	public ValidationResult addUnit(UnitInstance unit) {
		ValidationResult result = ValidationResult.create();
		if (!units.add(unit)){
			result.addIssue("Unit could not be added to the roster.");
		} else {
			unit.setParentRoster(this);
		}
		return result;
	}
	
	public ValidationResult removeUnit(UnitInstance unit) {
		units.remove(unit);
		unit.setParentRoster(null);
		return validate();
	}
	
	public ValidationResult addModel(
			UnitInstance unit, 
			ModelInstance model) {
		return unit.addModel(model);
	}
	
	public ValidationResult removeModel(ModelInstance model) {
		UnitInstance unit = model.getParentUnit();
		return unit.removeModel(model);
	}
	
	public RequirementResult selectOption(
			OptionOwner owner, 
			OptionChoice choice) {
		return owner.addSelection(choice);
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
	
	public ValidationResult validate() {
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
