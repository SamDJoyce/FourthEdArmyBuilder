package dto;

public class MaxSelectionReqDTO extends RequirementDTO {
	private final String name;
	private int maxSelection;
	private String message;


	public MaxSelectionReqDTO(String name, int maxSelection, String message) {
		super("max_selection");
		this.name = name;
		this.maxSelection = maxSelection;
		this.message = message;
	}


	public int getMaxSelection() {
		return maxSelection;
	}


	public void setMaxSelection(int maxSelection) {
		this.maxSelection = maxSelection;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getName() {
		return name;
	}

}
