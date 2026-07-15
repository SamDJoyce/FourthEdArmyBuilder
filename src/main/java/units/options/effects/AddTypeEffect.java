package units.options.effects;

import units.UnitType;
import units.instances.ModelInstance;

public class AddTypeEffect implements Effect {

	private ModelInstance model;
	private UnitType newType;
	
	public AddTypeEffect(ModelInstance model, UnitType newType) {
		this.model = model;
		this.newType = newType;
	}
	
	@Override
	public void doEffect() {
		model.addType(newType);
	}

	@Override
	public void undoEffect() {
		model.removeType(newType);
	}
}
