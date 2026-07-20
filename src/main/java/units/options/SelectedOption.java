package units.options;

import units.options.effects.Effect;
import units.options.requirements.Requirement;
import units.options.requirements.RequirementResult;

public class SelectedOption {
	private final OptionChoice choice;
	
	public SelectedOption(
			OptionChoice choice) {
		this.choice = choice;
	}
	
	public static SelectedOption fromChoice(
			OptionChoice choice) {
		return new SelectedOption(choice);
	}
	
	// Validate and pply all effects from this option
	public RequirementResult select(SelectionContext context) {

	    for (Requirement requirement : choice.getRequirements()) {

	        RequirementResult result =
	            requirement.validate(context);

	        if (!result.isValid()) {
	            return result;
	        }
	    }

	    for (Effect effect : choice.getEffects()) {
	        effect.apply(context);
	    }

	    return RequirementResult.success("valid");
	}
    
    // Remove all effects
    public void unselect(SelectionContext context) {
        for (Effect effect : choice.getEffects()) {
            effect.remove(context);
        }
    }
	
	public OptionChoice getChoice() {
		return choice;
	}
	
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof SelectedOption other)) return false;
        return choice.equals(other.choice);
    }

    @Override
    public int hashCode() {
        return choice.hashCode();
    }

}
