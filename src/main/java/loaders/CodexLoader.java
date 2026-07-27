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
import units.descriptions.UnitDescription;
import units.descriptions.models.ModelDescription;
import units.descriptions.models.StatLine;
import units.descriptions.wargear.WargearDescription;
import units.options.OptionChoice;
import units.options.OptionGroup;
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
	
	private final String codexFolder;
	private final Path wargearFile;
	private final Path statLineFile;
	private final Path modelFile;
	private final Path effectFile;
	private final Path requirementFile;
	private final Path optionChoiceFile;
	private final Path optionGroupFile;
	private final Path unitFile;
	
	private List<ModelDTO> 	      modelDtos;
	private List<EffectDTO> 	  effectDtos;
	private List<RequirementDTO>  reqDtos;
	private List<OptionChoiceDTO> choiceDtos;
	private List<OptionGroupDTO>  groupDtos;
	private List<UnitDTO> 		  unitDtos;
	
	public CodexLoader(String codexFolder) {
		wargearLoader 	   = LoaderFactory.forWargear();
		statLineLoader 	   = LoaderFactory.forStats();
		modelLoader 	   = LoaderFactory.forModels();
		unitLoader 		   = LoaderFactory.forUnits();
		reqLoader 		   = LoaderFactory.forReqs();
		effectLoader 	   = LoaderFactory.forEffects();
		optionGroupLoader  = LoaderFactory.forOptionGroups();
		optionChoiceLoader = LoaderFactory.forChoices();
		mapper 			   = new ObjectMapper();
		
		this.codexFolder  = codexFolder;
		wargearFile  	  = Path.of(this.codexFolder, "/wargear.json");
		statLineFile 	  = Path.of(this.codexFolder, "/statlines.json");
		modelFile 	  	  = Path.of(this.codexFolder, "/models.json");
		effectFile   	  = Path.of(this.codexFolder, "/effects.json");
		requirementFile   = Path.of(this.codexFolder, "/requirements.json");
		optionChoiceFile  = Path.of(this.codexFolder, "/optionChoices.json");
		optionGroupFile   = Path.of(this.codexFolder, "/optionGroups.json");
		unitFile		  = Path.of(this.codexFolder, "/units.json");
	}
	
	public void loadCodex(){
		// Load objects from file to create placeholders
		try {
			createWargear(wargearFile);
			createStatLines(statLineFile);
			createEffects(effectFile);
			createRequirements(requirementFile);
			createOptionChoices(optionChoiceFile);
			createOptionGroups(optionGroupFile);
			createModels(modelFile);
			createUnits(unitFile);
		} catch (Exception e) {
			System.out.println("There was a problem loading codex data.");
			System.out.println(e);
			e.printStackTrace();
		}
		// Populate objects by resolving references
		try {
			resolveEffects();
			resolveRequirements();
			resolveOptionChoices();
			resolveOptionGroups();
			resolveModels();
			resolveUnits();
		} catch (Exception e) {
			System.out.println("There was a problem resolving object references.");
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public List<WargearDescription> createWargear(Path file) throws IOException {
		List<WargearDTO> wargearDtos = mapper.readValue(
	            file.toFile(),
	            new TypeReference<List<WargearDTO>>() {}
	    );
		return wargearLoader.loadAll(wargearDtos);
	}
	
	public List<StatLine> createStatLines(Path file)  throws IOException{
		List<StatLineDTO> dtos = mapper.readValue(
				file.toFile(),
				new TypeReference<List<StatLineDTO>>(){}
				);
		return statLineLoader.loadAll(dtos);
	}
	
	public List<ModelDescription> createModels(Path file) throws IOException{
		modelDtos = mapper.readValue(
				file.toFile(),
				new TypeReference<List<ModelDTO>>(){}
				);
		return modelLoader.createAll(modelDtos);
	}
	
	public List<ModelDescription> resolveModels(){
		return modelLoader.resolveAllReferences(modelDtos);
	}
	
	public List<Effect> createEffects(Path file) throws IOException {
		effectDtos = mapper.readValue(
				file.toFile(),
				new TypeReference<List<EffectDTO>>(){}
				);
		return effectLoader.createAll(effectDtos);
	}
	
	public List<Effect> resolveEffects(){
		return effectLoader.resolveAllReferences(effectDtos);
	}
	
	
	public List<Requirement> createRequirements (Path file) throws IOException {
		reqDtos = mapper.readValue(
				file.toFile(),
				new TypeReference<List<RequirementDTO>>(){}
				);
		return reqLoader.createAll(reqDtos);
	}
	
	
	public List<Requirement> resolveRequirements(){
		return reqLoader.resolveAllReferences(reqDtos);
	}
	
	public List<OptionChoice> createOptionChoices(Path file) throws IOException {
		choiceDtos = mapper.readValue(
				file.toFile(),
				new TypeReference<List<OptionChoiceDTO>>(){}
				);
		return optionChoiceLoader.createAll(choiceDtos);
	}
	
	public List<OptionChoice> resolveOptionChoices() {
		return optionChoiceLoader.resolveAllReferences(choiceDtos);
	}
	
	public List<OptionGroup> createOptionGroups(Path file) throws IOException {
		groupDtos = mapper.readValue(
				file.toFile(),
				new TypeReference<List<OptionGroupDTO>>(){}
				);
		return optionGroupLoader.createAll(groupDtos);
	}
	
	public List<OptionGroup> resolveOptionGroups(){
		return optionGroupLoader.resolveAllReferences(groupDtos);
	}
	
	public void createUnits(Path file) throws IOException {
		unitDtos =  mapper.readValue(
				file.toFile(),
				new TypeReference<List<UnitDTO>>(){}
				);
		unitLoader.createAll(unitDtos);
	}
	
	public List<UnitDescription> resolveUnits(){
		return unitLoader.resolveAllReferences(unitDtos);
	}
}
