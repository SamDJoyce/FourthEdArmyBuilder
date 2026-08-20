package loaders;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import roster.Codex;
import units.ModelFactory;
import units.UnitFactory;
import units.WargearFactory;
import units.descriptions.UnitDescription;
import units.descriptions.models.ModelDescription;
import units.descriptions.models.StatLine;
import units.descriptions.models.StatLineFactory;
import units.descriptions.wargear.WargearDescription;
import units.options.OptionChoice;
import units.options.OptionChoiceFactory;
import units.options.OptionGroup;
import units.options.OptionGroupFactory;
import units.options.effects.Effect;
import units.options.effects.EffectFactory;
import units.options.requirements.ReqFactory;
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
	
	private final String codexResourcePath;
	private final String wargearFile;
	private final String statLineFile;
	private final String modelFile;
	private final String effectFile;
	private final String requirementFile;
	private final String optionChoiceFile;
	private final String optionGroupFile;
	private final String unitFile;
	
	private List<ModelDTO> 	      modelDtos;
	private List<EffectDTO> 	  effectDtos;
	private List<RequirementDTO>  reqDtos;
	private List<OptionChoiceDTO> choiceDtos;
	private List<OptionGroupDTO>  groupDtos;
	private List<UnitDTO> 		  unitDtos;
	
	public CodexLoader(String codexResourcePath) {
		wargearLoader 	   = LoaderFactory.forWargear();
		statLineLoader 	   = LoaderFactory.forStats();
		modelLoader 	   = LoaderFactory.forModels();
		unitLoader 		   = LoaderFactory.forUnits();
		reqLoader 		   = LoaderFactory.forReqs();
		effectLoader 	   = LoaderFactory.forEffects();
		optionGroupLoader  = LoaderFactory.forOptionGroups();
		optionChoiceLoader = LoaderFactory.forChoices();
		mapper 			   = new ObjectMapper();
		
		this.codexResourcePath  = codexResourcePath;
		wargearFile  	  = resource("wargear.json");
		statLineFile 	  = resource("statlines.json");
		modelFile 	  	  = resource("models.json");
		effectFile   	  = resource("effects.json");
		requirementFile   = resource("requirements.json");
		optionChoiceFile  = resource("optionChoices.json");
		optionGroupFile   = resource("optionGroups.json");
		unitFile		  = resource("units.json");
	}
	
	private String resource(String fileName) {
		return codexResourcePath + "/" + fileName;
	}
	
	public Codex loadCodex(){
		try {
			// Load objects from file to create placeholders
			createObjectsFromFiles();
			// Populate objects by resolving references
			resolveObjectReferences();
			// return assembled codex and clear everything 
			// in preparation for loading next codex
			return assembleCodex();
		} catch (IOException e) {
			throw new RuntimeException(
					"Failed to load codex: " + codexResourcePath,
					e
					);
		}
	}
	
	private Codex assembleCodex() {
		String name = getFolderName(codexResourcePath);
		Map<String, StatLine> statLines = 
				new HashMap<String, StatLine>(StatLineFactory.getRegistry()) ;
		Map<String, Effect> effects = 
				new HashMap<String, Effect>(EffectFactory.getRegistry());
		Map<String, Requirement> requirements = 
				new HashMap<String, Requirement>(ReqFactory.getRegistry());
		Map<String, OptionChoice> choices = 
				new HashMap<String, OptionChoice>(OptionChoiceFactory.getRegistry());
		Map<String, OptionGroup> groups = 
				new HashMap<String, OptionGroup>(OptionGroupFactory.getRegistry());
		Map<String, ModelDescription>models = 
				new HashMap<String, ModelDescription>(ModelFactory.getRegistry());
		Map<String, UnitDescription> units = 
				new HashMap<String, UnitDescription>(UnitFactory.getRegistry());
		Map<String, WargearDescription> gear = 
				new HashMap<String, WargearDescription>(WargearFactory.getRegistry());
		clearRegistries();
		clearDTOs();
		return new Codex(
				name,
				gear,
				statLines,
				effects,
				requirements,
				choices,
				groups,
				models,
				units
				);
	}
	
	private void createObjectsFromFiles() throws IOException{
			createWargear();
			createStatLines();
			createEffects();
			createRequirements();
			createOptionChoices();
			createOptionGroups();
			createModels();
			createUnits();
	}
	
	private void resolveObjectReferences() throws IOException {
			resolveEffects();
			resolveRequirements();
			resolveOptionChoices();
			resolveOptionGroups();
			resolveModels();
			resolveUnits();
	}
	
	private void clearRegistries() {
		WargearFactory.clearRegistry();
		StatLineFactory.clearRegistry();
		EffectFactory.clearRegistry();
		ReqFactory.clearRegistry();
		OptionChoiceFactory.clearRegistry();
		OptionGroupFactory.clearRegistry();
		ModelFactory.clearRegistry();
		UnitFactory.clearRegistry();
	}
	
	// **************************
	// ***** Create Methods *****
	// **************************
	
	public List<WargearDescription> createWargear() throws IOException {

		InputStream input =
            ResourceLoader.getResource(wargearFile);

        List<WargearDTO> dtos = mapper.readValue(
            input,
            new TypeReference<List<WargearDTO>>() {}
        );

        return wargearLoader.loadAll(dtos);
	}
	
	public List<StatLine> createStatLines()  throws IOException{
		InputStream input =
            ResourceLoader.getResource(statLineFile);
		List<StatLineDTO> dtos = mapper.readValue(
				input, 
				new TypeReference<List<StatLineDTO>>() {});
		return statLineLoader.loadAll(dtos);
	}
	
	public List<ModelDescription> createModels() throws IOException{
		InputStream input =
            ResourceLoader.getResource(modelFile);
		modelDtos = mapper.readValue(
				input, 
				new TypeReference<List<ModelDTO>>() {});
		return modelLoader.createAll(modelDtos);
	}
	
	public List<Effect> createEffects() throws IOException {
		InputStream input =
            ResourceLoader.getResource(effectFile);
		effectDtos = mapper.readValue(
				input,
				new TypeReference<List<EffectDTO>>() {});
		return effectLoader.createAll(effectDtos);
	}

	public List<Requirement> createRequirements () throws IOException {
		InputStream input =
	            ResourceLoader.getResource(requirementFile);
		reqDtos = mapper.readValue(
				input, 
				new TypeReference<List<RequirementDTO>>() {});
		return reqLoader.createAll(reqDtos);
	}
	
	public List<OptionChoice> createOptionChoices() throws IOException {
		InputStream input =
	            ResourceLoader.getResource(optionChoiceFile);
		choiceDtos = mapper.readValue(
				input, 
				new TypeReference<List<OptionChoiceDTO>>() {});
		return optionChoiceLoader.createAll(choiceDtos);
	}
	
	public List<OptionGroup> createOptionGroups() throws IOException {
		InputStream input =
            ResourceLoader.getResource(optionGroupFile);
		groupDtos = mapper.readValue(
				input, 
				new TypeReference<List<OptionGroupDTO>>() {});
		return optionGroupLoader.createAll(groupDtos);
	}
	
	public List<UnitDescription> createUnits() throws IOException {
		InputStream input =
            ResourceLoader.getResource(unitFile);
		unitDtos = mapper.readValue(
				input, 
				new TypeReference<List<UnitDTO>>() {});
		return unitLoader.createAll(unitDtos);
	}
	
	// ***************************
	// ***** Resolve Methods *****
	// ***************************
	
	public List<ModelDescription> resolveModels(){
		return modelLoader.resolveAllReferences(modelDtos);
	}
	
	public List<Effect> resolveEffects(){
		return effectLoader.resolveAllReferences(effectDtos);
	}
	
	public List<Requirement> resolveRequirements(){
		return reqLoader.resolveAllReferences(reqDtos);
	}
	
	public List<OptionChoice> resolveOptionChoices() {
		return optionChoiceLoader.resolveAllReferences(choiceDtos);
	}
	

	public List<OptionGroup> resolveOptionGroups(){
		return optionGroupLoader.resolveAllReferences(groupDtos);
	}
	
	public List<UnitDescription> resolveUnits(){
		return unitLoader.resolveAllReferences(unitDtos);
	}
	
	private String getFolderName(String path) {
	    int lastSlash = path.lastIndexOf('/');
	    if (lastSlash == -1) {
	        return path;
	    }
	    return path.substring(lastSlash + 1);
	}
	
	private void clearDTOs() {
	    modelDtos = null;
	    effectDtos = null;
	    reqDtos = null;
	    choiceDtos = null;
	    groupDtos = null;
	    unitDtos = null;
	}
}
