package units.models;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import units.UnitType;
import units.wargear.WargearDescription;

public class ModelDescription {
	private static Set<String> usedIds = new HashSet<>();
	
	private String 	 id;
	private String 	 name;
	private int	   	 basePoints;
	private StatLine stats;
	private Set<UnitType> types;
	private Set<WargearDescription> gear;
	
	public ModelDescription(
			String   id,
			String   name, 
			int      basePoints,
			StatLine stats,
			Set<UnitType> types,
			Set<WargearDescription> gear) {
		this.id = id;
		this.name = name;
		this.basePoints = basePoints;
		this.stats = stats;
		this.stats.setId(this.id);
		this.types = types;
		this.gear = gear;
	}
	
	public ModelDescription(
			String   name, 
			int      basePoints,
			StatLine stats,
			Set<UnitType> types,
			Set<WargearDescription> gear) {
		this.id = generateId();
		this.name = name;
		this.basePoints = basePoints;
		this.stats = stats;
		this.stats.setId(this.id);
		this.types = types;
		this.gear = gear;
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
	
	public Boolean addType(UnitType type) {
		return types.add(type);
	}
	
	public Boolean removeType(UnitType type) {
		return types.remove(type);
	}
	
	public Boolean isType(UnitType type) {
		return types.contains(type);
	}
	
	public Set<WargearDescription> getGear(){
		return gear;
	}
	
	public void setGear(Set<WargearDescription> gear) {
		this.gear = gear;
	}
	
	public Boolean addGear(WargearDescription gear) {
		return this.gear.add(gear);
	}
	
	public Boolean removeGear(WargearDescription gear) {
		return this.gear.remove(gear);
	}
	
	public Boolean hasGear(WargearDescription gear) {
		return this.gear.contains(gear);
	}

	private String generateId() {
		String id;
		do {
			id = UUID.randomUUID().toString();
		} while(usedIds.contains(id));
		usedIds.add(id);
		return id;
	}

}
