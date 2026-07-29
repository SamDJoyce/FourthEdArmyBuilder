package units.options;

import java.util.HashSet;
import java.util.Set;

import units.options.requirements.Requirement;

public class OptionGroup {
	private String name;
	private Set<OptionChoice> choices;
	private Set<Requirement> requirements;
	private int minSelections;
	private int maxSelections;
	
	public OptionGroup(
			String name,
			int minSelections,
			int maxSelections) {
		this.name = name;
		this.minSelections = minSelections;
		this.maxSelections = maxSelections;
	}
	
	public OptionGroup(
			String name,
			Set<OptionChoice> choices,
			Set<Requirement> requirements,
			int minSelections,
			int maxSelections) {
		this.name 		   = name;
		this.choices 	   = new HashSet<>(choices) ;
		assignAllParents(this.choices);
		this.requirements  = new HashSet<>(requirements);
		this.minSelections = minSelections;
		this.maxSelections = maxSelections;
	}
	


	public String getName() {
		return name;
	}

	public Set<OptionChoice> getChoices() {
		return choices;
	}

	public Set<Requirement> getRequirements() {
		return requirements;
	}
	
	public int getMinSelections() {
		return minSelections;
	}
	
	public int getMaxSelections() {
		return maxSelections;
	}
	
	public void setChoices(Set<OptionChoice> choices) {
		this.choices = choices;
		assignAllParents(this.choices);
	}

	public void setRequirements(Set<Requirement> requirements) {
		this.requirements = requirements;
	}

	public static OptionGroup get(
			String name,
			Set<OptionChoice> choices,
			int minSelections,
			int maxSelections) {
		return new OptionGroup(
					name,
					choices,
					new HashSet<Requirement>(),
					minSelections,
					maxSelections);
	}
	
	public static OptionGroup get(
			String name,
			Set<OptionChoice> choices,
			Set<Requirement> requirements,
			int minSelections,
			int maxSelections) {
		return new OptionGroup(
					name,
					choices,
					requirements,
					minSelections,
					maxSelections);
	}
	
	@Override
	public String toString() {
		String group = "\nGroup: " + name + "\n";
		
		for (OptionChoice c : choices) {
			group += "	Option: " + c.getName() + "\n";
		}
		
		return group;
	}
	
	private void assignAllParents(Set<OptionChoice> choices) {
		for (OptionChoice c : choices) {
			assignParent(c);
		}
	}
	
	private void assignParent(OptionChoice choice) {
		choice.setParentGroup(this);
	}
	
}
