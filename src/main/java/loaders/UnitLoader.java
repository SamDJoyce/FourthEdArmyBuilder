package loaders;

import java.util.ArrayList;
import java.util.List;

import dto.UnitDTO;
import units.ModelFactory;
import units.UnitFactory;
import units.UnitRole;
import units.UnitType;
import units.descriptions.UnitDescription;
import units.options.OptionGroupFactory;

public class UnitLoader {
	public UnitLoader() {}
	
	public UnitDescription create(UnitDTO dto) {
		return UnitFactory.createDescription(
							dto.getName(), 
							dto.getMinSize(), 
							dto.getMaxSize(), 
							UnitRole.fromString(dto.getRole()));
	}
	
	public List<UnitDescription> createAll(List<UnitDTO> dtos){
		List<UnitDescription> units = new ArrayList<>();
		
		for (UnitDTO d : dtos) {
			units.add(create(d));
		}
		
		return units;
	}
	
	public UnitDescription resolveReferences(UnitDTO dto) {
		UnitDescription unit = UnitFactory.get(dto.getName());
		
		unit.setOptions(OptionGroupFactory.getAll(dto.getOptionGroupIds()));
		unit.setModels(ModelFactory.getAll(dto.getModelNames()));
		unit.setTypes(UnitType.fromStrings(dto.getTypes()));
		
		return unit;
	}
	
	public List<UnitDescription> resolveAllReferences(List<UnitDTO> dtos){
		List<UnitDescription> units = new ArrayList<>();
		
		for (UnitDTO d : dtos) {
			units.add(resolveReferences(d));
		}
		
		return units;
	}
	
}
