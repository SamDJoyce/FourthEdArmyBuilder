package units.options.effects;

import java.util.List;

import units.instances.ModelInstance;
import units.instances.WargearInstance;

public class ReplaceWargearEffect implements Effect {

	private List<WargearInstance> oldGear;
	private List<WargearInstance> newGear;
	private ModelInstance model;
	
	public ReplaceWargearEffect(
			List<WargearInstance> newGear,
			List<WargearInstance> oldGear,
			ModelInstance  model) {
		this.newGear = newGear;
		this.oldGear = oldGear;
		this.model = model;
	}
	
	
	@Override
	public void doEffect() {
		for (WargearInstance g : oldGear) {
			model.removeGear(g);
		}
		for (WargearInstance g : newGear) {
			model.addGear(g);
		}
	}

	@Override
	public void undoEffect() {
		for (WargearInstance g : newGear) {
			model.removeGear(g);
		}
		for (WargearInstance g : oldGear) {
			model.addGear(g);
		}

	}

}
