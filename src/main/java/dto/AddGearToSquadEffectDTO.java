package dto;

public class AddGearToSquadEffectDTO extends EffectDTO {
	private String name;
	private String gearName;
	private int pointsPerModel;
	
	public AddGearToSquadEffectDTO() {};
	
	public AddGearToSquadEffectDTO(
			String name,
			String gearName,
			int pointsPerModel) {
		super("add_gear_to_squad");
		this.name = name;
		this.gearName = gearName;
		this.pointsPerModel = pointsPerModel;
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
	}

	public int getPointsPerModel() {
		return pointsPerModel;
	}

	public void setPointsPerModel(int pointsPerModel) {
		this.pointsPerModel = pointsPerModel;
	};
}
