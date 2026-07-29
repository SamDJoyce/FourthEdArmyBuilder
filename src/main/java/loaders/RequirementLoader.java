package loaders;

import java.util.ArrayList;
import java.util.List;

import dto.ArmouryPointsLimitReqDTO;
import dto.CannotHaveGearReqDTO;
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
import units.options.requirements.CannotHaveGearReq;
import units.options.requirements.MaxPerModelCountReq;
import units.options.requirements.MustHaveGearReq;
import units.options.requirements.MutualExclusionReq;
import units.options.requirements.ReqFactory;
import units.options.requirements.Requirement;

public class RequirementLoader {
	public Requirement create(RequirementDTO dto) {
		switch(dto.getType()){
			case "characters_only":
				CharactersOnlyReqDTO co = (CharactersOnlyReqDTO) dto;
				return ReqFactory.charactersOnly(co.getName());
				
			case "mutual_exclusion":
				MutualExclusionReqDTO me = (MutualExclusionReqDTO) dto;
				return ReqFactory.mutualExclusion(me.getName());
				 // differentiate between list and single exclusions
				
			case "max_selection":
				MaxSelectionReqDTO ms = (MaxSelectionReqDTO) dto;
				return ReqFactory.maxSelection(ms.getName(), ms.getMaxSelection());
				
			case "max_per_model_count":
				MaxPerModelCountReqDTO mpmc = (MaxPerModelCountReqDTO) dto;
				return ReqFactory.maxPerModelCount(
									mpmc.getName(),
									mpmc.getRate());
			case "model_count":
				ModelCountReqDTO mc = (ModelCountReqDTO) dto;
				return ReqFactory.modelCount(
									mc.getName(), 
									mc.getMinimum(), 
									mc.getMaximum());
				
			case "must_have_type":
				MustHaveTypeReqDTO mht = (MustHaveTypeReqDTO) dto;
				return ReqFactory.mustHaveType(
						mht.getType(),
						UnitType.fromString(mht.getRequiredType()));
				
			case "must_have_gear":
				MustHaveGearReqDTO mhg = (MustHaveGearReqDTO) dto;
				return ReqFactory.mustHaveGear(mhg.getName());
				
			case "cannot_have_gear":
				CannotHaveGearReqDTO chg = (CannotHaveGearReqDTO) dto;
				return ReqFactory.cannotHaveGear(chg.getName());
			
			case "armoury_points_limit":
				ArmouryPointsLimitReqDTO apl = (ArmouryPointsLimitReqDTO) dto;
				return ReqFactory.armouryPointsLimit(apl.getName(), apl.getLimit());
		}
		return null;
	}
	
	public List<Requirement> createAll(List<RequirementDTO> dtos){
		List<Requirement> reqs = new ArrayList<>();
		
		for (RequirementDTO d : dtos) {
			reqs.add(create(d));
		}
		
		return reqs;
	}
	
	public Requirement resolveReferences(RequirementDTO dto) {
		switch(dto.getType()){
			case "characters_only":
				CharactersOnlyReqDTO co = (CharactersOnlyReqDTO) dto;
				return ReqFactory.charactersOnly(co.getName());
				
			case "mutual_exclusion":
				MutualExclusionReqDTO me = (MutualExclusionReqDTO) dto;
				MutualExclusionReq mutualExclusion = 
						(MutualExclusionReq) ReqFactory.get(me.getName());
				mutualExclusion.setExcluded(OptionChoiceFactory.getAll(me.getExcludedChoiceNames()));
				
				return mutualExclusion;
				 // differentiate between list and single exclusions eventually
				
			case "max_selection":
				MaxSelectionReqDTO ms = (MaxSelectionReqDTO) dto;
				return ReqFactory.get(ms.getName());
				
			case "max_per_model_count":
				MaxPerModelCountReqDTO mpmc = (MaxPerModelCountReqDTO) dto;
				MaxPerModelCountReq maxPerModelCount = (MaxPerModelCountReq) ReqFactory.get(mpmc.getName());
				maxPerModelCount.setModel(ModelFactory.get(mpmc.getModelName()));
				return maxPerModelCount;
				
			case "model_count":
				ModelCountReqDTO mc = (ModelCountReqDTO) dto;
				return ReqFactory.get(mc.getName());
				
			case "must_have_type":
				MustHaveTypeReqDTO mht = (MustHaveTypeReqDTO) dto;
				//MustHaveTypeReq mustHaveType = (MustHaveTypeReq) ReqFactory.get(mht.getName());
				return ReqFactory.get(mht.getName());
				
			case "must_have_gear":
				MustHaveGearReqDTO mhg = (MustHaveGearReqDTO) dto;
				MustHaveGearReq mustHaveGear = (MustHaveGearReq) ReqFactory.get(mhg.getName());
				mustHaveGear.setRequiredGear(WargearFactory.get(mhg.getRequiredGear()));
				return mustHaveGear;
				
			case "cannot_have_gear":
				CannotHaveGearReqDTO chg = (CannotHaveGearReqDTO) dto;
				CannotHaveGearReq cannotHaveGear = (CannotHaveGearReq) ReqFactory.get(chg.getName());
				cannotHaveGear.setBlockingGear(WargearFactory.get(chg.getBlockingGearName()));
				return cannotHaveGear;
				
			case "armoury_points_limit":
				ArmouryPointsLimitReqDTO apl = (ArmouryPointsLimitReqDTO) dto;
				return ReqFactory.get(apl.getName());
				
		}
		return null;
	}
	
	public List<Requirement> resolveAllReferences(List<RequirementDTO> dtos){
		List<Requirement> reqs = new ArrayList<>();
		
		for (RequirementDTO d : dtos) {
			reqs.add(resolveReferences(d));
		}
		
		return reqs;
	}
	
	
}
