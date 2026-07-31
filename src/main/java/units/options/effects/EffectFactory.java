package units.options.effects;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import units.descriptions.models.ModelDescription;
import units.descriptions.wargear.WargearDescription;
import units.instances.ModelInstance;

/**
 * 
 */
public class EffectFactory {
	
	private static final Map<String, Effect> registry = new HashMap<>();
	
	
	/**
	 * Construct an AddModelEffect object
	 */
	public static Effect addModel(String name) {
		return registry.computeIfAbsent(name,
	            key -> new AddModelEffect(name));
	}
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
			String name) {
		return registry.computeIfAbsent(name,
	            key -> new ReplaceModelEffect(name));
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
	public static Effect addWargear(String name) {
		return registry.computeIfAbsent(name,
	            key -> new AddWargearEffect(name));
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
	public static Effect replaceWargear(String name) {
		return registry.computeIfAbsent(name,
	            key -> new ReplaceWargearEffect(name));
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
	 * Construct an AddGearToSquadEffect object
	 * 
	 * @param name
	 * @return
	 */
	public static Effect addGearToSquad(String name) {
		return registry.computeIfAbsent(name,
	            key -> new AddGearToSquadEffect(name));
	}
	
	/**
	 * Construct an AddGearToSquadEffect object
	 * 
	 * @param name
	 * @return
	 */
	public static Effect addGearToSquad(
			String name,
			WargearDescription gear) {
		return registry.computeIfAbsent(name,
	            key -> new AddGearToSquadEffect(name, gear));
	}
	
	/**
	 * Construct a ModifyStatEffect object
	 * @return 
	 */
	public static Effect modifyStat(String name) {
		return registry.computeIfAbsent(name,
	            key -> new ModifyStatEffect(name));
	}
	
	/**
	 * Construct a ModifyStatEffect object
	 * @return 
	 */
	public static Effect modifyStat(String name, String stat, int modifier) {
		return registry.computeIfAbsent(name,
	            key -> new ModifyStatEffect(name, stat,modifier));
	}
	
	public static Effect changeModelName(String effectName) {
		return registry.computeIfAbsent(effectName,
	            key -> new ChangeModelNameEffect(effectName));
	}
	
	public static Effect changeModelName(String effectName, String newName) {
		return registry.computeIfAbsent(effectName,
	            key -> new ChangeModelNameEffect(effectName, newName));
	}
	
	public static Effect get(String name) {
		return registry.get(name);
	}
	
	public static Set<Effect> getAll(Set<String> names){
		Set<Effect> effects = new HashSet<>();
		for (String n : names) {
			effects.add(get(n));
		}
		return effects;
	}
	
	public static Map<String, Effect> getRegistry(){
		return registry;
	}

}
