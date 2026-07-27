package units.options.effects;

import units.instances.ModelInstance;
import units.instances.UnitInstance;
import units.options.SelectionContext;

public class ReplaceModelEffect implements Effect {

	private final String name;
	private ModelInstance newModel;
	private ModelInstance oldModel;
	
	public ReplaceModelEffect(String name) {
		this.name = name;
	}
	
	public ReplaceModelEffect(
			String name,
			ModelInstance newModel, 
			ModelInstance oldModel) {
		this.name = name;
		this.newModel = newModel;
		this.oldModel = oldModel;
	}
	
	public String getName() {
		return name;
	}

	public ModelInstance getNewModel() {
		return newModel;
	}

	public ModelInstance getOldModel() {
		return oldModel;
	}
	
	public void setNewModel(ModelInstance newModel) {
		this.newModel = newModel;
	}
	
	public void setOldModel(ModelInstance oldModel) {
		this.oldModel = oldModel;
	}

	@Override
	public void apply(SelectionContext context) {
		UnitInstance unit = context.getUnit();
		for (ModelInstance m : unit.getModels()) {
			if (m.equals(oldModel)) {
				unit.removeModel(oldModel);
				unit.addModel(newModel);
			}
		}

	}

	@Override
	public void remove(SelectionContext context) {
		UnitInstance unit = context.getUnit();
		for (ModelInstance m : unit.getModels()) {
			if (m.equals(oldModel)) {
				unit.removeModel(newModel);
				unit.addModel(oldModel);
			}
		}
	}

}
