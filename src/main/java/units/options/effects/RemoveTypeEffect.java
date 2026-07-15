package units.options.effects;

import units.UnitType;
import units.instances.ModelInstance;

public class RemoveTypeEffect implements Effect {

	private ModelInstance model;
	private UnitType type;
	
	public RemoveTypeEffect(
			ModelInstance model, 
			UnitType type) {
		this.model = model;
		this.type = type;
	}

	@Override
	public void doEffect() {
		model.removeType(type);
	}

	@Override
	public void undoEffect() {
		model.addType(type);
	}

}
