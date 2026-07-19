package units.options;

import units.options.effects.Effect;
import units.options.requirements.RequirementResult;

public class SelectedOption {
	private final OptionChoice choice;
	private int numSelected;
	
	public SelectedOption(
			OptionChoice choice,
			int numSelected) {
		this.choice = choice;
		this.numSelected = numSelected;
	}
	
	public SelectedOption(
			OptionChoice choice) {
		this.choice = choice;
		this.numSelected = 1;
	}
	
	public static SelectedOption fromChoice(
			OptionChoice choice) {
		return new SelectedOption(choice);
	}
	
	// Apply all effects from this option
	public RequirementResult select(SelectionContext context) {

		RequirementResult result = choice.validate(context);
		
		if (!result.isValid()) {
		return result;
		}
		// Apply effects if valid
		for (Effect effect : choice.getEffects()) {
			effect.apply(context);
		}
		
		return RequirementResult.success("Valid");
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
	
	public int getNumSelected() {
		return numSelected;
	}

	
	public void increaseSelected() {
		numSelected++;
	}
	
	public void decreaseSelected() {
		numSelected--;
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
