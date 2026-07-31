package test;

import loaders.CodexLoader;
import units.UnitFactory;
import units.descriptions.UnitDescription;
import units.instances.UnitInstance;
import units.options.OptionChoice;
import units.options.OptionChoiceFactory;

public class LoadingTest {
//	private final static String wargearLoc  = "src/main/resources/json/wargear.json";
//	private final static String statLineLoc = "src/main/resources/json/statlines.json";
//	private final static String modelLoc    = "src/main/resources/json/models.json";
	private final static String codexLoc    = "src/main/resources/json/codex space marines";

	
	public static void main(String[] args) {
		// Load data from files
		CodexLoader loader = new CodexLoader(codexLoc);
		loader.loadCodex();
		// Create a unit description from loaded data
		UnitDescription u = UnitFactory.get("tactical squad");
		System.out.println(u);
		// Create a unit instance where options can be selected
		UnitInstance unit = UnitFactory.getInstance(u);
		System.out.println("Tactical Squad has " + unit.getCurrentSize() + " models");
		System.out.println(String.format(
				"Squad Points Cost: %d", 
				unit.getTotalPoints()) );
		// Create and select the 'add marine choice'
		OptionChoice addMarine = OptionChoiceFactory.get("select add tactical marine");
		System.out.println("Select option to add a marine");
		unit.addSelection(addMarine);
		System.out.println("Tactical Squad has " + unit.getCurrentSize() + " models");
		System.out.println(String.format(
				"Squad Points Cost: %d", 
				unit.getTotalPoints()) );
		System.out.println("\n");
		// Create and select frag grenades for the squad
		OptionChoice addFrag = OptionChoiceFactory.get("select frag grenades for squad");
		unit.addSelection(addFrag);
		System.out.println(unit);
		System.out.println(String.format(
				"Squad Points Cost: %d", 
				unit.getTotalPoints()) );
		// Create and select heavy bolter
		OptionChoice addHB = OptionChoiceFactory.get("select heavy bolter");
		unit.getModels().get(2).addSelection(addHB);
		System.out.println(unit);
		System.out.println(String.format(
				"Squad Points Cost: %d", 
				unit.getTotalPoints()) );
	}
	
}

