package units.instances;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import roster.RosterResult;
import units.UnitType;
import units.WargearFactory;
import units.descriptions.models.ModelDescription;
import units.descriptions.models.StatLine;
import units.descriptions.wargear.WargearDescription;
import units.options.OptionChoice;
import units.options.OptionOwner;
import units.options.SelectedOption;
import units.options.SelectionContext;
import units.options.requirements.RequirementResult;

public class ModelInstance implements OptionOwner{
	private final String 	 		id;
	private final ModelDescription 	description;
	private final ModelValidator 	validator;
	private Set<UnitType> 		 	currentTypes;
	private Set<WargearInstance> 	currentGear;
	private Set<SelectedOption>  	selectedOptions;
	private UnitInstance 		 	parentUnit;
	private String 				 	customName;
	
	public ModelInstance(ModelDescription description){
		this.id = UUID.randomUUID().toString();
		this.description = description;
		this.validator = ModelValidator.create();
		this.currentTypes = new HashSet<>(description.getTypes());
		this.currentGear = description.getGear()
			                .stream()
			                .map(WargearInstance::new)
			                .collect(Collectors.toSet());
		this.selectedOptions = new HashSet<>();
	}
	
	public ModelDescription getDescription() {
		return description;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return customName == null ? description.getName() : customName;
	}
	
	public void setName(String newName) {
		this.customName = newName;
	}
	
	public void resetName() {
		this.customName = null;
	}

	public int getBasePoints() {
		return description.getBasePoints();
	}
	
	public int getGearPoints() {
		int total = 0;
		for (WargearInstance g : currentGear) {
			total += g.getPoints();
		}
		return total;
	}
	
	public int getTotalPoints() {
		return getBasePoints() + getGearPoints();
	}

	public StatLine getStats() {
		return description.getStats();
	}

	public Set<UnitType> getTypes() {
		return Collections.unmodifiableSet(currentTypes);
	}

	public void setTypes(Set<UnitType> types) {
		this.currentTypes = types;
	}
	
	public Boolean addType(UnitType type) {
		return currentTypes.add(type);
	}
	
	public Boolean removeType(UnitType type) {
		return currentTypes.remove(type);
	}
	
	public Boolean isType(UnitType type) {
		return currentTypes.contains(type);
	}

	public Set<WargearInstance> getGear() {
		return Collections.unmodifiableSet(currentGear);
	}

	public void setGear(Set<WargearInstance> gear) {
		this.currentGear = gear;
	}

	public Boolean addGear(WargearInstance gear) {
		return this.currentGear.add(gear);
	}
	
	public Boolean addGear(WargearDescription gear) {
		if (hasGear(gear)) {
			return false;
		}
		return this.currentGear.add(WargearFactory.getInstance(gear));
	}
	
	public Boolean removeGear(WargearDescription gear) {
		for (WargearInstance i : currentGear) {
			if (i.getDescription().equals(gear)) {
				return currentGear.remove(i);
			}
		}
		return false;
	}
	
	public Boolean removeGear(WargearInstance gear) {
		return this.currentGear.remove(gear);
	}
	
	public Boolean hasGear(WargearDescription gear) {

		for (WargearInstance i : currentGear) {
			if (gear.getBaseName().equalsIgnoreCase(i.getBaseName()) ) {
				return true;
			}
		}
		
		return false;
	}

	
	public UnitInstance getParentUnit() {
		return this.parentUnit;
	}
	
	public void setParentUnit(UnitInstance parentUnit) {
		this.parentUnit = parentUnit;
	}
	
	public boolean hasSelectedOption(OptionChoice choice) {
		for (SelectedOption s : selectedOptions) {
			if (s.getChoice().equals(choice)) {
				return true;
			}
		}
		return false;
	}
	
	public RosterResult validate() {
		return validator.validate(this);
	}
	
	@Override
	public String toString() {
		
		return String.format(
				"Name: %s\nPoints: %d\nTypes: %s\nStats:\n%s\nGear: %s\nOptions: %s\n",
				customName != null ? customName : getName(), 
				getTotalPoints(),
				currentTypes, 
				description.getStats().toString(), 
				currentGear, 
				selectedOptions);

	}
	
	@Override
	public Set<SelectedOption> getSelectedOptions(){
		return selectedOptions;
	}
	
	@Override
	public RequirementResult addSelection(OptionChoice choice) {
		
		SelectionContext  context = SelectionContext.forModel(this,choice);
		RequirementResult result  = choice.checkRequirements(context);
		
		if (result.isValid()) {
			selectedOptions.add(choice.select(context));
		}
		return result;
	}
	
	@Override
	public void removeSelection(OptionChoice choice) {
	    SelectedOption option = findSelection(choice);
	    if (option == null) {
	        return;
	    }
		SelectionContext  context = SelectionContext.forModel(this, option.getChoice());
	    option.unselect(context);
	    selectedOptions.remove(option);
	}
	
	public boolean hasSelection(OptionChoice choice) {
		for(SelectedOption o : selectedOptions) {
			if (o.getChoice().equals(choice)) {
				return true;
			}
		}
		return false;
	}
	
	private SelectedOption findSelection(OptionChoice choice) {
		for (SelectedOption selected : selectedOptions) {
			if (selected.getChoice().equals(choice)) {
				return selected;
			}
		}
		return null;
	}
	
}
