package units.options.effects;

import units.ModelFactory;
import units.descriptions.models.ModelDescription;
import units.instances.ModelInstance;
import units.instances.UnitInstance;
import units.options.SelectionContext;

public class AddModelEffect implements Effect {

	private final ModelDescription model;
	
	public AddModelEffect(ModelDescription model) {
		this.model = model;
	}
	
	@Override
	public void apply(SelectionContext context) {
		context.getUnit().addModel(ModelFactory.getInstance(model));
	}

	@Override
	public void remove(SelectionContext context) {
		UnitInstance unit = context.getUnit();
		ModelInstance model = context.getModel();
		
		unit.removeModel(model);
	}

}
