package units.options.effects;

import dto.EffectDTO;
import units.descriptions.models.ModelDescription;
import units.descriptions.wargear.WargearDescription;
import units.instances.ModelInstance;

public class EffectFactory {
	
	public static Effect fromDTO(EffectDTO dto) {
		switch(dto.getType()) {
		case "add_model": 
			return null; // addModel()
		case "replace_model": 
			return null; // replaceModel()	
		case "add_wargear":
			return null; // addWargear()
		case "replace_wargear":
			return null; // replaceWargear()
		case "modify_stat":
			return null; // modifyStat()
		case "change_model_name":
			return null; // changeModelName()
		}
		return null;
	}
	
	/**
	 * Construct an AddModelEffect object
	 */
	public static AddModelEffect addModel(ModelDescription model) {
		return new AddModelEffect(model);
	}
	/**
	 * Construct a ReplaceModelEffect object
	 */
	public static ReplaceModelEffect replaceModel(
			ModelInstance newModel, 
			ModelInstance oldModel) {
		return new ReplaceModelEffect(newModel, oldModel);
	}
	
	/**
	 * Construct a AddWargearEffectDTO object
	 */
	public static AddWargearEffect addWargear(WargearDescription gear) {
		return new AddWargearEffect(gear);
	}
	/**
	 * Construct a ReplaceWargearEffect object
	 */
	public static ReplaceWargearEffect replaceWargear(
			WargearDescription remove,
			WargearDescription add) {
		return new ReplaceWargearEffect(remove,add);
	}
	/**
	 * Construct a ModifyStatEffect object
	 * @return 
	 */
	public static ModifyStatEffect modifyStat(String stat, int modifier) {
		return new ModifyStatEffect(stat,modifier);
	}
	
	public static ChangeModelNameEffect changeModelName(String newName) {
		return new ChangeModelNameEffect(newName);
	}
	

}
