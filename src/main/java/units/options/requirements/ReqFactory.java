package units.options.requirements;

import java.util.List;

import dto.RequirementDTO;
import units.UnitType;
import units.descriptions.models.ModelDescription;
import units.descriptions.wargear.WargearDescription;
import units.options.OptionChoice;

public class ReqFactory {
	
	public static Requirement fromDTO(RequirementDTO dto) {
		switch(dto.getType()){
		case "characters_only":
			return null; // charactersOnly()
		case "mutual_exclusion":
			return null; // differentiate between list and single exclusions
		case "max_selection":
			return null; // maxSelection() - does this duplicate the two below?
		case "max_per_model_count":
			return null; // maxPerModelCount()
		case "model_count":
			return null; // modelCount()
		case "must_have_type":
			return null; // mustHaveType()
		case "must_have_gear":
			return null; // mustHaveGear()
		}
		return null;
	}
	
	public static CharacterOnlyReq charactersOnly() {
		return  new CharacterOnlyReq();
	}
	
	public static MutualExclusionReq mutualExclusion(List<OptionChoice> excluded) {
		return new MutualExclusionReq(excluded);
	}
	
	public static MutualExclusionReq mutualExclusion(OptionChoice excluded) {
		return new MutualExclusionReq(List.of(excluded));
	}
	
	public static MaxSelectionReq maxSelection(int maxSelection) {
		return new MaxSelectionReq(maxSelection);
	}
	
	public static MaxPerModelCountReq maxPerModelCount(
			ModelDescription model,
			int rate) {
		return new MaxPerModelCountReq(model,rate);
	}
	
	public static ModelCountReq modelCount(
			ModelDescription model,
			int minimum,
			int maximum
			) {
		return new ModelCountReq(
				model,
				minimum,
				maximum);
	}
	
	public static MustHaveTypeReq mustHaveType(UnitType type){
		return new MustHaveTypeReq(type);
	}
	
	public static MustHaveGearReq mustHaveGear(WargearDescription requiredGear) {
		return new MustHaveGearReq(requiredGear);
	}
}
