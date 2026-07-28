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
	    @JsonSubTypes.Type(value = CharactersOnlyReqDTO.class, name   = "characters_only"),
	    @JsonSubTypes.Type(value = MaxPerModelCountReqDTO.class, name = "max_per_model_count"),
	    @JsonSubTypes.Type(value = MaxSelectionReqDTO.class, name     = "max_selection"),
	    @JsonSubTypes.Type(value = ModelCountReqDTO.class, name       = "model_count"),
	    @JsonSubTypes.Type(value = MustHaveGearReqDTO.class, name     = "must_have_gear"),
	    @JsonSubTypes.Type(value = MustHaveTypeReqDTO.class, name     = "must_have_type"),
	    @JsonSubTypes.Type(value = MutualExclusionReqDTO.class, name  = "mutual_exclusion"),
	    @JsonSubTypes.Type(value = ArmouryPointsLimitReqDTO.class, name  = "armoury_points_limit")
	})
public abstract class RequirementDTO {
	private String type;
	
	public RequirementDTO() {}
	
	public RequirementDTO(String type) {
		this.type = type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
