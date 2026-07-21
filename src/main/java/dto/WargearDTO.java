package dto;

public class WargearDTO {
    private String id;
    private String name;
    private String type;
    private int points;
    
    public WargearDTO(
    		String id,
    		String name,
    		String type,
    		int points) {
    	this.id = id;
    	this.name = name;
    	this.type = type;
    	this.points = points;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}
