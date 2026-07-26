package loaders;

import java.util.ArrayList;
import java.util.List;

import dto.ModelDTO;
import units.ModelFactory;
import units.UnitType;
import units.WargearFactory;
import units.descriptions.models.ModelDescription;
import units.descriptions.models.StatLineFactory;
import units.options.OptionGroupFactory;

public class ModelLoader {
	public ModelLoader() {}
	
	public ModelDescription create(ModelDTO dto) {
		return ModelFactory.createDescription(
				dto.getName(),
				dto.getBasePoints()
				);
	}
	
	public ModelDescription resolveReferences(ModelDTO dto) {
		ModelDescription model = ModelFactory.get(dto.getName());
		// assign stats, types (in constructor), optionGroups, wargear
		return model;
	}
	
	public ModelDescription load(ModelDTO dto) {
		
		return ModelFactory.createDescription(
				dto.getName(),
				dto.getBasePoints(), 
				StatLineFactory.get(dto.getStatlineName()),
				UnitType.fromStrings(dto.getTypeNames()),
				OptionGroupFactory.get(dto.getOptionGroupNames()),
				WargearFactory.get(dto.getWargearNames()));
	}
	
	public List<ModelDescription> loadAll(List<ModelDTO> dtos){
		List<ModelDescription> models = new ArrayList<>();
		for(ModelDTO d : dtos) {
			models.add(load(d));
		}
		return models;
	}
}
