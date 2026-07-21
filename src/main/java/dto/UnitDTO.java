package dto;

import java.util.List;

public class UnitDTO {
    private String name;
    private int minSize;
    private int maxSize;
    private String role;
    private List<String> modelNames;
    private List<String> optionGroupIds;
    
    public UnitDTO(
    		String name, 
    		int minSize, 
    		int maxSize, 
    		String role, 
    		List<String> modelNames,
			List<String> optionGroupIds) {
		super();
		this.name = name;
		this.minSize = minSize;
		this.maxSize = maxSize;
		this.role = role;
		this.modelNames = modelNames;
		this.optionGroupIds = optionGroupIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinSize() {
		return minSize;
	}

	public void setMinSize(int minSize) {
		this.minSize = minSize;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<String> getModelNames() {
		return modelNames;
	}

	public void setModelNames(List<String> modelNames) {
		this.modelNames = modelNames;
	}

	public List<String> getOptionGroupIds() {
		return optionGroupIds;
	}

	public void setOptionGroupIds(List<String> optionGroupIds) {
		this.optionGroupIds = optionGroupIds;
	}
}
