package dto;

import java.util.Set;

public class OptionGroupDTO {
	
	private String name;
	private Set<String> choiceNames;
	private Set<String> requirementNames;
	private int minSelections;
	private int maxSelections;
	
	public OptionGroupDTO(
			String name, 
			Set<String> choiceNames,
			Set<String> requirementNames, 
			int minSelections,
			int maxSelections) {
		this.name = name;
		this.choiceNames = choiceNames;
		this.requirementNames = requirementNames;
		this.minSelections = minSelections;
		this.maxSelections = maxSelections;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getChoiceNames() {
		return choiceNames;
	}

	public void setChoiceNames(Set<String> choiceNames) {
		this.choiceNames = choiceNames;
	}

	public Set<String> getRequirementNames() {
		return requirementNames;
	}

	public void setRequirementNames(Set<String> requirementNames) {
		this.requirementNames = requirementNames;
	}

	public int getMinSelections() {
		return minSelections;
	}

	public void setMinSelections(int minSelections) {
		this.minSelections = minSelections;
	}

	public int getMaxSelections() {
		return maxSelections;
	}

	public void setMaxSelections(int maxSelections) {
		this.maxSelections = maxSelections;
	}
	
	
}
