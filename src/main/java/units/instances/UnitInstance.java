package units.instances;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import units.UnitRole;
import units.UnitType;
import units.descriptions.UnitDescription;
import units.descriptions.models.ModelDescription;

public class UnitInstance {
	// Fields
	private static Set<String> usedIds = new HashSet<>();
	private final String   id;
	private final UnitDescription description;
	private List<ModelInstance> models;
	
	public UnitInstance(UnitDescription description) {
		this.id = generateId();
		this.description = description;
		this.models = fromDescriptions(description.getModels());
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return description.getName();
	}

	public int getMinSize() {
		return description.getMinSize();
	}

	public int getMaxSize() {
		return description.getMaxSize();
	}

	public UnitRole getRole() {
		return description.getRole();
	}
	
	public int getCurrentSize() {
		return models.size();
	}
	
	public Boolean sizeIsValid() {
		return getCurrentSize() <= getMaxSize()
			&& getCurrentSize() >= getMinSize();
	}
	
	public Boolean canAddModel() {
		return getCurrentSize() + 1 <= getMaxSize();
	}
	
	public Boolean canRemoveModel() {
		return getCurrentSize() - 1 >= getMinSize();
	}

	public List<ModelInstance> getModels() {
		return models;
	}

	public void setModels(List<ModelInstance> models) {
		this.models = models;
	}
	
	public Boolean containsModel(ModelInstance model) {
		return models.contains(model);
	}
	
	public void addModel(ModelInstance model) throws Exception {
		if (!canAddModel()) {
			return;
		}
		// Add model if not present
		if (!containsModel(model)) {
			models.add(model);
			return;
		}
	}
	
	public void removeModel(ModelInstance model) throws Exception {
		if (!canRemoveModel()) {
			return;
		}
		models.remove(model);
	}

	public Set<UnitType> getTypes() {
		if (models == null || models.isEmpty()) {
			return new HashSet<UnitType>();
		}
		Set<UnitType> types = new HashSet<UnitType>();
		// Pull each model
		for (ModelInstance m: models) {
			// Pull each model's types and add them to the set
			for(UnitType t: m.getTypes()) {
				types.add(t);
			}
		}
		return types;
	}
	
	public Boolean isType(UnitType type) {
		return this.getTypes().contains(type);
	}
	
	public int getTotalPoints() {
		int total = 0;
		for (ModelInstance m : models) {
			total += m.getTotalPoints();
		}
		return total;
	}
	
	private List<ModelInstance> fromDescriptions(List<ModelDescription> descriptions) {
		List<ModelInstance> instances = new ArrayList<>();
		
		for (ModelDescription d : descriptions) {
			instances.add(new ModelInstance(d));
		}
		
		return instances;
	}
	
	private String generateId() {
		String id;
		do {
			id = UUID.randomUUID().toString();
		} while(usedIds.contains(id));
		usedIds.add(id);
		return id;
	}
}
