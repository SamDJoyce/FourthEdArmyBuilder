package units;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import units.models.ModelDescription;
import units.options.OptionGroup;

public class UnitDescription {
	private static Set<String> usedIds = new HashSet<>();
	
	// Fields
	private String   id;
	private String   name;
	private int      minSize;
	private int      maxSize;
	private UnitRole role;
	private Set<UnitType> types;
	private List<OptionGroup> options;
	private Map<ModelDescription, Integer> models;
	
	// Constructor
	private UnitDescription() {};
	
	// Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public void setTypes(Set<UnitType> types) {
		this.types = types;
	}
	
	private Set<UnitType> getTypesFromModels() {
		if (models == null || models.isEmpty()) {
			return null;
		}
		Set<UnitType> types = new HashSet<UnitType>();
		// Pull each model
		for (ModelDescription m: models.keySet()) {
			// Pull each model's types and add them to the set
			for(UnitType t: m.getTypes()) {
				types.add(t);
			}
		}
		return types;
	}

	public UnitRole getRole() {
		return role;
	}

	public void setRole(UnitRole role) {
		this.role = role;
	}
	
// TODO Update this to use points from gear and other options
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

	public void setMinSize(int minSize) {
		this.minSize = minSize;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
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

	public void setOptions(List<OptionGroup> options) {
		this.options = options;
	}
	
	public Boolean addOption(OptionGroup option) {
		if (!options.contains(option)) {
			return options.add(option);
		}
		return false;
	}
	
	public Boolean removeOption(OptionGroup option) {
			return options.remove(option);
	}

	public Map<ModelDescription, Integer> getModels() {
		return models;
	}

	public void setModels(Map<ModelDescription, Integer> models) {
		this.models = models;
	}
	
	public int addModel(ModelDescription model) throws Exception {
		if (!canAddModel()) {
			throw new Exception();
		}
		// Add model if not present
		if (!containsModel(model)) {
			models.put(model, 1);
			return models.get(model);
		}
		// Increment model count if present
		int modelCount = models.get(model);
		this.models.replace(model, modelCount + 1);
		this.types = getTypesFromModels();
		return models.get(model);
	}
	
	public int removeModel(ModelDescription model) throws Exception {
		if (!canRemoveModel()) {
			throw new Exception();
		}
		
		// Model not present
		if (!containsModel(model)) {
			return 0;
		}
		// Remove last model
		if (models.get(model) == 1) {
			models.remove(model);
			this.types = getTypesFromModels();
			return 0;
		}
		// Decrement model count
		int modelCount = models.get(model);
		models.replace(model, modelCount-1);
		this.types = getTypesFromModels();
		return models.get(model);
	}
	
	public Boolean containsModel(ModelDescription model) {
		return models.containsKey(model);
	}

	// Builder
	public static class Builder {
		private String id;
		private String name;
		private UnitRole role;
		private int minSize;
		private int maxSize;
		private List<OptionGroup> options;
		private Map<ModelDescription, Integer> models;
		
		public Builder setId(String id) {
			this.id = id;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setRole(UnitRole role) {
			this.role = role;
			return this;
		}


		public Builder setMinSize(int minSize) {
			this.minSize = minSize;
			return this;
		}

		public Builder setMaxSize(int maxSize) {
			this.maxSize = maxSize;
			return this;
		}
		
		public Builder setOptions(List<OptionGroup> options) {
			this.options = options;
			return this;
		}
		
		public Builder setModels(Map<ModelDescription, Integer> models) {
			this.models = models;
			return this;
		}
		
		public UnitDescription build() {
			UnitDescription u = new UnitDescription();
			
			// Check if an ID is assigned
			if (this.id == null 
			 || this.id.isEmpty()) {
				// Generate an ID that has not been used
				// and add it to the list of usedIDs
				do {
					this.id = UUID.randomUUID().toString();
				} while(usedIds.contains(this.id));
				usedIds.add(this.id);
			}
			u.id = this.id;
			u.name = this.name;
			u.role = this.role;
			//u.points = this.basePoints;
			u.minSize = this.minSize;
			u.maxSize = this.maxSize;
			u.options = this.options;
			u.models = this.models;
			u.types = u.getTypesFromModels();
			
			return u;
		}
		
	}

}
