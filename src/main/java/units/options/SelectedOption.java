package units.options;

public class SelectedOption {
	private final OptionChoice choice;
	private int numSelected;
	
	public SelectedOption(OptionChoice choice, int numSelected) {
		this.choice = choice;
		this.numSelected = numSelected;
	}
	
	public OptionChoice getChoice() {
		return choice;
	}
	
	public int getNumSelected() {
		return numSelected;
	}
}
