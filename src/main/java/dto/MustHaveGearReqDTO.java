package dto;

public class MustHaveGearReqDTO extends RequirementDTO {

	private String requiredGear;
	
	public MustHaveGearReqDTO(
			String requiredGear) {
		super("must_have_gear");
		this.requiredGear = requiredGear;
	}

	public String getRequiredGear() {
		return this.requiredGear;
	}
}
