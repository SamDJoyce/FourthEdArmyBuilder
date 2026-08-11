package dto;

public class MustStartWithGearReqDTO extends RequirementDTO {
	
	private String name;
	private String requiredGearName;
	
	public MustStartWithGearReqDTO(String name, String requiredGearName) {
		super("must_start_with_gear");
		this.name = name;
		this.requiredGearName = requiredGearName;
	}
	public MustStartWithGearReqDTO() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRequiredGearName() {
		return requiredGearName;
	}
	public void setRequiredGearName(String requiredGearName) {
		this.requiredGearName = requiredGearName;
	}
	
}
