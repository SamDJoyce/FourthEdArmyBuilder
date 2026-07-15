package units.instances;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import units.UnitType;
import units.descriptions.models.StatLine;

public class ModelInstance {
	private static Set<String> usedIds = new HashSet<>();
	private String 	 id;
	private String 	 name;
	private int	   	 basePoints;
	private StatLine stats;
	private Set<UnitType> types;
	private Set<WargearInstance> gear;
	
	public ModelInstance(
			String   id,
			String   name, 
			int      basePoints,
			StatLine stats,
			Set<UnitType> types,
			Set<WargearInstance> gear) {
		this.id = id;
		this.name = name;
		this.basePoints = basePoints;
		this.stats = stats;
		this.types = types;
		this.gear = gear;
	}
	
	public ModelInstance(
			String   name, 
			int      basePoints,
			StatLine stats,
			Set<UnitType> types,
			Set<WargearInstance> gear) {
		this.id = generateId();
		this.name = name;
		this.basePoints = basePoints;
		this.stats = stats;
		this.types = types;
		this.gear = gear;
	}
	
	public static Set<String> getUsedIds() {
		return usedIds;
	}

	public static void setUsedIds(Set<String> usedIds) {
		ModelInstance.usedIds = usedIds;
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
	
	public int getGearPoints() {
		int total = 0;
		for (WargearInstance g : gear) {
			total += g.getPoints();
		}
		return total;
	}

	public StatLine getStats() {
		return stats;
	}

	public void setStats(StatLine stats) {
		this.stats = stats;
	}

	public Set<UnitType> getTypes() {
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

	public Set<WargearInstance> getGear() {
		return gear;
	}

	public void setGear(Set<WargearInstance> gear) {
		this.gear = gear;
	}

	public Boolean addGear(WargearInstance gear) {
		return this.gear.add(gear);
	}
	
	public Boolean removeGear(WargearInstance gear) {
		return this.gear.remove(gear);
	}
	
	public Boolean hasGear(WargearInstance gear) {
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
