package units.options.effects;

import units.descriptions.wargear.WargearDescription;
import units.instances.WargearInstance;
import units.options.SelectionContext;

public class AddWargearEffect implements Effect {

	private final String name;
	private final WargearDescription gear;
	
	public AddWargearEffect(String name, WargearDescription gear) {
		this.name = name;
		this.gear = gear;
	}
	
	public String getName() {
		return name;
	}

	public WargearDescription getGear() {
		return gear;
	}

	@Override
	public void apply(SelectionContext context) {
		context.getModel().addGear(new WargearInstance(gear));
	}
	
	@Override
	public void remove(SelectionContext context) {
		context.getModel().removeGear(gear);
	}

}
