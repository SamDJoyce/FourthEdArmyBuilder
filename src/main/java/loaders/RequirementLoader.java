package loaders;

import dto.CharactersOnlyReqDTO;
import dto.MaxPerModelCountReqDTO;
import dto.MaxSelectionReqDTO;
import dto.ModelCountReqDTO;
import dto.MustHaveGearReqDTO;
import dto.MustHaveTypeReqDTO;
import dto.MutualExclusionReqDTO;
import dto.RequirementDTO;
import units.UnitType;
import units.options.requirements.ReqFactory;
import units.options.requirements.Requirement;

public class RequirementLoader {
	public static Requirement load(RequirementDTO dto) {
		switch(dto.getType()){
			case "characters_only":
				CharactersOnlyReqDTO co = (CharactersOnlyReqDTO) dto;
				return ReqFactory.charactersOnly(co.getName());
			case "mutual_exclusion":
				MutualExclusionReqDTO me = (MutualExclusionReqDTO) dto;
				return null;
				 // differentiate between list and single exclusions
			case "max_selection":
				MaxSelectionReqDTO ms = (MaxSelectionReqDTO) dto;
				return null; // maxSelection() - does this duplicate the two below?
			case "max_per_model_count":
				MaxPerModelCountReqDTO mpmc = (MaxPerModelCountReqDTO) dto;
				return null; // maxPerModelCount()
			case "model_count":
				ModelCountReqDTO mc = (ModelCountReqDTO) dto;
				 // modelCount()
				return null;
			case "must_have_type":
				MustHaveTypeReqDTO mht = (MustHaveTypeReqDTO) dto;
				return ReqFactory.mustHaveType(
						mht.getType(),
						UnitType.fromString(mht.getRequiredType())); // mustHaveType()
			case "must_have_gear":
				MustHaveGearReqDTO mhg = (MustHaveGearReqDTO) dto;
//				return ReqFactory.mustHaveGear(
//						WargearFactory.getDescription(
//								mhg.getName(), 
//								mhg.getRequiredGear(), 
//								mhg.getPoints()));
		}
		return null;
	}
}
