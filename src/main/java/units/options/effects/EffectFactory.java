package units.options.effects;

import java.util.HashMap;
import java.util.Map;

import units.descriptions.models.ModelDescription;
import units.descriptions.models.StatLine;
import units.descriptions.wargear.WargearDescription;
import units.instances.ModelInstance;

public class EffectFactory {
	
	private static final Map<String, Effect> registry = new HashMap<>();
	
	/**
	 * Construct an AddModelEffect object
	 */
	public static Effect addModel(String name, ModelDescription model) {
		return registry.computeIfAbsent(name,
	            key -> new AddModelEffect(name, model));
	}
	/**
	 * Construct a ReplaceModelEffect object
	 */
	public static Effect replaceModel(
			String name,
			ModelInstance newModel, 
			ModelInstance oldModel) {
		return registry.computeIfAbsent(name,
	            key -> new ReplaceModelEffect(name, newModel, oldModel));
	}
	
	/**
	 * Construct a AddWargearEffectDTO object
	 */
	public static Effect addWargear(String name,WargearDescription gear) {
		return registry.computeIfAbsent(name,
	            key -> new AddWargearEffect(name, gear));
	}
	/**
	 * Construct a ReplaceWargearEffect object
	 */
	public static Effect replaceWargear(
			String name,
			WargearDescription remove,
			WargearDescription add) {
		return registry.computeIfAbsent(name,
	            key -> new ReplaceWargearEffect(name,remove,add));
	}
	/**
	 * Construct a ModifyStatEffect object
	 * @return 
	 */
	public static Effect modifyStat(String name, String stat, int modifier) {
		return registry.computeIfAbsent(name,
	            key -> new ModifyStatEffect(name, stat,modifier));
	}
	
	public static Effect changeModelName(String effectName, String newName) {
		return registry.computeIfAbsent(effectName,
	            key -> new ChangeModelNameEffect(effectName, newName));
	}
	

}
