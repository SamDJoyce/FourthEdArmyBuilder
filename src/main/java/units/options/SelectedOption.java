package units.options;

import roster.ValidationResult;
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
	
	// Validate and apply all effects from this option
	public SelectedOption select(SelectionContext context) {

		return choice.select(context);
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
	
	public ValidationResult validate(SelectionContext context) {
		return choice.validate(context);
	}
	
	public String toString() {
		return choice.getName();
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
