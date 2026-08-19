package roster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import units.UnitRole;
import units.WargearType;
import units.descriptions.UnitDescription;
import units.descriptions.models.ModelDescription;
import units.descriptions.models.StatLine;
import units.descriptions.wargear.WargearDescription;
import units.options.OptionChoice;
import units.options.OptionGroup;
import units.options.effects.Effect;
import units.options.requirements.Requirement;

public class Codex {
	private String name;
	private Map<String, WargearDescription> wargear;
	private Map<String, StatLine> statLines;
	private Map<String, Effect> effects;
	private Map<String, Requirement> requirements;
	private Map<String, OptionChoice> choices;
	private Map<String, OptionGroup> groups;
	private Map<String, ModelDescription> models;
	private Map<String, UnitDescription> units;
	
	public Codex( 
			String name, 
			Map<String, WargearDescription> wargear,
			Map<String, StatLine> statLines,
			Map<String, Effect> effects,
			Map<String, Requirement> requirements,
			Map<String, OptionChoice> choices,
			Map<String, OptionGroup> groups,
			Map<String, ModelDescription> models,
			Map<String, UnitDescription> units
			) {
		this.name = name;
		this.wargear = wargear;
		this.statLines = statLines;
		this.effects = effects;
		this.requirements = requirements;
		this.choices = choices;
		this.groups = groups;
		this.models = models;
		this.units = units;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, UnitDescription> getAllUnits() {
		return units;
	}
	
	public List<UnitDescription> getUnitsByRole(UnitRole role) {

		List<UnitDescription> unitsByRole = new ArrayList<>();

	    for (UnitDescription unit : units.values()) {
	        if (unit.getRole().equals(role)) {
	        	unitsByRole.add(unit);
	        }
	    }
	    unitsByRole.sort(Comparator.comparing(
    			UnitDescription::getName,
    			String.CASE_INSENSITIVE_ORDER));
	    return Collections.unmodifiableList(unitsByRole);
	}
	
	public UnitDescription getUnit(String name) {
		return units.get(name);
	}

	public void setUnits(Map<String, UnitDescription> units) {
		this.units = units;
	}
	
	public void addUnit(UnitDescription unit) {
		units.put(unit.getName(),unit);
	}
	
	public void removeUnit(UnitDescription unit) {
		units.remove(unit.getName());
	}

	public Map<String, WargearDescription> getAllWargear() {
		return Collections.unmodifiableMap(wargear);
	}
	
	public WargearDescription getWargear(String name) {
		return wargear.get(name);
	}
	
	public List<WargearDescription> getWargearByType(WargearType type){
		List<WargearDescription> gear = new ArrayList<>();
		for (WargearDescription w : wargear.values()) {
			if (w.getType().equals(type)) {
				gear.add(w);
			}
		}
		gear.sort(Comparator.comparing(
				WargearDescription::getBaseName,
				String.CASE_INSENSITIVE_ORDER));
		return gear;
	}

	public void setWargear(Map<String, WargearDescription> wargear) {
		this.wargear = wargear;
	}

	public Map<String, StatLine> getAllStatLines() {
		return Collections.unmodifiableMap(statLines);
	}
	
	public StatLine getStatline(String name) {
		return statLines.get(name);
	}

	public void setStatLines(Map<String, StatLine> statLines) {
		this.statLines = statLines;
	}

	public Map<String, Effect> getAllEffects() {
		return Collections.unmodifiableMap(effects);
	}
	
	public Effect getEffect(String name) {
		return effects.get(name);
	}

	public void setEffects(Map<String, Effect> effects) {
		this.effects = effects;
	}

	public Map<String, Requirement> getAllRequirements() {
		return Collections.unmodifiableMap(requirements);
	}
	
	public Requirement getRequirement(String name) {
		return requirements.get(name);
	}

	public void setRequirements(Map<String, Requirement> requirements) {
		this.requirements = requirements;
	}

	public Map<String, OptionChoice> getAllChoices() {
		return Collections.unmodifiableMap(choices);
	}
	
	public OptionChoice getChoice(String name) {
		return choices.get(name);
	}

	public void setChoices(Map<String, OptionChoice> choices) {
		this.choices = choices;
	}

	public Map<String, OptionGroup> getAllGroups() {
		return Collections.unmodifiableMap(groups);
	}
	
	public OptionGroup getGroup(String name) {
		return groups.get(name);
	}

	public void setGroups(Map<String, OptionGroup> groups) {
		this.groups = groups;
	}

	public Map<String, ModelDescription> getAllModels() {
		return Collections.unmodifiableMap(models);
	}

	public ModelDescription getModel(String name) {
		return models.get(name);
	}
	
	public void setModels(Map<String, ModelDescription> models) {
		this.models = models;
	}

}
