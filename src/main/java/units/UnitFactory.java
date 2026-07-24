package units;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import units.descriptions.UnitDescription;
import units.descriptions.models.ModelDescription;
import units.instances.UnitInstance;
import units.options.OptionGroup;

public class UnitFactory {
	
	private static final Map<String, UnitDescription> registry = new HashMap<>();
	
	public static UnitDescription createDescription(			
			String name, 
			int minSize, 
			int maxSize, 
			UnitRole role,
			Set<OptionGroup> options,
			List<ModelDescription> models) {
		return registry.computeIfAbsent(name,
	            key -> new UnitDescription(
				name,
				minSize,
				maxSize,
				role,
				options,
				models));
	}
	
	public static UnitDescription createDescription(			
			String name, 
			int minSize, 
			int maxSize, 
			UnitRole role,
			Set<OptionGroup> options) {
		return registry.computeIfAbsent(name,
	            key -> new UnitDescription(
				name,
				minSize,
				maxSize,
				role,
				options,
				new ArrayList<ModelDescription>()));
	}
	
	public static UnitInstance getInstance(UnitDescription unit) {
		return new UnitInstance(unit);
	}
	
	public static UnitDescription get(String name) {
		return registry.get(name);
	}
	
	
//	public static List<UnitInstance> getInstances(UnitDescription unit, int count){
//		List<UnitInstance> units = new ArrayList<UnitInstance>();
//		for (int i = 0; i < count; i++) {
//			units.add(getInstance(unit));
//		}
//		return units;
//	}
}
