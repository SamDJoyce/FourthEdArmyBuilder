package roster;

import java.util.Map;

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

	public Map<String, UnitDescription> getUnits() {
		return units;
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

	public Map<String, WargearDescription> getWargear() {
		return wargear;
	}

	public void setWargear(Map<String, WargearDescription> wargear) {
		this.wargear = wargear;
	}

	public Map<String, StatLine> getStatLines() {
		return statLines;
	}

	public void setStatLines(Map<String, StatLine> statLines) {
		this.statLines = statLines;
	}

	public Map<String, Effect> getEffects() {
		return effects;
	}

	public void setEffects(Map<String, Effect> effects) {
		this.effects = effects;
	}

	public Map<String, Requirement> getRequirements() {
		return requirements;
	}

	public void setRequirements(Map<String, Requirement> requirements) {
		this.requirements = requirements;
	}

	public Map<String, OptionChoice> getChoices() {
		return choices;
	}

	public void setChoices(Map<String, OptionChoice> choices) {
		this.choices = choices;
	}

	public Map<String, OptionGroup> getGroups() {
		return groups;
	}

	public void setGroups(Map<String, OptionGroup> groups) {
		this.groups = groups;
	}

	public Map<String, ModelDescription> getModels() {
		return models;
	}

	public void setModels(Map<String, ModelDescription> models) {
		this.models = models;
	}
	
}
