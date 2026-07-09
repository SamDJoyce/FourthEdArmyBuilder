package units.options.effects;

import units.models.ModelDescription;
import units.wargear.WargearDescription;

public class AddWargearEffect implements Effect {

	private WargearDescription gear;
	private ModelDescription model;
	
	public AddWargearEffect(
			WargearDescription gear,
			ModelDescription  model) {
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
