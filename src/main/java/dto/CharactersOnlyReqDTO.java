package dto;

public class CharactersOnlyReqDTO extends RequirementDTO {
	private final String name;
	public CharactersOnlyReqDTO(String name) {
		super("characters_only");
		this.name = name;
	}
	public String getName() {
		return name;
	}

}
