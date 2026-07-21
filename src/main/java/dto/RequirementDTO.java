package dto;

public abstract class RequirementDTO {
	private String type;
	
	public RequirementDTO(String type) {
		this.type = type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
