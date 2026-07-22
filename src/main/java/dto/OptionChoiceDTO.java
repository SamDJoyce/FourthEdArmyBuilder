package dto;

import java.util.Set;


public class OptionChoiceDTO {
	private final String 	name;
	private final int		points;
	private Set<String> requirementNames;
	private Set<String> effectNames;
	
	public OptionChoiceDTO(
			String name, 
			int points, 
			Set<String> requirementNames,
			Set<String> effectNames) {
		super();
		this.name = name;
		this.points = points;
		this.requirementNames = requirementNames;
		this.effectNames = effectNames;
	}

	public Set<String> getRequirementNames() {
		return requirementNames;
	}

	public void setRequirementNames(Set<String> requirementNames) {
		this.requirementNames = requirementNames;
	}

	public Set<String> getEffectNames() {
		return effectNames;
	}

	public void setEffectNames(Set<String> effectNames) {
		this.effectNames = effectNames;
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}
	
	
}
