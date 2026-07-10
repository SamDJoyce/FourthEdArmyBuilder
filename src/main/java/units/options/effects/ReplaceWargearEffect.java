package units.options.effects;

import java.util.List;

import units.models.ModelDescription;
import units.wargear.WargearDescription;

public class ReplaceWargearEffect implements Effect {

	private List<WargearDescription> oldGear;
	private List<WargearDescription> newGear;
	private ModelDescription model;
	
	public ReplaceWargearEffect(
			List<WargearDescription> newGear,
			List<WargearDescription> oldGear,
			ModelDescription  model) {
		this.newGear = newGear;
		this.oldGear = oldGear;
		this.model = model;
	}
	
	
	@Override
	public void doEffect() {
		for (WargearDescription g : oldGear) {
			model.removeGear(g);
		}
		for (WargearDescription g : newGear) {
			model.addGear(g);
		}
	}

	@Override
	public void undoEffect() {
		for (WargearDescription g : newGear) {
			model.removeGear(g);
		}
		for (WargearDescription g : oldGear) {
			model.addGear(g);
		}

	}

}
