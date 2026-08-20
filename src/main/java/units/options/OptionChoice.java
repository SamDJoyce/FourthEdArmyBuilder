package units.options;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import roster.ValidationResult;
import units.options.effects.Effect;
import units.options.requirements.Requirement;

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
	
	public ValidationResult checkRequirements(SelectionContext context) {
	    ValidationResult result = ValidationResult.create();
		for (Requirement req : requirements) {
			result.addIssues(req.isMet(context).getIssues());
	    }
	    return result;
	}
	
	public ValidationResult validate(SelectionContext context) {
		ValidationResult result = ValidationResult.create();
		
		for (Requirement req : requirements) {
			ValidationResult r = req.validate(context);
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
