package dto;

public class ReplaceWargearEffectDTO extends EffectDTO {

	private final String name;
	private final String remove;
	private final String add;
	
	public ReplaceWargearEffectDTO(String name, String removeName, String addName) {
		super("replace_wargear");
		this.name = name;
		this.remove = removeName;
		this.add = addName;
	}

	public String getName() {
		return name;
	}

	public String getRemoveName() {
		return remove;
	}

	public String getAddName() {
		return add;
	}

}
