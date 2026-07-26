package dto;

import java.util.Set;

public class ModelDTO {

    private String name;
    private int basePoints;
    private String statlineName;
    private Set<String> typeNames;
    private Set<String> optionGroupNames;
    private Set<String> wargearNames;
    
    public ModelDTO() {}
    
	public ModelDTO( 
			String name, 
			int basePoints, 
			String statlineName, 
			Set<String> typeNames,
			Set<String> optionGroupNames,
			Set<String> wargearNames) {
		this.name = name;
		this.basePoints = basePoints;
		this.statlineName = statlineName;
		this.typeNames = typeNames;
		this.optionGroupNames = optionGroupNames;
		this.wargearNames = wargearNames;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBasePoints() {
		return basePoints;
	}

	public void setBasePoints(int points) {
		this.basePoints = points;
	}

	public String getStatlineName() {
		return statlineName;
	}

	public void setStatlineName(String statlineId) {
		this.statlineName = statlineId;
	}

	public Set<String> getTypeNames() {
		return typeNames;
	}

	public void setTypeNames(Set<String> typeNames) {
		this.typeNames = typeNames;
	}

	public Set<String> getWargearNames() {
		return wargearNames;
	}

	public void setWargearNames(Set<String> wargearNamess) {
		this.wargearNames = wargearNamess;
	}

	public Set<String> getOptionGroupNames() {
		return optionGroupNames;
	}

	public void setOptionGroupNames(Set<String> optionGroupNames) {
		this.optionGroupNames = optionGroupNames;
	}

	
}
