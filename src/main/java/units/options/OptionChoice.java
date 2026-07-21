package units.options;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import units.options.effects.Effect;
import units.options.requirements.Requirement;

public class OptionChoice {
	
	// Fields
	private final String 	name;
	private final int		points;
	private List<Requirement> requirements;
	private List<Effect> effects;
	
	public OptionChoice(
			String name,
			int points,
			List<Requirement> requirements,
			List<Effect> effects
			) {
		this.name = name;
		this.points = points;
		this.requirements = new ArrayList<>(requirements);
		this.effects = new ArrayList<>(effects);
	}
	
	public OptionChoice(
			String name,
			int points) {
		this.name = name;
		this.points = points;
		this.requirements = new ArrayList<>();
		this.effects = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}

	public List<Requirement> getRequirements() {
		return Collections.unmodifiableList(requirements);
	}
	
	public void setRequirements(List<Requirement> requirements) {
		this.requirements = requirements;
	}
	
	public List<Effect> getEffects(){
		return Collections.unmodifiableList(effects);
	}
	
	public void setEffects(List<Effect> effects) {
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
