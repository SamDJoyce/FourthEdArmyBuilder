package dto;

public class AddWargearEffectDTO extends EffectDTO {

	private final String name;
	private final String gearName;
	
	public AddWargearEffectDTO(String name, String gearName) {
		super("add_wargear");
		this.name = name;
		this.gearName = gearName;
	}

	public String getName() {
		return name;
	}

	public String getGearName() {
		return gearName;
	}

}
