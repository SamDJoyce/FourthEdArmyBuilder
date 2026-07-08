package units;

import java.util.List;
import java.util.Map;
import java.util.Set;

import units.models.ModelDescription;
import units.options.OptionGroup;

public class UnitDescFactory {
	private UnitDescFactory() {}
	
	// Create a UnitDescription with an ID
	public static UnitDescription get(
			String id,
			String name,
			int basePoints,
			int minSize,
			int maxSize,
			UnitRole role,
			Set<UnitType> types,
			List<OptionGroup> options,
			Map<ModelDescription, Integer> models) {
		return new UnitDescription.Builder()
				.setId(id)
				.setName(name)
				.setBasePoints(basePoints)
				.setMinSize(minSize)
				.setMaxSize(maxSize)
				.setRole(role)
				.setOptions(options)
				.setModels(models)
				.build();
	}
	
	// Create a unitDescription and generate an ID
	public static UnitDescription get(
			String name,
			int basePoints,
			int minSize,
			int maxSize,
			UnitRole role,
			Set<UnitType> types,
			List<OptionGroup> options,
			Map<ModelDescription, Integer> models) {
		return new UnitDescription.Builder()
				.setName(name)
				.setBasePoints(basePoints)
				.setMinSize(minSize)
				.setMaxSize(maxSize)
				.setRole(role)
				.setOptions(options)
				.setModels(models)
				.build();
	}
	
}
