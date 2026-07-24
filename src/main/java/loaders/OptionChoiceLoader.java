package loaders;

import java.util.ArrayList;
import java.util.List;

import dto.OptionChoiceDTO;
import units.options.OptionChoice;
import units.options.OptionChoiceFactory;

public class OptionChoiceLoader {
	public static OptionChoice load(OptionChoiceDTO dto) {
		return OptionChoiceFactory.create(dto.getName(), dto.getPoints() );
	}
	
	public static List<OptionChoice> loadAll(List<OptionChoiceDTO> dtos){
		List<OptionChoice> choices = new ArrayList<>();
		for (OptionChoiceDTO d : dtos) {
			choices.add(OptionChoiceFactory.create(
					d.getName(),
					d.getPoints()));
		}
		return choices;
	}
}
