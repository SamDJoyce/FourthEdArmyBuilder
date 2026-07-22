package loaders;

import dto.AddModelEffectDTO;
import dto.AddWargearEffectDTO;
import dto.ChangeModelNameEffectDTO;
import dto.EffectDTO;
import dto.ModifyStatEffectDTO;
import dto.ReplaceModelEffectDTO;
import dto.ReplaceWargearEffectDTO;
import units.ModelFactory;
import units.WargearFactory;
import units.options.effects.Effect;
import units.options.effects.EffectFactory;

public class EffectLoader {
	public static Effect load(EffectDTO dto) {
		switch(dto.getType()) {
			case "add_model":
				AddModelEffectDTO am = (AddModelEffectDTO) dto;
				return EffectFactory.addModel(
									am.getName(), 
									ModelFactory.get(am.getModelName()));
			case "replace_model":
				ReplaceModelEffectDTO rm = (ReplaceModelEffectDTO) dto;
				return EffectFactory.replaceModel(
									rm.getName(), 
									ModelFactory.getInstance(rm.getNewModelName()), 
									ModelFactory.getInstance(rm.getOldModelName()));
				
			case "add_wargear":
				AddWargearEffectDTO aw = (AddWargearEffectDTO) dto;
				return EffectFactory.addWargear(
									aw.getName(), 
									WargearFactory.get(aw.getGearName()));
			case "replace_wargear":
				ReplaceWargearEffectDTO rw = (ReplaceWargearEffectDTO) dto;
				return EffectFactory.replaceWargear(
									rw.getName(), 
									WargearFactory.get(rw.getRemoveName()), 
									WargearFactory.get(rw.getAddName()));
				
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
}
