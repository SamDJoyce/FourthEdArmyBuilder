package loaders;

import java.util.ArrayList;
import java.util.List;

import dto.OptionGroupDTO;
import units.options.OptionChoiceFactory;
import units.options.OptionGroup;
import units.options.OptionGroupFactory;
import units.options.requirements.ReqFactory;

public class OptionGroupLoader {
	
	public OptionGroupLoader() {};
	
	public OptionGroup load(OptionGroupDTO dto) {
		return OptionGroupFactory.create(
				dto.getName(),
				OptionChoiceFactory.get(dto.getChoiceNames()),
				ReqFactory.get(dto.getRequirementNames()),
				dto.getMinSelections(),
				dto.getMaxSelections()
				);
	}
	
	public List<OptionGroup> loadAll(List<OptionGroupDTO> dtos){
		List<OptionGroup> groups = new ArrayList<>();
		
		for (OptionGroupDTO d : dtos) {
			groups.add(load(d));
		}
		
		return groups;
	}
}
