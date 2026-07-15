package units.options.requirements;

import units.UnitType;
import units.descriptions.models.ModelDescription;

public class CharacterOnlyReq implements Requirement {

	private ModelDescription model;

	public CharacterOnlyReq(ModelDescription model) {
		this.model = model;
	}
	
	@Override
	public Boolean isSatisfied() {
		return model.getTypes().contains(UnitType.CHARACTER);
	}

}
