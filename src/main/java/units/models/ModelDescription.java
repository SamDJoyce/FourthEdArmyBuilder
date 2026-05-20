package units.models;

import java.util.Set;

import units.UnitType;

public class ModelDescription {
	private String 	 id;
	private String 	 name;
	private int	   	 basePoints;
	private StatLine stats;
	private Set<UnitType> types;
	
	public ModelDescription(String   id,
							String   name, 
							int      basePoints,
							StatLine stats,
							Set<UnitType> types) {
		this.id = id;
		this.name = name;
		this.basePoints = basePoints;
		this.stats = stats;
		this.types = types;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBasePoints() {
		return basePoints;
	}

	public void setBasePoints(int basePoints) {
		this.basePoints = basePoints;
	}

	public StatLine getStats() {
		return stats;
	}

	public void setStats(StatLine stats) {
		this.stats = stats;
	}
	
	public Set<UnitType> getTypes(){
		return types;
	}
	
	public void setTypes(Set<UnitType> types) {
		this.types = types;
	}
}
