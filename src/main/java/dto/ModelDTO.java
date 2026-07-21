package dto;

import java.util.List;

public class ModelDTO {

    private String id;
    private String name;
    private int points;
    private String statlineId;
    private List<String> typeIds;
    private List<String> wargearIds;
    
	public ModelDTO(
			String id, 
			String name, 
			int points, 
			String statlineId, 
			List<String> typeIds,
			List<String> wargearIds) {
		super();
		this.id = id;
		this.name = name;
		this.points = points;
		this.statlineId = statlineId;
		this.typeIds = typeIds;
		this.wargearIds = wargearIds;
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

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getStatlineId() {
		return statlineId;
	}

	public void setStatlineId(String statlineId) {
		this.statlineId = statlineId;
	}

	public List<String> getTypeIds() {
		return typeIds;
	}

	public void setTypeIds(List<String> typeIds) {
		this.typeIds = typeIds;
	}

	public List<String> getWargearIds() {
		return wargearIds;
	}

	public void setWargearIds(List<String> wargearIds) {
		this.wargearIds = wargearIds;
	}

	
}
