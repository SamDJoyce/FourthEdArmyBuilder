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
			Set<OptionChoice> choices,
			Set<Requirement> requirements,
			int minSelections,
			int maxSelections) {
		this.name = name;
		this.choices = choices;
		this.requirements = requirements;
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
	
}
