package units.descriptions;

import java.util.List;
import java.util.Map;
import java.util.Set;

import units.UnitRole;
import units.UnitType;
import units.descriptions.models.ModelDescription;
import units.options.OptionGroup;

public class UnitDescription {

	
	// Fields
	private final String   name;
	private final int      minSize;
	private final int      maxSize;
	private final UnitRole role;
	private final Set<UnitType> types;
	private final List<OptionGroup> options;
	private final Map<ModelDescription, Integer> models;
	
	// Constructor
	public UnitDescription(
			String name, 
			int minSize, 
			int maxSize, 
			UnitRole role, 
			Set<UnitType> types,
			List<OptionGroup> options,
			Map<ModelDescription, Integer> models) {
		this.name = name;
		this.minSize = minSize;
		this.maxSize = maxSize;
		this.role = role;
		this.types = types;
		this.options = options;
		this.models = models;
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public Set<UnitType> getTypes() {
		return types;
	}
	
	public Boolean addType(UnitType type) {
		return types.add(type);
	}

	public Boolean removeType(UnitType type) {
		return types.remove(type);
	}
	
	public Boolean isType(UnitType type) {
		for (UnitType t: types) {
			if (t.isType(type)) {
				return true;
			}
		}
		return false;
	}

	public UnitRole getRole() {
		return role;
	}
	
	public int getPoints() { 
		int points = 0;
		
		for (ModelDescription m : models.keySet()) {
			points += m.getBasePoints() * this.models.get(m);
		}
		
		return points;
	}

	public int getMinSize() {
		return minSize;
	}

	public int getMaxSize() {
		return maxSize;
	}
	
	public int getCurrentSize() {
		int count = 0;
		for (int m: models.values()) {
			count = count + m;
		}
		return count;
	}
	
	public Boolean sizeIsValid() {
		return getCurrentSize() <= maxSize
			&& getCurrentSize() >= minSize;
	}
	
	public Boolean canAddModel() {
		return getCurrentSize() + 1 <= maxSize;
	}
	
	public Boolean canRemoveModel() {
		return getCurrentSize() - 1 >= minSize;
	}

	public List<OptionGroup> getOptions() {
		return options;
	}

	public Map<ModelDescription, Integer> getModels() {
		return models;
	}
	
	public Boolean containsModel(ModelDescription model) {
		return models.containsKey(model);
	}

}
