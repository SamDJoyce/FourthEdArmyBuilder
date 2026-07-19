package units.options;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import units.options.effects.Effect;
import units.options.requirements.Requirement;
import units.options.requirements.RequirementResult;

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
		this.requirements = new ArrayList<>(requirements);
		this.effects = new ArrayList<>(effects);
	}
	
	public OptionChoice(
			String name,
			int points,
			List<Requirement> requirements,
			List<Effect> effects
			) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.points = points;
		this.requirements = new ArrayList<>(requirements);
		this.effects = new ArrayList<>(effects);
	}

	public String getId() {
		return id;
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
	
	public List<Effect> getEffects(){
		return Collections.unmodifiableList(effects);
	}
	
	public void applyEffects(SelectionContext context) {
		for (Effect e : effects) {
			e.apply(context);
		}
	}
	
	public RequirementResult validate(SelectionContext context) {
		RequirementResult result;
		for (Requirement r : requirements) {
			result = r.validate(context);
			
			if (!result.isValid()) {
				return result; 
			}
		}
		return RequirementResult.success(name + " is available.");
	}
}
