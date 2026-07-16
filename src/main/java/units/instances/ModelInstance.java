package units.instances;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import units.UnitType;
import units.descriptions.models.ModelDescription;
import units.descriptions.models.StatLine;
import units.descriptions.wargear.WargearDescription;
import units.options.SelectedOption;
import units.options.effects.Effect;

public class ModelInstance {
	private final String 	 id;
	private final ModelDescription description;
	//private StatLine currentStats;
	private Set<UnitType> currentTypes;
	private Set<WargearInstance> currentGear;
	private List<SelectedOption> selectedOptions;
	private List<Effect> activeEffects;
	
	public ModelInstance(ModelDescription description){
		this.id = UUID.randomUUID().toString();
		this.description = description;
		this.currentTypes = new HashSet<>(description.getTypes());
		this.currentGear = description.getGear()
			                .stream()
			                .map(WargearInstance::new)
			                .collect(Collectors.toSet());
		this.selectedOptions = new ArrayList<>();
		this.activeEffects = new ArrayList<>();
	}
	
	public ModelDescription getDescription() {
		return description;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return description.getName();
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
	
	public StatLine getCurrentStats() {
		return null; // TODO will apply effects here
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
		return this.currentGear.add(new WargearInstance(gear));
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
	
	public Boolean hasGear(WargearInstance gear) {
		return this.currentGear.contains(gear);
	}
	
	public boolean hasGear(WargearDescription gear) {
		for (WargearInstance i : currentGear) {
			if (i.getDescription().equals(gear)) {
				return true;
			}
		}
		return false;
	}
	
	public List<SelectedOption> getSelectedOptions(){
		return selectedOptions;
	}
	
	public List<Effect> getEffects(){
		return activeEffects;
	}
	
	public static ModelInstance fromDescription(ModelDescription model) {
		return new ModelInstance(model);
	}
}
