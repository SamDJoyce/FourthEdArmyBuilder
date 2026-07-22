package dto;

public class AddModelEffectDTO extends EffectDTO {

	private final String name;
	private final String modelName;
	
	public AddModelEffectDTO(String name, String modelName) {
		super("add_model");
		this.name = name;
		this.modelName = modelName;
	}

	public String getName() {
		return name;
	}

	public String getModelName() {
		return modelName;
	}

}
