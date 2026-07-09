package units.options;

import java.util.List;

import units.options.effects.Effect;
import units.options.requirements.Requirement;

public class OptionChoice {
	
	// Fields
	private String 	id;
	private String 	name;
	private int		points;
	private List<Requirement> requirements;
	private List<Effect> effects;
	
	public OptionChoice(
			String id,
			String name,
			int points,
			List<Requirement> requirements,
			List<Effect> effects
			) {
		this.id = id;
		this.name = name;
		this.points = points;
		this.requirements = requirements;
		this.effects = effects;
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

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public List<Requirement> getRequirements() {
		return requirements;
	}

	public void setRequirements(List<Requirement> requirements) {
		this.requirements = requirements;
	}
	
	public Boolean addRequirement(Requirement r) {
		return this.requirements.add(r);
	}
	
	public Boolean removeRequirement(Requirement r) {
		return this.requirements.remove(r);
	}
	
	public Boolean reqsSatisfied() {
		for (Requirement r : requirements) {
			if (!r.isSatisfied()) {
				return false;
			}
		}
		return true;
	}

	public List<Effect> getEffects() {
		return effects;
	}

	public void setEffects(List<Effect> effects) {
		this.effects = effects;
	}
	
	public Boolean addEffect(Effect e) {
		return this.effects.add(e);
	}
	
	public Boolean removeEffect(Effect e) {
		return this.effects.remove(e);
	}
}
