package units;

import java.util.HashMap;
import java.util.Map;

import units.descriptions.wargear.WargearDescription;
import units.instances.WargearInstance;

public class WargearFactory {
	private static final Map<String, WargearDescription> registry = new HashMap<>();
	// Descriptions
	public static WargearDescription getDescription(
					String name,
					WargearType type) {
		return  registry.computeIfAbsent(name,
	            key -> new WargearDescription (name,type));
	}
	
	public static WargearDescription getDescription(
			String name,
			String type) {
		return   registry.computeIfAbsent(name,
	            key -> new WargearDescription (name,WargearType.fromString(type)));
	}
	
	public static WargearDescription getDescription(
			String name,
			WargearType type,
			int points) {
	return  registry.computeIfAbsent(name,
            key -> new WargearDescription (name,type,points));
	}
	
	public static WargearDescription getDescription(
			String name,
			String type,
			int points) {
	return  registry.computeIfAbsent(name,
            key -> new WargearDescription (name,WargearType.fromString(type),points));
	}
	
	// Instances
	public static WargearInstance getInstance(WargearDescription gear) {
		return new WargearInstance(gear);
	}
	
	public static WargearDescription get(String name) {
		return registry.get(name);
	}
}
