package loaders;

import java.util.HashSet;
import java.util.Set;

import dto.ModelDTO;
import units.ModelFactory;
import units.UnitType;
import units.descriptions.models.ModelDescription;
import units.descriptions.models.StatLineFactory;

public class ModelLoader {
	public ModelLoader() {}
	
	public ModelDescription load(ModelDTO dto) {
		Set<UnitType> types = fromStrings(dto.getTypeNames());
		
		return ModelFactory.getDescription(
				dto.getName(),
				dto.getPoints(), 
				null, // Statline
				types, 
				null); // Gear
	}
	
	private Set<UnitType> fromStrings(Set<String> typeNames){
		Set<UnitType> types = new HashSet<>();
		for (String typeName : typeNames) {
			types.add(UnitType.fromString(typeName));
		}
		return types;
	}
}
