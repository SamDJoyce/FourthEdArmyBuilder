package units.wargear;

public class WargearDescription {

	private String name;
	private WargearType type;
	
	public WargearDescription(
			String name,
			WargearType type) {
		this.name = name;
		this.type = type;
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
	
}
