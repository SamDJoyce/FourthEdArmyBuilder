package dto;

public class AddModelTypeEffectDTO extends EffectDTO{
	String name;
	String typeName;
	
	public AddModelTypeEffectDTO() {
		super("add_model_type");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
}
