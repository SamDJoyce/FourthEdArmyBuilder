package units.options.effects;

import units.UnitType;
import units.models.ModelDescription;

public class ReplaceTypeEffect implements Effect {


	private ModelDescription model;
	private UnitType newType;
	private UnitType oldType;
	
	public ReplaceTypeEffect(
			ModelDescription model, 
			UnitType newType, 
			UnitType oldType) {
		this.model   = model;
		this.newType = newType;
		this.oldType = oldType;
	}

	
	@Override
	public void doEffect() {
		model.removeType(oldType);
		model.addType(newType);

	}

	@Override
	public void undoEffect() {
		model.removeType(newType);
		model.addType(oldType);
	}

}
