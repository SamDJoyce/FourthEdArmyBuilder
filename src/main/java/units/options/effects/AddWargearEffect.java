package units.options.effects;

import units.descriptions.wargear.WargearDescription;
import units.instances.WargearInstance;

public class AddWargearEffect implements Effect {

	private final WargearDescription gear;
	
	public AddWargearEffect(WargearDescription gear) {
		this.gear = gear;
	}
	
	@Override
	public void apply(EffectContext context) {
		if (!context.hasModel()) {
			return;
		}
		context.getModel().addGear(new WargearInstance(gear));
	}
	
	@Override
	public void remove(EffectContext context) {
		if (!context.hasModel()) {
			return;
		}
		context.getModel().removeGear(gear);
	}

}
