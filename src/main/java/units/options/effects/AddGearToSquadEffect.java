package units.options.effects;

import units.descriptions.wargear.WargearDescription;
import units.instances.ModelInstance;
import units.instances.UnitInstance;
import units.options.SelectionContext;

public class AddGearToSquadEffect implements Effect {

	private final String name;
	private WargearDescription gear;
	
	public AddGearToSquadEffect(String name) {
		this.name = name;
	};
	public AddGearToSquadEffect(String name, WargearDescription gear) {
		this.name = name;
		this.gear = gear;
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
	@Override
	public void apply(SelectionContext context) {
		UnitInstance unit = context.getUnit();
		unit.addGearToEachModel(gear);
	}

	@Override
	public void remove(SelectionContext context) {
		UnitInstance unit = context.getUnit();
		unit.removeGearFromEachModel(gear);
	}

}
