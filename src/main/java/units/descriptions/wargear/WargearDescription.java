package units.descriptions.wargear;

import units.WargearType;

public class WargearDescription {

	private String name;
	private WargearType type;
	private int points;
	
	public WargearDescription(
			String name,
			WargearType type) {
		this.name = name;
		this.type = type;
		this.points = 0;
	}
	
	public WargearDescription(
			String name,
			WargearType type,
			int points) {
		this.name = name;
		this.type = type;
		this.points = points;
	}
	
	public String getName() {
		return this.name;
	}
	
	public WargearType getType() {
		return type;
	}
	
	public int getPoints() {
		return this.points;
	}
	
}
