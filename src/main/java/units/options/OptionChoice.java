package units.options;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import roster.RosterResult;
import units.options.effects.Effect;
import units.options.requirements.Requirement;
import units.options.requirements.RequirementResult;

public class OptionChoice {
	
	// Fields
	private final String 	name;
	private final int		points;
	private OptionGroup     parentGroup;
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
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}

	public OptionGroup getParentGroup() {
		return parentGroup;
	}

	public void setParentGroup(OptionGroup parentGroup) {
		this.parentGroup = parentGroup;
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
	
	public String toString() {
		return name;
	}
	
	public RequirementResult checkRequirements(SelectionContext context) {
	    for (Requirement r : requirements) {

	        RequirementResult result =
	            r.isMet(context);

	        if (!result.isValid()) {
	            return result;
	        }
	    }
	    return RequirementResult.success("valid");
	}
	
	public RosterResult validate(SelectionContext context) {
		RosterResult result = RosterResult.create();
		
		for (Requirement req : requirements) {
			RosterResult r = req.validate(context);
			if (r.hasIssues()) {
				result.addIssues(r.getIssues());
			}
		}
		
		return result;
	}
	
	// Apply all effects from this option
	public SelectedOption select(SelectionContext context) {

	    for (Effect effect : getEffects()) {
	        effect.apply(context);
	    }

	    return SelectedOption.fromChoice(this);
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
