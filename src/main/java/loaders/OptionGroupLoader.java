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
	
	public OptionGroup create(OptionGroupDTO dto) {
		return OptionGroupFactory.create(
				dto.getName(),
				dto.getMinSelections(),
				dto.getMaxSelections()
				);
	}
	
//	public OptionGroup create(OptionGroupDTO dto) {
//		return OptionGroupFactory.create(
//				dto.getName(),
//				OptionChoiceFactory.get(dto.getChoiceNames()),
//				ReqFactory.getAll(dto.getRequirementNames()),
//				dto.getMinSelections(),
//				dto.getMaxSelections()
//				);
//	}
	
	public List<OptionGroup> createAll(List<OptionGroupDTO> dtos){
		List<OptionGroup> groups = new ArrayList<>();
		
		for (OptionGroupDTO d : dtos) {
			groups.add(create(d));
		}
		
		return groups;
	}
	
	public OptionGroup resolveReferences(OptionGroupDTO dto) {
		OptionGroup group = OptionGroupFactory.get(dto.getName());
		group.setChoices(OptionChoiceFactory.getAll(dto.getChoiceNames()));
		group.setRequirements(ReqFactory.getAll(dto.getRequirementNames()));
		return group;
	}
	
	public List<OptionGroup> resolveAllReferences(List<OptionGroupDTO> dtos){
		List<OptionGroup> groups = new ArrayList<>();
		
		for (OptionGroupDTO d : dtos) {
			groups.add(resolveReferences(d));
		}
		
		return groups;
	}
	
	
}
