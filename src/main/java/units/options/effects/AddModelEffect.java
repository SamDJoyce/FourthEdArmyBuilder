package units.options.effects;

import units.descriptions.models.ModelDescription;
import units.instances.ModelInstance;
import units.instances.UnitInstance;

public class AddModelEffect implements Effect {

	private final ModelDescription model;
	
	public AddModelEffect(ModelDescription model) {
		this.model = model;
	}
	
	@Override
	public void apply(EffectContext context) {
		context.getUnit().addModel(ModelInstance.fromDescription(model));
	}

	@Override
	public void remove(EffectContext context) {
		UnitInstance unit = context.getUnit();
		ModelInstance model = context.getModel();
		
		unit.removeModel(model);
	}

}
