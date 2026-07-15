package units.instances;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import units.UnitRole;
import units.UnitType;
import units.options.OptionGroup;

public class UnitInstance {
	// Fields
	private String   id;
	private String   name;
	private int      minSize;
	private int      maxSize;
	private UnitRole role;
	private Set<UnitType> types;
	private List<OptionGroup> options;
	private Map<ModelInstance, Integer> models;
	
	private UnitInstance() {};
	
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

	public UnitRole getRole() {
		return role;
	}

	public void setRole(UnitRole role) {
		this.role = role;
	}

	public Set<UnitType> getTypes() {
		return types;
	}

	public void setTypes(Set<UnitType> types) {
		this.types = types;
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

	public Map<ModelInstance, Integer> getModels() {
		return models;
	}

	public void setModels(Map<ModelInstance, Integer> models) {
		this.models = models;
	}
	
	public Boolean containsModel(ModelInstance model) {
		return models.containsKey(model);
	}
	
	public int addModel(ModelInstance model) throws Exception {
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
	
	public int removeModel(ModelInstance model) throws Exception {
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

	private Set<UnitType> getTypesFromModels() {
		if (models == null || models.isEmpty()) {
			return null;
		}
		Set<UnitType> types = new HashSet<UnitType>();
		// Pull each model
		for (ModelInstance m: models.keySet()) {
			// Pull each model's types and add them to the set
			for(UnitType t: m.getTypes()) {
				types.add(t);
			}
		}
		return types;
	}
	
	// Builder
		public static class Builder {
			private static Set<String> usedIds = new HashSet<>();
			
			private String id;
			private String name;
			private UnitRole role;
			private int minSize;
			private int maxSize;
			private List<OptionGroup> options;
			private Map<ModelInstance, Integer> models;
			
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
			
			public Builder setModels(Map<ModelInstance, Integer> models) {
				this.models = models;
				return this;
			}
			
			private String generateId() {
				String id;
				do {
					id = UUID.randomUUID().toString();
				} while(usedIds.contains(id));
				usedIds.add(id);
				return id;
			}
			
			private Boolean idNotSet() {
				return this.id == null 
					|| this.id.isEmpty();
			}
			
			public UnitInstance build() {
				UnitInstance u = new UnitInstance();
				
				// Check if an ID is assigned
				if (idNotSet()) {
					this.id = generateId();
				}
				u.id = this.id;
				u.name = this.name;
				u.role = this.role;
				u.minSize = this.minSize;
				u.maxSize = this.maxSize;
				u.options = this.options;
				u.models = this.models;
				u.types = u.getTypesFromModels();
				
				return u;
			}
			
		}
}
