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

	public void loadWargear(Path file) throws IOException {
		List<WargearDTO> dtos = mapper.readValue(
	            file.toFile(),
	            new TypeReference<List<WargearDTO>>() {}
	    );
		wargearLoader.loadAll(dtos);
	}
	
	public void loadStatLines(Path file)  throws IOException{
		List<StatLineDTO> dtos = mapper.readValue(
				file.toFile(),
				new TypeReference<List<StatLineDTO>>(){}
				);
		statLineLoader.loadAll(dtos);
	}
	
	public void loadModels(Path file) throws IOException{
		List<ModelDTO> dtos = mapper.readValue(
				file.toFile(),
				new TypeReference<List<ModelDTO>>(){}
				);
		modelLoader.loadAll(dtos);
	}
	
	public void loadRequirements (Path file) throws IOException {
		List<RequirementDTO> dtos = mapper.readValue(
				file.toFile(),
				new TypeReference<List<RequirementDTO>>(){}
				);
		reqLoader.loadAll(dtos);
	}
	
	public void loadEffects(Path file) throws IOException {
		List<EffectDTO> dtos = mapper.readValue(
				file.toFile(),
				new TypeReference<List<EffectDTO>>(){}
				);
		effectLoader.loadAll(dtos);
	}
	
	public void loadOptionChoices(Path file) throws IOException {
		List<OptionChoiceDTO> choices = mapper.readValue(
				file.toFile(),
				new TypeReference<List<OptionChoiceDTO>>(){}
				);
		optionChoiceLoader.loadAll(choices);
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
