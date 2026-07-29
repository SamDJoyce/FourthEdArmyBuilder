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
	    @JsonSubTypes.Type(value = AddModelEffectDTO.class, name = "add_model"),
	    @JsonSubTypes.Type(value = AddWargearEffectDTO.class, name = "add_wargear"),
	    @JsonSubTypes.Type(value = ChangeModelNameEffectDTO.class, name = "change_model_name"),
	    @JsonSubTypes.Type(value = ModifyStatEffectDTO.class, name = "modify_stat"),
	    @JsonSubTypes.Type(value = ReplaceModelEffectDTO.class, name = "replace_model"),
	    @JsonSubTypes.Type(value = ReplaceWargearEffectDTO.class, name = "replace_wargear"),
	    @JsonSubTypes.Type(value = AddGearToSquadEffectDTO.class, name = "add_gear_to_squad")
	})
public abstract class EffectDTO {
	private String type;
	
	public EffectDTO() {}
	
	public EffectDTO(String type) {
		this.type = type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
