package units.options;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import units.options.effects.Effect;
import units.options.requirements.Requirement;

public class OptionChoice {
	
	// Fields
	private final String 	name;
	private final int		points;
	private Set<Requirement> requirements;
	private Set<Effect> effects;
	
	public OptionChoice(
			String name,
			int points,
			Set<Requirement> requirements,
			Set<Effect> effects
			) {
		this.name = name;
		this.points = points;
		this.requirements = new HashSet<>(requirements);
		this.effects = new HashSet<>(effects);
	}
	
	public OptionChoice(
			String name,
			int points) {
		this.name = name;
		this.points = points;
		this.requirements = new HashSet<>();
		this.effects = new HashSet<>();
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}

	public Set<Requirement> getRequirements() {
		return Collections.unmodifiableSet(requirements);
	}
	
	public void setRequirements(Set<Requirement> requirements) {
		this.requirements = requirements;
	}
	
	public Set<Effect> getEffects(){
		return Collections.unmodifiableSet(effects);
	}
	
	public void setEffects(Set<Effect> effects) {
		this.effects = effects;
	}
	
	public static OptionChoice get(String name, int points) {
		return new OptionChoice(name, points);
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    if (!(obj instanceof OptionChoice other)) {
	        return false;
	    }

	    return points == other.points
	            && Objects.equals(name, other.name)
	            && Objects.equals(requirements, other.requirements)
	            && Objects.equals(effects, other.effects);
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(name, points, requirements, effects);
	}

}
