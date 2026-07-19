package units.options.effects;

import units.descriptions.wargear.WargearDescription;
import units.instances.WargearInstance;
import units.options.SelectionContext;

public class AddWargearEffect implements Effect {

	private final WargearDescription gear;
	
	public AddWargearEffect(WargearDescription gear) {
		this.gear = gear;
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
