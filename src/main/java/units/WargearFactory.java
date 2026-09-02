package units;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import units.descriptions.wargear.WargearDescription;
import units.instances.WargearInstance;

public class WargearFactory {
	private static final Map<String, WargearDescription> registry = new HashMap<>();
	// Descriptions
	public static WargearDescription createDescription(
					String name,
					WargearType type) {
		return  registry.computeIfAbsent(name,
	            key -> new WargearDescription (name,type));
	}
	
	public static WargearDescription createDescription(
			String name,
			String type) {
		return   registry.computeIfAbsent(name,
	            key -> new WargearDescription (
	            					name,
	            					WargearType.fromString(type)));
	}
	
	// Instances
	public static WargearInstance getInstance(WargearDescription gear) {
		return new WargearInstance(gear);
	}
	
	public static Set<WargearInstance> getAllInstances(Set<WargearDescription> gear){
		Set<WargearInstance> instances = new HashSet<>();
		for (WargearDescription d : gear) {
			instances.add(WargearFactory.getInstance(d));
		}
		return instances;
	}
	
	public static Set<WargearDescription> getAll(Set<String> names) {
		Set<WargearDescription> gear = new HashSet<>();
		for (String name : names) {
			gear.add(get(name));
		}
		return gear;
	}
	
	public static WargearDescription get(String name) {
		return registry.get(name);
	}
	
	public static Map<String, WargearDescription> getRegistry(){
		return registry;
	}
	
	public static void clearRegistry() {
		registry.clear();
	}
	
}
