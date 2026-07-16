package units.instances;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import units.UnitRole;
import units.UnitType;
import units.descriptions.UnitDescription;
import units.descriptions.models.ModelDescription;
import units.options.OptionChoice;
import units.options.SelectedOption;

public class UnitInstance {
	// Fields
	private final String   id;
	private final UnitDescription description;
	private List<ModelInstance> models;
	private List<SelectedOption> selectedOptions;
	
	public UnitInstance(UnitDescription description) {
		this.id = UUID.randomUUID().toString();
		this.description = description;
		this.models = fromDescriptions(description.getModels());
		this.selectedOptions = new ArrayList<>();//fromOptionGroups();
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
	
	public int getModelCount(ModelDescription model) {
	    int count = 0;
	    for (ModelInstance instance: models) {
	        if (instance.getDescription().equals(model)) {
	            count++;
	        }
	    }
	    return count;
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
		return Collections.unmodifiableList(models);
	}
	
	public Boolean containsModel(ModelInstance model) {
		return models.contains(model);
	}
	
	public Boolean addModel(ModelInstance model) {
		if (!canAddModel()) {
			return false;
		}
		// Add model if not present
		if (!containsModel(model)) {
			models.add(model);
			return true;
		}
		return true;
	}
	
	public void removeModel(ModelInstance model) throws Exception {
		if (!canRemoveModel()) {
			return;
		}
		models.remove(model);
	}

	public Set<UnitType> getTypes() {
		Set<UnitType> types = new HashSet<UnitType>();
		
		if (models == null || models.isEmpty()) {
			return types;
		}
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
	
	public List<SelectedOption> getSelectedOptions(){
		return Collections.unmodifiableList(selectedOptions);
	}
	
	public void addSelection(OptionChoice choice) {
		for (SelectedOption selected : selectedOptions) {
			if(selected.getChoice().equals(choice)) {
				selected.increaseSelected();
				return;
			}
		}
		selectedOptions.add(new SelectedOption(choice,1));
	}
	
	public void removeSelection(OptionChoice choice) {
		for (SelectedOption selected : selectedOptions) {
			if(selected.getChoice().equals(choice)) {
				selected.decreaseSelected();
				if (selected.getNumSelected() <= 0) {
					selectedOptions.remove(selected);
				}
				return;
			}
		}
	}
	
	public int getOptionCount(OptionChoice choice) {
		int count = 0;
		for (SelectedOption o : selectedOptions) {
			if (o.getChoice().equals(choice)) {
				count += o.getNumSelected();
			}
		}
		return count;
	}
	
	public boolean hasOption(OptionChoice choice) {
		for(SelectedOption o : selectedOptions) {
			if (o.getChoice().equals(choice)) {
				return true;
			}
		}
		return false;
	}
	
	private List<ModelInstance> fromDescriptions(List<ModelDescription> descriptions) {
		List<ModelInstance> instances = new ArrayList<>();
		
		for (ModelDescription d : descriptions) {
			instances.add(new ModelInstance(d));
		}
		return instances;
	}
}
