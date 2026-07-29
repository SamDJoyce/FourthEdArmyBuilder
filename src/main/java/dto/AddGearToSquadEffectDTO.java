package dto;

public class AddGearToSquadEffectDTO extends EffectDTO {
	private String name;
	private String gearName;
	
	public AddGearToSquadEffectDTO() {};
	
	public AddGearToSquadEffectDTO(String name, String gearName) {
		super("add_gear_to_squad");
		this.name = name;
		this.gearName = gearName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGearName() {
		return gearName;
	}

	public void setGearName(String gearName) {
		this.gearName = gearName;
	};
}
