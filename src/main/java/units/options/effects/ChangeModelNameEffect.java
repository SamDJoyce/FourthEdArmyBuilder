package units.options.effects;

import units.instances.ModelInstance;
import units.options.SelectionContext;

public class ChangeModelNameEffect implements Effect {

	private final String effectName;
	private String newName;
	
	public ChangeModelNameEffect(String effectName) {
		this.effectName = effectName;
	}
	
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
	
	public void setNewName(String newName) {
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
