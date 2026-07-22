package dto;

import java.util.List;
import java.util.Set;

public class UnitDTO {
    private String name;
    private int minSize;
    private int maxSize;
    private String role;
    private List<String> modelNames;
    private Set<String> optionGroupIds;
    
    public UnitDTO(
    		String name, 
    		int minSize, 
    		int maxSize, 
    		String role, 
    		List<String> modelNames,
    		Set<String> optionGroupIds) {
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

	public Set<String> getOptionGroupIds() {
		return optionGroupIds;
	}

	public void setOptionGroupIds(Set<String> optionGroupIds) {
		this.optionGroupIds = optionGroupIds;
	}
}
