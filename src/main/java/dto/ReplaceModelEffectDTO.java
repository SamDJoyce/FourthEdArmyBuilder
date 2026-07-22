package dto;

public class ReplaceModelEffectDTO extends EffectDTO {
	private final String name;
	private final String newModelName;
	private final String oldModelName;
	
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
