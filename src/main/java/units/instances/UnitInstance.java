package units.instances;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import roster.Roster;
import roster.RosterResult;
import units.ModelFactory;
import units.UnitRole;
import units.UnitType;
import units.descriptions.UnitDescription;
import units.descriptions.models.ModelDescription;
import units.descriptions.wargear.WargearDescription;
import units.options.OptionChoice;
import units.options.OptionGroup;
import units.options.OptionOwner;
import units.options.SelectedOption;
import units.options.SelectionContext;
import units.options.requirements.RequirementResult;

public class UnitInstance implements OptionOwner{
	// Fields
	private final String   id;
	private final UnitDescription description;
	private final UnitValidator validator;
	private Set<UnitType> types;
	private List<ModelInstance> models;
	private Set<SelectedOption> selectedOptions;
	private Roster parentRoster;
	
	public UnitInstance(UnitDescription description) {
		this.id = UUID.randomUUID().toString();
		this.description = description;
		validator = UnitValidator.create();
		this.models = ModelFactory.getInstances(description.getModels());
		setParentUnit(models);
		this.selectedOptions = new HashSet<>();
		this.types = new HashSet<>();
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return description.getName();
	}
	
	public UnitDescription getDescription() {
		return this.description;
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
	
	public boolean sizeIsValid() {
		return getCurrentSize() <= getMaxSize()
			&& getCurrentSize() >= getMinSize();
	}
	
	public boolean canAddModel() {
		return getCurrentSize() + 1 <= getMaxSize();
	}
	
	public boolean canRemoveModel() {
		return getCurrentSize() - 1 >= getMinSize();
	}

	public List<ModelInstance> getModels() {
		return Collections.unmodifiableList(models);
	}
	
	public boolean containsModel(ModelInstance model) {
		return models.contains(model);
	}
	
	public RosterResult addModel(ModelInstance model) {
		RosterResult result = RosterResult.create();
		if (!canAddModel()) {
			result.addIssue("Cannot add another model to this unit.");
		}
		// Add model if not present
		if (!containsModel(model)) {
			models.add(model);
		}
		return result;
	}
	
	public RosterResult removeModel(ModelInstance model){
		RosterResult result = RosterResult.create();
		if (!canRemoveModel()) {
			result.addIssue("Cannot remove model");
		}
		return result;
	}
	
	public void addGearToEachModel(WargearDescription gear) {
		for (ModelInstance m : models) {
			m.addGear(gear);
		}
	}
	
	public void removeGearFromEachModel(WargearDescription gear) {
		for (ModelInstance m : models) {
			m.removeGear(gear);
		}
	}
	
	public boolean addType(UnitType type){
		return types.add(type);
	}
	
	public boolean removeType(UnitType type) {
		return types.remove(type);
	}
	
	public Set<UnitType> getTypes(){
		Set<UnitType> allTypes = new HashSet<>(this.types);
		allTypes.addAll(getTypesFromModels());
		return Collections.unmodifiableSet(allTypes);
	}
	
	public Set<UnitType> getTypesFromUnit(){
		return Collections.unmodifiableSet(types);
	}

	public Set<UnitType> getTypesFromModels() {
		Set<UnitType> types = new HashSet<UnitType>();
		
		if (models == null || models.isEmpty()) {
			return Collections.unmodifiableSet(types);
		}
		// Pull each model
		for (ModelInstance m: models) {
			// Pull each model's types and add them to the set
			for(UnitType t: m.getTypes()) {
				types.add(t);
			}
		}
		return Collections.unmodifiableSet(types);
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
	
	@Override
	public Set<SelectedOption> getSelectedOptions(){
		return Collections.unmodifiableSet(selectedOptions);
	}
	
	@Override
	public RequirementResult addSelection(OptionChoice choice) {
		SelectionContext context = SelectionContext.forUnit(this, choice);
		RequirementResult result = choice.checkRequirements(context);
		
		if (result.isValid()) {
			selectedOptions.add(choice.select(context));
		}
		return result;
	}
	
	@Override
	public void removeSelection(OptionChoice choice) {
		Iterator<SelectedOption> iterator = selectedOptions.iterator();

		while (iterator.hasNext()) {

		    SelectedOption selected = iterator.next();
		    SelectionContext context = SelectionContext.forUnit(this, choice);
		    if (selected.getChoice().equals(choice)) {
		        selected.unselect(context);
		        iterator.remove();
		        return;
		    }
		}
	}
	
	public boolean hasSelection(OptionChoice choice) {
		for(SelectedOption o : selectedOptions) {
			if (o.getChoice().equals(choice)) {
				return true;
			}
		}
		return false;
	}
	
	public Set<OptionGroup> getOptions() {
		return this.description.getOptions();
	}
	
	public void setParentRoster(Roster roster) {
		this.parentRoster = roster;
	}
	
	public Roster getParentRoster() {
		return this.parentRoster;
	}
	
	private void setParentUnit(List<ModelInstance> models) {
		for (ModelInstance model : models) {
		    model.setParentUnit(this);
		}
	}
	
	@Override
	public boolean isUnit() {
		return true;
	}
	
	@Override
	public boolean isModel() {
		return false;
	}
	
	@Override
	public String toString() {
		String unit = String.format("Unit Name: %s\n",getName());
		
		for (ModelInstance i : models) {
			unit += i.toString() + "\n";
		}
		
		return unit;
	}
	
	public RosterResult validate() {
		return validator.validate(this);
	}

}
