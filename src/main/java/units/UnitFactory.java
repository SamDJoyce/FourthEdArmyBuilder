package units;

import java.util.ArrayList;
import java.util.List;

import units.descriptions.UnitDescription;
import units.descriptions.models.ModelDescription;
import units.instances.UnitInstance;
import units.options.OptionGroup;

public class UnitFactory {
	
	public static UnitDescription getDescription(			
			String name, 
			int minSize, 
			int maxSize, 
			UnitRole role,
			List<OptionGroup> options,
			List<ModelDescription> models) {
		return new UnitDescription(
				name,
				minSize,
				maxSize,
				role,
				options,
				models);
	}
	
	public static UnitDescription getDescription(			
			String name, 
			int minSize, 
			int maxSize, 
			UnitRole role,
			List<OptionGroup> options) {
		return new UnitDescription(
				name,
				minSize,
				maxSize,
				role,
				options,
				new ArrayList<ModelDescription>());
	}
	
	public static UnitInstance getInstance(UnitDescription unit) {
		return new UnitInstance(unit);
	}
	
	public static List<UnitInstance> getInstances(UnitDescription unit, int count){
		List<UnitInstance> units = new ArrayList<UnitInstance>();
		for (int i = 0; i < count; i++) {
			units.add(getInstance(unit));
		}
		return units;
	}
}
