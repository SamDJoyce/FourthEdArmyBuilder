package units.instances;

import java.util.UUID;

import units.WargearType;
import units.descriptions.wargear.WargearDescription;

public class WargearInstance {

	private final String id;
	private final WargearDescription description;
	
	public WargearInstance(
			WargearDescription description) {
		this.id = UUID.randomUUID().toString();
		this.description = description;
	}

	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.description.getName();
	}
	
	public String getBaseName() {
		return description.getBaseName();
	}
	
	public WargearType getType() {
		return description.getType();
	}
	
	public int getPoints() {
		return description.getPoints();
	}
	
	public WargearDescription getDescription() {
		return this.description;
	}
	
	public String toString() {
		return description.getName();
	}

}
