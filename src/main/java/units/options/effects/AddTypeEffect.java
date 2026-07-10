package units.options.effects;

import units.UnitType;
import units.models.ModelDescription;

public class AddTypeEffect implements Effect {

	private ModelDescription model;
	private UnitType newType;
	
	public AddTypeEffect(ModelDescription model, UnitType newType) {
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
