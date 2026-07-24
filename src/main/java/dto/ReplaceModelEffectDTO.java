package dto;

public class ReplaceModelEffectDTO extends EffectDTO {
	private String name;
	private String newModelName;
	private String oldModelName;
	
	public ReplaceModelEffectDTO() {}
	
	public ReplaceModelEffectDTO(
			String name, 
			String newModelName, 
			String oldModelName) {
		super("replace_model");
		this.name = name;
		this.newModelName = newModelName;
		this.oldModelName = oldModelName;
	}

	public String getName() {
		return name;
	}

	public String getNewModelName() {
		return newModelName;
	}

	public String getOldModelName() {
		return oldModelName;
	}

}
