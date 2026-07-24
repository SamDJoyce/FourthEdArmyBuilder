package dto;

public class AddModelEffectDTO extends EffectDTO {

	private String name;
	private String modelName;
	
	public AddModelEffectDTO() {}
	
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
