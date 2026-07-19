package units.options;

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
	
	public static SelectedOption fromChoice(
			OptionChoice choice) {
		return new SelectedOption(choice);
	}
	
	public static SelectedOption fromChoice(
			OptionChoice choice,
			OptionOwner owner,
			int count) {
		return new SelectedOption(choice,count);
	}
}
