package dto;

import java.util.List;

public class UnitDTO {
	
	private String id;
    private String name;
    private int minSize;
    private int maxSize;
    private String role;
    private List<String> modelIds;
    private List<String> optionGroupIds;
    
    public UnitDTO(
    		String id, 
    		String name, 
    		int minSize, 
    		int maxSize, 
    		String role, 
    		List<String> modelIds,
			List<String> optionGroupIds) {
		super();
		this.id = id;
		this.name = name;
		this.minSize = minSize;
		this.maxSize = maxSize;
		this.role = role;
		this.modelIds = modelIds;
		this.optionGroupIds = optionGroupIds;
	}
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public List<String> getModelIds() {
		return modelIds;
	}

	public void setModelIds(List<String> modelIds) {
		this.modelIds = modelIds;
	}

	public List<String> getOptionGroupIds() {
		return optionGroupIds;
	}

	public void setOptionGroupIds(List<String> optionGroupIds) {
		this.optionGroupIds = optionGroupIds;
	}
}
