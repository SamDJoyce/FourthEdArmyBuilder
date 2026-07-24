package loaders;

import java.util.ArrayList;
import java.util.List;

import dto.UnitDTO;
import units.ModelFactory;
import units.UnitFactory;
import units.UnitRole;
import units.descriptions.UnitDescription;
import units.options.OptionGroupFactory;

public class UnitLoader {
	public UnitLoader() {}
	
	public UnitDescription load(UnitDTO dto) {
		return UnitFactory.createDescription(
							dto.getName(), 
							dto.getMinSize(), 
							dto.getMaxSize(), 
							UnitRole.fromString(dto.getRole()), 
							OptionGroupFactory.get(dto.getOptionGroupIds()),
							ModelFactory.get(dto.getModelNames()));
	}
	
	public List<UnitDescription> loadAll(List<UnitDTO> dtos){
		List<UnitDescription> units = new ArrayList<>();
		
		for (UnitDTO d : dtos) {
			units.add(load(d));
		}
		
		return units;
	}
	
}
