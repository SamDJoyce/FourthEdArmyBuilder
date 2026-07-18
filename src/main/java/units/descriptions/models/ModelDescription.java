package units.descriptions.models;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import units.UnitType;
import units.descriptions.wargear.WargearDescription;

public class ModelDescription {
	
	private final String 	 name;
	private final int	   	 basePoints;
	private final StatLine   stats;
	private final Set<UnitType> types;
	private final Set<WargearDescription> defaultGear;
	
	public ModelDescription(
			String   name, 
			int      basePoints,
			StatLine stats,
			Set<UnitType> types,
			Set<WargearDescription> defaultGear) {
		this.name = name;
		this.basePoints = basePoints;
		this.stats = StatLineFactory.copy(stats);
		this.types = new HashSet<>(types);
		this.defaultGear = new HashSet<>(defaultGear);
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
		return Collections.unmodifiableSet(types);
	}

	public Set<WargearDescription> getGear(){
		return Collections.unmodifiableSet(defaultGear);
	}
	

}
