package dto;

import java.util.Set;

public class MutualExclusionReqDTO extends RequirementDTO {

	private String name;
	private Set<String> excludedChoiceNames;
	
	public MutualExclusionReqDTO(String name, Set<String> excludedChoiceNames) {
		super("mutual_exclusion");
		this.name = name;
		this.excludedChoiceNames = excludedChoiceNames;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getExcluded() {
		return excludedChoiceNames;
	}

	public void setExcluded(Set<String> excluded) {
		this.excludedChoiceNames = excluded;
	}

}
