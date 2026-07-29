package test;

import loaders.CodexLoader;
import units.UnitFactory;
import units.descriptions.UnitDescription;

public class LoadingTest {
//	private final static String wargearLoc  = "src/main/resources/json/wargear.json";
//	private final static String statLineLoc = "src/main/resources/json/statlines.json";
//	private final static String modelLoc    = "src/main/resources/json/models.json";
	private final static String codexLoc    = "src/main/resources/json";
	
	public static void main(String[] args) {
		CodexLoader loader = new CodexLoader(codexLoc);
		LoadingTest test = new LoadingTest();
		
		loader.loadCodex();
		test.printUnitInfo();
	}
	
	private void printUnitInfo() {
		for (UnitDescription u : UnitFactory.getRegistry().values()) {
			System.out.println(u);
		}
	}
}

