package units.options.effects;

import units.ModelFactory;
import units.descriptions.models.ModelDescription;
import units.instances.ModelInstance;
import units.instances.UnitInstance;
import units.options.SelectionContext;

public class AddModelEffect implements Effect {

	private final String name;
	private ModelDescription model;
	
	public AddModelEffect(String name) {
		this.name = name;
	}
	
	public AddModelEffect(String name, ModelDescription model) {
		this.name = name;
		this.model = model;
	}
	
	public String getName() {
		return name;
	}

	public ModelDescription getModel() {
		return model;
	}
	
	public void setModel(ModelDescription model) {
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
