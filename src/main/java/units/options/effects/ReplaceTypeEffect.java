package units.options.effects;

import units.UnitType;
import units.instances.ModelInstance;

public class ReplaceTypeEffect implements Effect {


	private ModelInstance model;
	private UnitType newType;
	private UnitType oldType;
	
	public ReplaceTypeEffect(
			ModelInstance model, 
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
