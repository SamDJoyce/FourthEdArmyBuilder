package dto;

public class MutualExclusionReqDTO extends RequirementDTO {

	private String name;
	private String excludedGroupName;
	
	public MutualExclusionReqDTO() {}
	
	public MutualExclusionReqDTO(String name, String excludedGroupName) {
		super("mutual_exclusion");
		this.name = name;
		this.excludedGroupName = excludedGroupName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExcludedGroupName() {
		return excludedGroupName;
	}

	public void setExcludedGroupName(String excludedGroupName) {
		this.excludedGroupName = excludedGroupName;
	}

}
