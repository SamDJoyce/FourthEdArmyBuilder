package dto;

public class AddWargearEffectDTO extends EffectDTO {

	private String name;
	private String gearName;
	
	public AddWargearEffectDTO() {}
	
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
