package units.options;

import java.util.ArrayList;
import java.util.List;

import units.options.requirements.Requirement;

public class OptionGroup {
	private String name;
	private List<OptionChoice> choices;
	private List<Requirement> requirements;
	private int minSelections;
	private int maxSelections;
	
	public OptionGroup(
			String name,
			List<OptionChoice> choices,
			List<Requirement> requirements,
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

	public List<OptionChoice> getChoices() {
		return choices;
	}

	public List<Requirement> getRequirements() {
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
			List<OptionChoice> choices,
			int minSelections,
			int maxSelections) {
		return new OptionGroup(
					name,
					choices,
					new ArrayList<Requirement>(),
					minSelections,
					maxSelections);
	}
	
	public static OptionGroup get(
			String name,
			List<OptionChoice> choices,
			List<Requirement> requirements,
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
