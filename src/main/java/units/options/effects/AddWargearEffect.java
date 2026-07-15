package units.options.effects;

import units.instances.ModelInstance;
import units.instances.WargearInstance;

public class AddWargearEffect implements Effect {

	private WargearInstance gear;
	private ModelInstance model;
	
	public AddWargearEffect(
			WargearInstance gear,
			ModelInstance  model) {
		this.gear = gear;
		this.model = model;
	}
	
	@Override
	public void doEffect() {
		model.addGear(gear);
	}

	@Override
	public void undoEffect() {
		model.removeGear(gear);
	}

}
