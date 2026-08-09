package dto;

public class RemoveWargearEffectDTO extends EffectDTO {
	private String name;
	private String remove;
	
	public RemoveWargearEffectDTO() {}

	public RemoveWargearEffectDTO(String name, String remove) {
		super("remove_wargear");
		this.name = name;
		this.remove = remove;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemove() {
		return remove;
	}

	public void setRemove(String remove) {
		this.remove = remove;
	}
	
	
}
