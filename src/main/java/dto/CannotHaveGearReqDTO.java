package dto;

public class CannotHaveGearReqDTO extends RequirementDTO{
	
	String name;
	String blockingGearName;
	
	public CannotHaveGearReqDTO() {};
	
	public CannotHaveGearReqDTO(String name, String blockingGearName){
		super("cannot_have_gear");
		this.name = name;
		this.blockingGearName = blockingGearName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBlockingGearName() {
		return blockingGearName;
	}

	public void setBlockingGearName(String blockingGearName) {
		this.blockingGearName = blockingGearName;
	}
}
