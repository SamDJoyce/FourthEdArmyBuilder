package loaders;

import java.util.ArrayList;
import java.util.List;

import dto.AddModelEffectDTO;
import dto.AddWargearEffectDTO;
import dto.ChangeModelNameEffectDTO;
import dto.EffectDTO;
import dto.ModifyStatEffectDTO;
import dto.ReplaceModelEffectDTO;
import dto.ReplaceWargearEffectDTO;
import units.ModelFactory;
import units.WargearFactory;
import units.options.effects.AddModelEffect;
import units.options.effects.AddWargearEffect;
import units.options.effects.Effect;
import units.options.effects.EffectFactory;
import units.options.effects.ReplaceModelEffect;
import units.options.effects.ReplaceWargearEffect;

public class EffectLoader {
	public Effect create(EffectDTO dto) {
		switch(dto.getType()) {
			// Create Placeholder objects
			case "add_model":
				AddModelEffectDTO am = (AddModelEffectDTO) dto;
				return EffectFactory.addModel(
									am.getName());
			case "replace_model":
				ReplaceModelEffectDTO rm = (ReplaceModelEffectDTO) dto;
				return EffectFactory.replaceModel(
									rm.getName());
				
			case "add_wargear":
				AddWargearEffectDTO aw = (AddWargearEffectDTO) dto;
				return EffectFactory.addWargear(
									aw.getName());
			case "replace_wargear":
				ReplaceWargearEffectDTO rw = (ReplaceWargearEffectDTO) dto;
				return EffectFactory.replaceWargear(
									rw.getName());
			// Create full Effect Objects (no references to resolve)
			case "modify_stat":
				ModifyStatEffectDTO ms = (ModifyStatEffectDTO) dto;
				return EffectFactory.modifyStat(
									ms.getName(), 
									ms.getStat(), 
									ms.getModifier());
			case "change_model_name":
				ChangeModelNameEffectDTO cmn = (ChangeModelNameEffectDTO) dto;
				return EffectFactory.changeModelName(
									cmn.getEffectName(), 
									cmn.getNewName());
		}
		return null;
	}
	
	public List<Effect> createAll(List<EffectDTO> dtos){
		List<Effect> effects = new ArrayList<>();
		
		for (EffectDTO d : dtos) {
			effects.add(create(d));
		}
		
		return effects;
	}
	
	public Effect resolveReferences(EffectDTO dto) {
		switch(dto.getType()) {
			case "add_model":
				AddModelEffectDTO am = (AddModelEffectDTO) dto;
				AddModelEffect addModel = (AddModelEffect)EffectFactory.get(am.getName());
				addModel.setModel(ModelFactory.get(am.getModelName()));
				return addModel;
			case "replace_model":
				ReplaceModelEffectDTO rm = (ReplaceModelEffectDTO) dto;
				ReplaceModelEffect replaceModel = (ReplaceModelEffect) EffectFactory.get(rm.getName());
				replaceModel.setOldModel(ModelFactory.getInstance(rm.getOldModelName()));
				replaceModel.setNewModel(ModelFactory.getInstance(rm.getNewModelName()));
				return replaceModel;
				
			case "add_wargear":
				AddWargearEffectDTO aw = (AddWargearEffectDTO) dto;
				AddWargearEffect addWargear = (AddWargearEffect) EffectFactory.get(aw.getName());
				addWargear.setGear(WargearFactory.get(aw.getGearName()));
				return addWargear;
				
			case "replace_wargear":
				ReplaceWargearEffectDTO rw = (ReplaceWargearEffectDTO) dto;
				ReplaceWargearEffect replaceWargear = (ReplaceWargearEffect) EffectFactory.get(rw.getName());
				replaceWargear.setRemove(WargearFactory.get(rw.getRemoveName()));
				replaceWargear.setAdd(WargearFactory.get(rw.getAddName()));
				return replaceWargear;
				
			case "modify_stat":
				ModifyStatEffectDTO ms = (ModifyStatEffectDTO) dto;
				return EffectFactory.get(ms.getName());
				
			case "change_model_name":
				ChangeModelNameEffectDTO cmn = (ChangeModelNameEffectDTO) dto;
				return EffectFactory.get(cmn.getEffectName());
		}
		return null;
	}
	
	public List<Effect> resolveAllReferences(List<EffectDTO> dtos){
		List<Effect> effects = new ArrayList<>();
		
		for (EffectDTO e : dtos) {
			effects.add(resolveReferences(e));
		}
		
		return effects;
	}
}
