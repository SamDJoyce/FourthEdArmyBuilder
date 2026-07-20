package units.options.effects;

import units.instances.ModelInstance;
import units.options.SelectionContext;

public class ChangeModelNameEffect implements Effect {

	private final String newName;
	
	public ChangeModelNameEffect(String newName) {
		this.newName = newName;
	}
	
	@Override
	public void apply(SelectionContext context) {
		ModelInstance model = context.getModel();
		model.setName(newName);
	}

	@Override
	public void remove(SelectionContext context) {
		ModelInstance model = context.getModel();
		model.resetName();
	}

}
