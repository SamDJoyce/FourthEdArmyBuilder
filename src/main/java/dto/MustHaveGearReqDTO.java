package dto;

public class MustHaveGearReqDTO extends RequirementDTO {

	private String name;
	private String requiredGearName;
	
	public MustHaveGearReqDTO() {}
	
	public MustHaveGearReqDTO(
			String name,
			String requiredGear) {
		super("must_have_gear");
		this.name = name;
		this.requiredGearName = requiredGear;
	}

	public String getName() {
		return name;
	}
	
	public String getRequiredGearName() {
		return this.requiredGearName;
	}
}
