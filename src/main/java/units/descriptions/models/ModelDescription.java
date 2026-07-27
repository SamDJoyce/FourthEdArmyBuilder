package units.descriptions.models;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import units.UnitType;
import units.descriptions.wargear.WargearDescription;
import units.options.OptionGroup;

public class ModelDescription {
	
	private final String 	 name;
	private final int	   	 basePoints;
	private StatLine   stats;
	private Set<UnitType> types;
	private Set<WargearDescription> defaultGear;
	private Set<OptionGroup> options;
	
	public ModelDescription(
			String   name, 
			int      basePoints,
			StatLine stats,
			Set<UnitType> types,
			Set<OptionGroup> options,
			Set<WargearDescription> defaultGear) {
		this.name = name;
		this.basePoints = basePoints;
		this.stats = StatLineFactory.copy(stats);
		this.types = new HashSet<>(types);
		this.options = new HashSet<>(options);
		this.defaultGear = new HashSet<>(defaultGear);
	}
	
	public ModelDescription(String name, int basePoints, Set<UnitType> types) {
		this.name = name;
		this.basePoints = basePoints;
		this.stats = StatLineFactory.copy(stats);
		this.types = types;
		this.options = new HashSet<>();
		this.defaultGear = new HashSet<>();
	}

	public String getName() {
		return name;
	}

	public int getBasePoints() {
		return basePoints;
	}
	
	public void setStats(StatLine stats) {
		this.stats = stats;
	}

	public StatLine getStats() {
		return stats;
	}
	
	public void setTypes (Set<UnitType> types) {
		this.types = types;
	}
	
	public Set<UnitType> getTypes(){
		return Collections.unmodifiableSet(types);
	}

	public void setGear(Set<WargearDescription> gear) {
		this.defaultGear = gear;
	}
	
	public Set<WargearDescription> getGear(){
		return Collections.unmodifiableSet(defaultGear);
	}
	
	public void setOptions(Set<OptionGroup> options) {
		this.options = options;
	}
	
	public Set<OptionGroup> getOptions() {
		return this.options;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Name: %s\nPoints: %d\nTypes: %s\nStats:\n%s\nGear: %s\n",
				name, basePoints,types, stats.toString(), defaultGear);
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (!(obj instanceof ModelDescription other)) return false;

	    return basePoints == other.basePoints
	        && Objects.equals(name, other.name)
	        && Objects.equals(stats, other.stats)
	        && Objects.equals(types, other.types)
	        && Objects.equals(defaultGear, other.defaultGear);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(
	        name,
	        basePoints,
	        stats,
	        types,
	        defaultGear);
	}

}
