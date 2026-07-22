package dto;

public class MustHaveGearReqDTO extends RequirementDTO {

	private String name;
	private String requiredGear;
	
	public MustHaveGearReqDTO(
			String name,
			String requiredGear) {
		super("must_have_gear");
		this.name = name;
		this.requiredGear = requiredGear;
	}

	public String getName() {
		return name;
	}
	
	public String getRequiredGear() {
		return this.requiredGear;
	}
}
