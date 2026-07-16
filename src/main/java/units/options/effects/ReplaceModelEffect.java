package units.options.effects;

import units.instances.ModelInstance;
import units.instances.UnitInstance;

public class ReplaceModelEffect implements Effect {

	private final ModelInstance newModel;
	private final ModelInstance oldModel;
	
	public ReplaceModelEffect(
			ModelInstance newModel, 
			ModelInstance oldModel) {
		this.newModel = newModel;
		this.oldModel = oldModel;
	}
	
	@Override
	public void apply(EffectContext context) {
		UnitInstance unit = context.getUnit();
		for (ModelInstance m : unit.getModels()) {
			if (m.equals(oldModel)) {
				unit.removeModel(oldModel);
				unit.addModel(newModel);
			}
		}

	}

	@Override
	public void remove(EffectContext context) {
		UnitInstance unit = context.getUnit();
		for (ModelInstance m : unit.getModels()) {
			if (m.equals(oldModel)) {
				unit.removeModel(newModel);
				unit.addModel(oldModel);
			}
		}
	}

}
