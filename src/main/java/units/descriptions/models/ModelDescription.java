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
	private final StatLine   stats;
	private final Set<UnitType> types;
	private final Set<WargearDescription> defaultGear;
	private final Set<OptionGroup> options;
	
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
	
	public Set<OptionGroup> getOptions() {
		return this.options;
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
