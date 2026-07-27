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
				dto.getBasePoints(),
				UnitType.fromStrings(dto.getTypeNames())
				);
	}
	
	public List<ModelDescription> createAll(List<ModelDTO> dtos){
		List<ModelDescription> models = new ArrayList<>();
		for(ModelDTO d : dtos) {
			models.add(create(d));
		}
		return models;
	}
	
	public ModelDescription resolveReferences(ModelDTO dto) {
		ModelDescription model = ModelFactory.get(dto.getName());
		model.setStats(StatLineFactory.get(dto.getStatlineName()));
		model.setOptions(OptionGroupFactory.getAll(dto.getOptionGroupNames()));
		model.setGear(WargearFactory.get(dto.getWargearNames()));
		return model;
	}
	
	public List<ModelDescription> resolveAllReferences(List<ModelDTO> dtos){
		List<ModelDescription> models = new ArrayList<>();
		for(ModelDTO d : dtos) {
			models.add(resolveReferences(d));
		}
		return models;
	}
	
	public ModelDescription load(ModelDTO dto) {
		
		return ModelFactory.createDescription(
				dto.getName(),
				dto.getBasePoints(), 
				StatLineFactory.get(dto.getStatlineName()),
				UnitType.fromStrings(dto.getTypeNames()),
				OptionGroupFactory.getAll(dto.getOptionGroupNames()),
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
