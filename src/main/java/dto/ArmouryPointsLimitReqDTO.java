package dto;

public class ArmouryPointsLimitReqDTO extends RequirementDTO {
	private String name;
	private int limit;
	
	public ArmouryPointsLimitReqDTO() {}
	
	ArmouryPointsLimitReqDTO(String name, int limit){
		super("armoury_points_limit");
		this.name = name;
		this.limit = limit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}
