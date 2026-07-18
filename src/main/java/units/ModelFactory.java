package units;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import units.descriptions.models.ModelDescription;
import units.descriptions.models.StatLine;
import units.descriptions.wargear.WargearDescription;
import units.instances.ModelInstance;

public class ModelFactory {
	public static ModelDescription getDescription(
				String   name, 
				int      basePoints,
				StatLine stats,
				Set<UnitType> types,
				Set<WargearDescription> defaultGear ){
	return new ModelDescription(
				name,
				basePoints,
				stats,
				types,
				defaultGear);
	}
	
	public static ModelDescription getDescription(
		String   name, 
		int      basePoints,
		StatLine stats,
		Set<UnitType> types){
	return new ModelDescription(
				name,
				basePoints,
				stats,
				types,
				new HashSet<WargearDescription>());
	}
	
	public static ModelInstance getInstance(ModelDescription model) {
		return new ModelInstance(model);
	}
	
	public static List<ModelInstance> getInstances(List<ModelDescription> descriptions) {
		List<ModelInstance> instances = new ArrayList<>();
		
		for (ModelDescription d : descriptions) {
			instances.add(new ModelInstance(d));
		}
		return instances;
	}
	
	public static List<ModelInstance> getInstances(ModelDescription description, int count) {
		List<ModelInstance> instances = new ArrayList<>();
		
		for (int i = 0; i < count ; i++) {
			instances.add(new ModelInstance(description));
		}
		return instances;
	}
}
