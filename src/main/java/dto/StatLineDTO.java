package dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
	    use = JsonTypeInfo.Id.NAME,
	    include = JsonTypeInfo.As.PROPERTY,
	    property = "type",
	    visible = true
	)
	@JsonSubTypes({
	    @JsonSubTypes.Type(value = StatLineInfantryDTO.class, name = "infantry"),
	    @JsonSubTypes.Type(value = StatLineVehicleDTO.class, name = "vehicle"),
	    @JsonSubTypes.Type(value = StatLineWalkerDTO.class, name = "walker")
	})

public abstract class StatLineDTO {
	private String type;
	
	public StatLineDTO() {}
	
	public StatLineDTO(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
