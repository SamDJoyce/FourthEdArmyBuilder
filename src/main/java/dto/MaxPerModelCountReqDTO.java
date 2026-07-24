package dto;


public class MaxPerModelCountReqDTO extends RequirementDTO {
	private String name;
	private int rate;
	private String modelName;
	
	public MaxPerModelCountReqDTO() {}
	
	public MaxPerModelCountReqDTO(String type, String name, int rate, String modelName) {
		super(type);
		this.name = name;
		this.rate = rate;
		this.modelName = modelName;
	}

	public String getName() {
		return name;
	}

	public int getRate() {
		return rate;
	}

	public String getModelName() {
		return modelName;
	}
	


}
