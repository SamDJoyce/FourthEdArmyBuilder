package units.descriptions.models;

import java.util.Set;

import units.UnitType;
import units.descriptions.wargear.WargearDescription;

public class ModelDescription {
	
	private final String 	 name;
	private final int	   	 basePoints;
	private final StatLine stats;
	private final Set<UnitType> types;
	private final Set<WargearDescription> gear;
	
	public ModelDescription(
			String   name, 
			int      basePoints,
			StatLine stats,
			Set<UnitType> types,
			Set<WargearDescription> gear) {
		this.name = name;
		this.basePoints = basePoints;
		this.stats = stats;
		this.types = types;
		this.gear = gear;
	}

	public String getName() {
		return name;
	}

	public int getBasePoints() {
		return basePoints;
	}

	public StatLine getStats() {
		return stats;
	}
	
	public Set<UnitType> getTypes(){
		return types;
	}

	public Set<WargearDescription> getGear(){
		return gear;
	}

}
