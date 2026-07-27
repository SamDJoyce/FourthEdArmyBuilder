package loaders;

import java.util.ArrayList;
import java.util.List;

import dto.OptionChoiceDTO;
import units.options.OptionChoice;
import units.options.OptionChoiceFactory;
import units.options.effects.EffectFactory;
import units.options.requirements.ReqFactory;

public class OptionChoiceLoader {
	public OptionChoice create(OptionChoiceDTO dto) {
		return OptionChoiceFactory.create(dto.getName(), dto.getPoints() );
	}
	
	public List<OptionChoice> createAll(List<OptionChoiceDTO> dtos){
		List<OptionChoice> choices = new ArrayList<>();
		for (OptionChoiceDTO d : dtos) {
			choices.add(OptionChoiceFactory.create(
					d.getName(),
					d.getPoints()));
		}
		return choices;
	}
	
	public OptionChoice resolveReferences(OptionChoiceDTO dto) {
		OptionChoice choice = OptionChoiceFactory.get(dto.getName());
		choice.setEffects(EffectFactory.getAll(dto.getEffectNames()));
		choice.setRequirements(ReqFactory.getAll(dto.getRequirementNames()));
		return choice;
	}
	
	public List<OptionChoice> resolveAllReferences(List<OptionChoiceDTO> dtos){
		List<OptionChoice> choices = new ArrayList<>();
		for (OptionChoiceDTO d : dtos) {
			choices.add(resolveReferences(d));
		}
		return choices;
	}
	
}
