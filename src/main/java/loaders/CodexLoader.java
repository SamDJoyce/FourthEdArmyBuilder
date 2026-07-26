package loaders;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.EffectDTO;
import dto.ModelDTO;
import dto.OptionChoiceDTO;
import dto.OptionGroupDTO;
import dto.RequirementDTO;
import dto.StatLineDTO;
import dto.UnitDTO;
import dto.WargearDTO;
import units.descriptions.models.ModelDescription;
import units.descriptions.models.StatLine;
import units.descriptions.wargear.WargearDescription;
import units.options.OptionChoice;
import units.options.effects.Effect;
import units.options.requirements.Requirement;

public class CodexLoader {
	private final WargearLoader  	 wargearLoader;
	private final StatlineLoader 	 statLineLoader;
	private final ModelLoader    	 modelLoader;
	private final UnitLoader 	 	 unitLoader;
	private final RequirementLoader  reqLoader;
	private final EffectLoader 		 effectLoader;
	private final OptionGroupLoader  optionGroupLoader;
	private final OptionChoiceLoader optionChoiceLoader;
	private final ObjectMapper 		 mapper;
	
	public CodexLoader() {
		wargearLoader 	   = new WargearLoader();
		statLineLoader 	   = new StatlineLoader();
		modelLoader 	   = new ModelLoader();
		unitLoader 		   = new UnitLoader();
		reqLoader 		   = new RequirementLoader();
		effectLoader 	   = new EffectLoader();
		optionGroupLoader  = new OptionGroupLoader();
		optionChoiceLoader = new OptionChoiceLoader();
		mapper 			   = new ObjectMapper();
	}

	public List<WargearDescription> loadWargear(Path file) throws IOException {
		List<WargearDTO> dtos = mapper.readValue(
	            file.toFile(),
	            new TypeReference<List<WargearDTO>>() {}
	    );
		return wargearLoader.loadAll(dtos);
	}
	
	public List<StatLine> loadStatLines(Path file)  throws IOException{
		List<StatLineDTO> dtos = mapper.readValue(
				file.toFile(),
				new TypeReference<List<StatLineDTO>>(){}
				);
		return statLineLoader.loadAll(dtos);
	}
	
	public List<ModelDescription> loadModels(Path file) throws IOException{
		List<ModelDTO> dtos = mapper.readValue(
				file.toFile(),
				new TypeReference<List<ModelDTO>>(){}
				);
		return modelLoader.loadAll(dtos);
	}
	
	public List<Requirement> loadRequirements (Path file) throws IOException {
		List<RequirementDTO> dtos = mapper.readValue(
				file.toFile(),
				new TypeReference<List<RequirementDTO>>(){}
				);
		return reqLoader.loadAll(dtos);
	}
	
	public List<Effect> loadEffects(Path file) throws IOException {
		List<EffectDTO> dtos = mapper.readValue(
				file.toFile(),
				new TypeReference<List<EffectDTO>>(){}
				);
		return effectLoader.loadAll(dtos);
	}
	
	public List<OptionChoice> loadOptionChoices(Path file) throws IOException {
		List<OptionChoiceDTO> choices = mapper.readValue(
				file.toFile(),
				new TypeReference<List<OptionChoiceDTO>>(){}
				);
		return optionChoiceLoader.loadAll(choices);
	}
	
	public void loadOptionGroups(Path file) throws IOException {
		List<OptionGroupDTO> groups = mapper.readValue(
				file.toFile(),
				new TypeReference<List<OptionGroupDTO>>(){}
				);
		optionGroupLoader.loadAll(groups);
	}
	
	public void loadUnits(Path file) throws IOException {
		List<UnitDTO> units =  mapper.readValue(
				file.toFile(),
				new TypeReference<List<UnitDTO>>(){}
				);
		unitLoader.loadAll(units);
	}
}
