package test;

import builder.ArmyBuilder;
import loaders.CodexLoader;
import roster.Codex;
import roster.Roster;
import units.descriptions.UnitDescription;
import units.instances.UnitInstance;

public class RosterManipulationTest {
	private final static String spaceMarineCodex = 
			"src/main/resources/json/codex space marines";
	
	public RosterManipulationTest() {
	}
	
	public static void main(String[] args) {
		CodexLoader loader = new CodexLoader(spaceMarineCodex);
		ArmyBuilder builder = new ArmyBuilder(loader.loadCodex());
		RosterManipulationTest test = new RosterManipulationTest();
		
		// Add a Tactical Squad to the Roster
		String unitName = "tactical squad";
		test.selectUnitForRoster(unitName, builder);
		//test.displayRoster(builder.getRoster());
		
		// Add a Terminator squad to the roster
		unitName = "terminator squad";
		test.selectUnitForRoster(unitName, builder);
		test.displayRoster(builder.getRoster());
	}
	
	public void selectUnitForRoster(String unitName, ArmyBuilder builder) {
		Roster roster = builder.getRoster();
		Codex codex = builder.getCodex();
		roster.addUnit(codex.getUnit(unitName));
		UnitDescription unitDesc = codex.getUnit(unitName);
		System.out.println(unitDesc.getName() + " successfully loaded");
	}
	
	public void displayRoster(Roster roster) {
		System.out.println("\n***ROSTER IN FULL***\n");
		for (UnitInstance unit : roster.getUnits()) {
			System.out.println(unit.toString());
		}
	}
	
}
