package units.options;

public class SelectedOption {
	private final OptionChoice choice;
	private int numSelected;
	private OptionOwner owner;
	
	public SelectedOption(
			OptionChoice choice,
			OptionOwner owner,
			int numSelected) {
		this.choice = choice;
		this.owner = owner;
		this.numSelected = numSelected;
	}
	
	public SelectedOption(
			OptionChoice choice,
			OptionOwner owner) {
		this.choice = choice;
		this.owner = owner;
		this.numSelected = 1;
	}
	
	public OptionChoice getChoice() {
		return choice;
	}
	
	public int getNumSelected() {
		return numSelected;
	}
	
	public OptionOwner getOwner() {
		return owner;
	}
	
	public void increaseSelected() {
		numSelected++;
	}
	
	public void decreaseSelected() {
		numSelected--;
	}
	
	public static SelectedOption fromChoice(
			OptionChoice choice,
			OptionOwner owner) {
		return new SelectedOption(choice,owner);
	}
	
	public static SelectedOption fromChoice(
			OptionChoice choice,
			OptionOwner owner,
			int count) {
		return new SelectedOption(choice,owner,count);
	}
}
