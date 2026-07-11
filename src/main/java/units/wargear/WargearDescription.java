package units.wargear;

public class WargearDescription {

	private String name;
	private WargearType type;
	private int points;
	
	public WargearDescription(
			String name,
			WargearType type) {
		this.name = name;
		this.type = type;
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
	
	public void setName(String name) {
		this.name = name;
	}
	
	public WargearType getType() {
		return type;
	}
	
	public void setType(WargearType type) {
		this.type = type;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public int getPoints() {
		return this.points;
	}
	
}
