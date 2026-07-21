package dto;

public class MustHaveTypeReqDTO extends RequirementDTO {

	private String requiredType;
	
	public MustHaveTypeReqDTO(String requiredType) {
		super("must_have_type");
		this.requiredType = requiredType;
	}
	
	public String getRequiredType() {
		return this.requiredType;
	}

}
