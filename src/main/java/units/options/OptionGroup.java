package units.options;

import java.util.List;

import units.options.requirements.Effect;
import units.options.requirements.Requirement;

public class OptionGroup {
	private String name;
	private int minSelection;
	private int maxSelection;
	private List<OptionChoice> choices;
	private List<Requirement> requirements;
	
	public OptionGroup(
			String name,
			int minSelection,
			int maxSelection,
			List<OptionChoice> choices,
			List<Requirement> requirements,
			List<Effect> effects) {
		this.name = name;
		this.minSelection = minSelection;
		this.maxSelection = maxSelection;
		this.choices = choices;
		this.requirements = requirements;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinSelection() {
		return minSelection;
	}

	public void setMinSelection(int minSelection) {
		this.minSelection = minSelection;
	}

	public int getMaxSelection() {
		return maxSelection;
	}
	

	public void setMaxSelection(int maxSelection) {
		this.maxSelection = maxSelection;
	}

	public List<OptionChoice> getChoices() {
		return choices;
	}

	public void setChoices(List<OptionChoice> choices) {
		this.choices = choices;
	}
	
	public Boolean addChoice(OptionChoice choice) {
		return this.choices.add(choice);
	}
	
	public Boolean removeChoice(OptionChoice choice) {
		return this.choices.remove(choice);
	}

	public List<Requirement> getRequirements() {
		return requirements;
	}
	
	public Boolean addRequirement(Requirement requirement) {
		return this.requirements.add(requirement);
	}

	public Boolean removeRequirement(Requirement requirement) {
		return this.requirements.remove(requirement);
	}
	
	public void setRequirements(List<Requirement> requirements) {
		this.requirements = requirements;
	}
	
}
