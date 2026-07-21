package dto;

public abstract class StatLineDTO {
	private String type;
	
	public StatLineDTO(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
