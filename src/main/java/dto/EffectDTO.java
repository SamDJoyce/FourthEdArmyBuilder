package dto;

public abstract class EffectDTO {
	private String type;
	
	public EffectDTO(String type) {
		this.type = type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
