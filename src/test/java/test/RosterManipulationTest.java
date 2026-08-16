package test;

import builder.ArmyBuilder;
import loaders.CodexLoader;
import roster.Codex;
import roster.Roster;
import units.descriptions.UnitDescription;
import units.instances.ModelInstance;
import units.instances.UnitInstance;
import units.options.OptionChoice;

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
		test.selectOptionForUnit(
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
		test.selectOptionForModel(
				builder.getRoster(), 
				modelId, 
				choice);
		test.displayRoster(builder.getRoster());
	}
	
	public void selectOptionForUnit(
			Roster roster,
			String unitId,
			OptionChoice choice) {
		UnitInstance unit = roster.getUnitById(unitId);
		unit.addSelection(choice);
		System.out.println(String.format(
				"Option '%s' selected for %s",
				choice.getName(),
				unit.getName()
				));
	}
	
	public void selectOptionForModel(
			Roster roster,
			String modelId,
			OptionChoice choice) {
		ModelInstance model = roster.getModelById(modelId);
		model.addSelection(choice);
		System.out.println(String.format(
				"Option '%s' selected for %s",
				choice.getName(),
				model.getName()
				));
	}
	
	public String selectUnitForRoster(String unitName, ArmyBuilder builder) {
		Roster roster = builder.getRoster();
		Codex codex = builder.getCodex();
		String unitId = roster.addUnit(codex.getUnit(unitName));
		UnitDescription unitDesc = codex.getUnit(unitName);
		System.out.println(String.format(
				"'%s' selected for roster",
				roster.getUnitById(unitId).getName()));
		return unitId;
	}
	
	public void displayRoster(Roster roster) {
		System.out.println("\n***ROSTER IN FULL***\n");
		for (UnitInstance unit : roster.getUnits()) {
			System.out.println(unit.toString());
		}
	}
	
}
