package loaders;

import dto.CharactersOnlyReqDTO;
import dto.MaxPerModelCountReqDTO;
import dto.MaxSelectionReqDTO;
import dto.ModelCountReqDTO;
import dto.MustHaveGearReqDTO;
import dto.MustHaveTypeReqDTO;
import dto.MutualExclusionReqDTO;
import dto.RequirementDTO;
import units.ModelFactory;
import units.UnitType;
import units.WargearFactory;
import units.options.OptionChoiceFactory;
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
				return ReqFactory.mutualExclusion(me.getName(),
						OptionChoiceFactory.get(me.getExcluded()));
				 // differentiate between list and single exclusions
				
			case "max_selection":
				MaxSelectionReqDTO ms = (MaxSelectionReqDTO) dto;
				return ReqFactory.maxSelection(ms.getName(), ms.getMaxSelection());
				
			case "max_per_model_count":
				MaxPerModelCountReqDTO mpmc = (MaxPerModelCountReqDTO) dto;
				return ReqFactory.maxPerModelCount(
									mpmc.getName(), 
									ModelFactory.get(mpmc.getModelName()),
									mpmc.getRate());
			case "model_count":
				ModelCountReqDTO mc = (ModelCountReqDTO) dto;
				return ReqFactory.modelCount(
									mc.getName(), 
									ModelFactory.get(mc.getModelName()), 
									mc.getMinimum(), 
									mc.getMaximum());
				
			case "must_have_type":
				MustHaveTypeReqDTO mht = (MustHaveTypeReqDTO) dto;
				return ReqFactory.mustHaveType(
						mht.getType(),
						UnitType.fromString(mht.getRequiredType()));
				
			case "must_have_gear":
				MustHaveGearReqDTO mhg = (MustHaveGearReqDTO) dto;
				return ReqFactory.mustHaveGear(mhg.getName(),
						WargearFactory.get(mhg.getRequiredGear()));
		}
		return null;
	}
	
}
