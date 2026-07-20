package units.options.effects;

import units.descriptions.models.ModelDescription;
import units.descriptions.wargear.WargearDescription;
import units.instances.ModelInstance;

public class EffectFactory {
	
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
	 * Construct a AddWargearEffect object
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
