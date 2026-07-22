package dto;

public class ChangeModelNameEffectDTO extends EffectDTO {

	private final String effectName;
	private final String newName;
	
	public ChangeModelNameEffectDTO(String effectName, String newName) {
		super("change_model_name");
		this.effectName = effectName;
		this.newName = newName;
	}

	public String getEffectName() {
		return effectName;
	}

	public String getNewName() {
		return newName;
	}

}
