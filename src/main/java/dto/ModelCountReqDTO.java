package dto;

public class ModelCountReqDTO extends RequirementDTO {

	private final String name;
    private final String modelName;
    private final int minimum;
    private final int maximum;
    private String message;
    
	public ModelCountReqDTO(
			String type, 
			String name, 
			String modelName, 
			int minimum, 
			int maximum,
			String message) {
		super(type);
		this.name = name;
		this.modelName = modelName;
		this.minimum = minimum;
		this.maximum = maximum;
		this.message = message;
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

	public String getModelName() {
		return modelName;
	}

	public int getMinimum() {
		return minimum;
	}

	public int getMaximum() {
		return maximum;
	}
    

}
