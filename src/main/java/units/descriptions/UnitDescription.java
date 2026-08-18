package units.descriptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
	private Set<UnitType> types;
	private Set<OptionGroup> options;
	private List<ModelDescription> models;
	
	// Constructor
	
	public UnitDescription(
			String name, 
			int minSize, 
			int maxSize, 
			UnitRole role) {
		this.name = name;
		this.minSize = minSize;
		this.maxSize = maxSize;
		this.role = role;
	}
	
	public UnitDescription(
			String name, 
			int minSize, 
			int maxSize, 
			UnitRole role, 
			Set<UnitType> types,
			Set<OptionGroup> options,
			List<ModelDescription> models) {
		this.name = name;
		this.minSize = minSize;
		this.maxSize = maxSize;
		this.role = role;
		this.types = new HashSet<>(types);
		this.options = new HashSet<>(options);
		this.models = new ArrayList<>(models);
	}
	
	public UnitDescription(
			String name, 
			int minSize, 
			int maxSize, 
			UnitRole role,
			Set<OptionGroup> options,
			List<ModelDescription> models) {
		this.name = name;
		this.minSize = minSize;
		this.maxSize = maxSize;
		this.role = role;
		this.options = new HashSet<>(options);
		this.models = new ArrayList<>(models);
		this.types = new HashSet<>();
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public Set<UnitType> getTypes() {
		return Collections.unmodifiableSet(this.types);
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

	public int getMinSize() {
		return minSize;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public Set<OptionGroup> getOptions() {
		return Collections.unmodifiableSet(options);
	}

	public List<ModelDescription> getModels() {
		return Collections.unmodifiableList(models);
	}
	
	public Boolean containsModel(ModelDescription model) {
		return models.contains(model);
	}

	public void setTypes(Set<UnitType> types) {
		this.types = types;
	}

	public void setOptions(Set<OptionGroup> options) {
		this.options = options;
	}

	public void setModels(List<ModelDescription> models) {
		this.models = models;
	}
	
	@Override
	public String toString() {
		String unit = new String();
		
		for (ModelDescription m : models) {
			unit += m.toString() + "\n";
		}
		unit += "Unit Options:\n" + options + "\n";
		
		return unit;
	}

}
