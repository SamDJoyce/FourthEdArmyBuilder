package units.options.effects;

import units.UnitType;
import units.models.ModelDescription;

public class RemoveTypeEffect implements Effect {

	private ModelDescription model;
	private UnitType type;
	
	public RemoveTypeEffect(
			ModelDescription model, 
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
