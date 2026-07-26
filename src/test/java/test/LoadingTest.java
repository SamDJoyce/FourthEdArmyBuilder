package test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import loaders.CodexLoader;
import units.ModelFactory;
import units.descriptions.models.ModelDescription;
import units.descriptions.models.StatLine;
import units.descriptions.wargear.WargearDescription;

public class LoadingTest {
	private final static String wargearLoc  = "src/main/java/resources/json/wargear.json";
	private final static String statLineLoc = "src/main/java/resources/json/statlines.json";
	private final static String modelLoc    = "src/main/java/resources/json/models.json";
	private final static String codexLoc    = "src/main/java/resources/json";
	
	public static void main(String[] args) {
		CodexLoader loader = new CodexLoader();
		LoadingTest test = new LoadingTest();
		
//		test.loadGear(loader);
//		test.loadStatLines(loader);
//		test.loadModel(loader);
		test.loadCodex(loader, codexLoc);
	}
	
	private void loadCodex(CodexLoader loader, String codexLocation) {
		loader.loadCodex(codexLoc);
		printModels();
	}
	
	private void loadGear(CodexLoader loader) {
		List<WargearDescription> loadedGear = new ArrayList<>();
		Path wargearFile = Path.of(wargearLoc);
		
		try {
			loadedGear = loader.loadWargear(wargearFile);
			System.out.println("\nGear has been loaded.");
		} catch (Exception e) {
			System.out.println("\nGear loading went wrong");
			System.out.println(e);
		}
		
//		for (WargearDescription w : loadedGear) {
//			System.out.println(w.toString());
//		}
	}
	
	private void loadStatLines(CodexLoader loader) {
		List<StatLine> loadedStats = new ArrayList<>();
		Path statFile = Path.of(statLineLoc);
		
		try {
			loadedStats = loader.loadStatLines(statFile);
			System.out.println("\nStats have been loaded.");
		} catch (Exception e) {
			System.out.println("\nStat loading went wrong");
			System.out.println(e);
		}
//		for (StatLine s : loadedStats) {
//			System.out.println(s.toString());
//		}
	}
	
	private void loadModel(CodexLoader loader) {
		List<ModelDescription> loadedModels = new ArrayList<>();
		Path modelFile = Path.of(modelLoc);
		
		try {
			loadedModels = loader.loadModels(modelFile);
			System.out.println("\nModels have been loaded.");
		} catch (Exception e) {
			System.out.println("\nModel loaded went wrong.");
			System.out.println(e);
		}
		for (ModelDescription model : loadedModels) {
			System.out.println(model);
		}		
	}
	
	private void printModels() {
		for (ModelDescription m : ModelFactory.getRegistry().values()) {
			System.out.println(m);
		}
	}
}

