package units.descriptions;

import java.util.Set;

import units.descriptions.wargear.WargearDescription;

public class Faction {
	private String id;
	private String name;
	private Set<UnitDescription> units;
	private Set<WargearDescription> wargear;
	
	public Faction(	String id, 
					String name, 
					Set<UnitDescription> units, 
					Set<WargearDescription> wargear) {
		this.id = id;
		this.name = name;
		this.units = units;
		this.wargear = wargear;
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

	public Set<UnitDescription> getUnits() {
		return units;
	}

	public void setUnits(Set<UnitDescription> units) {
		this.units = units;
	}
	
	public Boolean addUnit(UnitDescription unit) {
		return units.add(unit);
	}
	
	public Boolean removeUnit(UnitDescription unit) {
		return units.remove(unit);
	}

	public Set<WargearDescription> getWargear() {
		return wargear;
	}

	public void setWargear(Set<WargearDescription> wargear) {
		this.wargear = wargear;
	}
	
	public Boolean addWargear(WargearDescription gear) {
		return wargear.add(gear);
	}
	
	public Boolean removeWargear(WargearDescription gear) {
		return wargear.remove(gear);
	}
	
}
