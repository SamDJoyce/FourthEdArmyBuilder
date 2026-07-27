package dto;

public class MustHaveTypeReqDTO extends RequirementDTO {

	private String requiredType;
	private String name;
	
	public MustHaveTypeReqDTO() {}
	
	public MustHaveTypeReqDTO(String name, String requiredType) {
		super("must_have_type");
		this.requiredType = requiredType;
		this.name = name;
	}
	
	public String getRequiredType() {
		return this.requiredType;
	}

	public String getName() {
		return name;
	}
	
}
