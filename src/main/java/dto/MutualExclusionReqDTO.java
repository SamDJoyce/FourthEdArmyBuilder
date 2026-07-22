package dto;

import java.util.List;

public class MutualExclusionReqDTO extends RequirementDTO {

	private String name;
	private List<String> excludedChoiceNames;
	
	public MutualExclusionReqDTO(String name, List<String> excludedChoiceNames) {
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

	public List<String> getExcluded() {
		return excludedChoiceNames;
	}

	public void setExcluded(List<String> excluded) {
		this.excludedChoiceNames = excluded;
	}

}
