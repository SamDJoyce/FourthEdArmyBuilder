package test;

import builder.ArmyBuilder;
import loaders.CodexLoader;
import units.UnitFactory;
import units.descriptions.UnitDescription;
import units.instances.UnitInstance;
import units.options.OptionChoice;

public class LoadingTest {
	
	private final static String spaceMarineCodex = "src/main/resources/json/codex space marines";
	
	public static void main(String[] args) {
		LoadingTest test = new LoadingTest();
		ArmyBuilder army = test.loadData();
		// Create a unit description from loaded data
		UnitDescription description = test.createDescription(army);
		// Create a unit instance where options can be selected
		UnitInstance instance = test.createInstance(army, description);
		// Create and select the 'add marine choice'
		test.createAndAssignAddMarineChoice(army, instance);
		// Create and select frag grenades for the squad
		test.createAndAssignAddFragGrenades(army, instance);
		// Create and select heavy bolter
		test.createAndAssignHeavyBolter(army, instance);
	}
	
	private ArmyBuilder loadData() {
		// Load data from files
		CodexLoader loader = new CodexLoader(spaceMarineCodex);
		ArmyBuilder army = new ArmyBuilder(loader.loadCodex());
		System.out.println(army.getCodex().getName());
		return army;
	}
	
	private UnitDescription createDescription(ArmyBuilder army) {
		UnitDescription u = army.getCodex().getUnit("tactical squad");
		System.out.println(u);
		return u;
	}
	
	private UnitInstance createInstance(ArmyBuilder army, UnitDescription description) {
		UnitInstance instance = UnitFactory.createInstance(description);
		army.getRoster().addUnit(instance);
		System.out.println("Tactical Squad has " + army.getRoster().getUnits().getFirst() + " models");
		System.out.println(String.format(
				"Squad Points Cost: %d", 
				army.getRoster().getCurrentPoints()) );
		return instance;
	}
	
	private void createAndAssignAddMarineChoice(ArmyBuilder army, UnitInstance instance) {
		OptionChoice addMarine = army.getCodex().getChoice("select add tactical marine");
		System.out.println("Select option to add a marine");
		instance.addSelection(addMarine);
		System.out.printf(
				"Tactical Squad has %d models\n", 
				instance.getCurrentSize());
		System.out.println(String.format(
				"Squad Points Cost: %d", 
				instance.getTotalPoints()) );
		System.out.println("\n");
	}
	
	private void createAndAssignAddFragGrenades(ArmyBuilder army, UnitInstance instance) {
		OptionChoice addFrag = army.getCodex().getChoice("select frag grenades for squad");
		instance.addSelection(addFrag);
		System.out.println(instance);
		System.out.println(String.format(
				"Squad Points Cost: %d", 
				army.getRoster().getCurrentPoints()) );
		
	}
	private void createAndAssignHeavyBolter(ArmyBuilder army, UnitInstance instance) {
		OptionChoice addHB = army.getCodex().getChoice("select heavy bolter");
		instance.getModels().get(2).addSelection(addHB);
		System.out.println(instance);
		System.out.println(String.format(
				"Squad Points Cost: %d", 
				army.getRoster().getCurrentPoints()) );
	}
	
	private void displayRoster() {
		
	}
	
}

