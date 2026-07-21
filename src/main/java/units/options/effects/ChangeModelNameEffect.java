package units.options.effects;

import units.instances.ModelInstance;
import units.options.SelectionContext;

public class ChangeModelNameEffect implements Effect {

	private final String effectName;
	private final String newName;
	
	public ChangeModelNameEffect(String effectName, String newName) {
		this.effectName = effectName;
		this.newName = newName;
	}
	
	public String getEffectName() {
		return effectName;
	}

	public String getNewName() {
		return newName;
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
