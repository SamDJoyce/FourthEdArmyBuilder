package units.instances;

import units.WargearType;
import units.descriptions.wargear.WargearDescription;

public class WargearInstance {

	private final WargearDescription description;
	private Integer points;
	
	public WargearInstance(
			WargearDescription description) {
		this.description = description;
		this.points = description.getPoints();
	}

	
	public String getName() {
		return this.description.getName();
	}
	
	public WargearType getType() {
		return description.getType();
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public WargearDescription getDescription() {
		return this.description;
	}

}
