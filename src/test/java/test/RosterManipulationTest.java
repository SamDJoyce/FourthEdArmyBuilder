package test;

import builder.ArmyBuilder;
import loaders.CodexLoader;
import roster.Codex;
import roster.Roster;
import roster.ValidationResult;
import units.UnitFactory;
import units.descriptions.UnitDescription;
import units.instances.UnitInstance;
import units.options.OptionChoice;
import units.options.OptionOwner;

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
		String tacSquadId = test.selectUnitForRoster(unitName, builder);
		//test.displayRoster(builder.getRoster());
		
		// Add a Terminator squad to the roster
//		unitName = "terminator squad";
//		String terminatorSquadId = test.selectUnitForRoster(unitName, builder);
//		test.displayRoster(builder.getRoster());
		
		// Get choice for unit from Codex
		OptionChoice choice = builder.getCodex().getChoice(
				"select add tactical marine");
		test.selectOption(
				builder.getRoster(),
				tacSquadId,
				choice);
		test.displayRoster(builder.getRoster());
		
		// Get choice for model from codex
		choice = builder.getCodex().getChoice(
				"select plasma");
		String modelId = builder.getRoster()
								.getUnitById(tacSquadId)
								.getModels()
								.get(2)
								.getId();
		test.selectOption(
				builder.getRoster(), 
				modelId, 
				choice);
		test.displayRoster(builder.getRoster());
	}
	
	public void selectOption(			
			Roster roster,
			String unitId,
			OptionChoice choice) {
		
		OptionOwner owner = roster.getOwnerById(unitId);
		ValidationResult result = roster.selectOption(owner, choice);
		if (result.isValid()) {
			System.out.println(String.format(
					"Option '%s' selected for %s",
					choice.getName(),
					owner.getName()
					));
			return;
		}
		System.out.println(String.format(
				"Selection Error: %s", 
				result.getMessage()));
	}
	
	public String selectUnitForRoster(String unitName, ArmyBuilder builder) {
		Roster roster = builder.getRoster();
		Codex codex = builder.getCodex();
		UnitInstance instance  = UnitFactory.createInstance(codex.getUnit(unitName));
		roster.addUnit(instance);
		UnitDescription unitDesc = codex.getUnit(unitName);
		System.out.println(String.format(
				"'%s' added to roster",
				instance.getName()));
		return instance.getId();
	}
	
	public void displayRoster(Roster roster) {
		System.out.println("\n***ROSTER IN FULL***\n");
		for (UnitInstance unit : roster.getUnits()) {
			System.out.println(unit.toString());
		}
	}
	
}
