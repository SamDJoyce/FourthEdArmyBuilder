package units.options.effects;

import units.descriptions.wargear.WargearDescription;
import units.instances.UnitInstance;
import units.options.OptionChoice;
import units.options.SelectionContext;

public class AddGearToSquadEffect implements Effect {

	private final String name;
	private WargearDescription gear;
	private int pointsPerModel;
	private OptionChoice forEachModel;
	
	public AddGearToSquadEffect(String name) {
		this.name = name;
	};
	
	public AddGearToSquadEffect(
			String name,
			WargearDescription gear,
			int pointsPerModel) {
		this.name = name;
		this.gear = gear;
		this.pointsPerModel = pointsPerModel;
		
		
	};
	
	public WargearDescription getGear() {
		return gear;
	}
	public void setGear(WargearDescription gear) {
		this.gear = gear;
	}
	public String getName() {
		return name;
	}
	public void setpointsPerModel(int points) {
		this.pointsPerModel = points;
	}
	public int getPointsPerModel() {
		return pointsPerModel;
	}
	
	@Override
	public void apply(SelectionContext context) {
		System.out.println("applying Add Gear to Squad effect");
		UnitInstance unit = context.getUnit();
		this.forEachModel = OptionChoice.create(name, pointsPerModel);
		unit.addGearToEachModel(gear);
		unit.addSelectionToEachModel(forEachModel);
	}

	@Override
	public void remove(SelectionContext context) {
		UnitInstance unit = context.getUnit();
		this.forEachModel = OptionChoice.create(name, pointsPerModel);
		unit.removeGearFromEachModel(gear);
		unit.removeSelectionFromEachModel(forEachModel);
	}

}
