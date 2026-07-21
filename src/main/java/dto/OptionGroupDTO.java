package dto;

import java.util.List;

public class OptionGroupDTO {
	
	private String name;
	private List<String> choiceNames;
	private List<String> requirementNames;
	private int minSelections;
	private int maxSelections;
	
	public OptionGroupDTO(
			String name, 
			List<String> choiceNames,
			List<String> requirementNames, 
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

	public List<String> getChoiceNames() {
		return choiceNames;
	}

	public void setChoiceNames(List<String> choiceNames) {
		this.choiceNames = choiceNames;
	}

	public List<String> getRequirementNames() {
		return requirementNames;
	}

	public void setRequirementNames(List<String> requirementNames) {
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
