package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import units.UnitRole;
import units.UnitType;
import units.WargearType;
import units.descriptions.UnitDescription;
import units.descriptions.models.ModelDescription;
import units.descriptions.models.StatLine;
import units.descriptions.models.StatLineFactory;
import units.descriptions.wargear.WargearDescription;
import units.instances.ModelInstance;
import units.instances.UnitInstance;
import units.instances.WargearInstance;
import units.options.OptionChoice;
import units.options.OptionGroup;
import units.options.effects.Effect;
import units.options.effects.EffectFactory;
import units.options.requirements.ReqFactory;
import units.options.requirements.Requirement;
import units.options.requirements.RequirementResult;

public class UnitExampleTest {

	// Test container class for unit loading
	// Not actual unit tests
	
	private static final String SQUAD_NAME = "Tactical Squad";
	private static final String MARINE_NAME = "Tactical Marine";
	private static final String MARINE_SRG_NAME = "Marine Sergeant";
	private static final String BOLTER_NAME = "Bolter";
	private static final String ARMOR_NAME = "Power Armor";
	private static final String PLASMA_GUN_NAME = "Plasma gun";
	private static final String FLAMER_NAME = "Flamer";
	private static final String MELTA_NAME = "Melta gun";
	
	private static final WargearType BOLTER_TYPE = WargearType.TWO_HANDED;
	private static final WargearType PLASMA_GUN_TYPE = WargearType.TWO_HANDED;
	private static final WargearType FLAMER_TYPE = WargearType.TWO_HANDED;
	private static final WargearType MELTA_TYPE = WargearType.TWO_HANDED;
	private static final WargearType ARMOR_TYPE = WargearType.GEAR;
	private static final UnitRole    TROOPS = UnitRole.TROOPS;
	
	private static final int MARINE_POINTS = 15;
	private static final int PLASMA_GUN_POINTS = 10;
	private static final int MELTA_POINTS = 10;
	private static final int FLAMER_POINTS = 6;
	private static final int TAC_SQUAD_MIN = 5;
	private static final int TAC_SQUAD_MAX = 10;
	private static final int COUNT_MARINES = 4;
	private static final int COUNT_SRG = 1;
	private static final UnitType INFANTRY = UnitType.INFANTRY;
	private static final UnitType CHARACTER = UnitType.CHARACTER;
	
	// Unit Stats and types
	private final StatLine MARINE_STATS;
	private final StatLine MARINE_SRG_STATS;
	private final Set<UnitType> TACMARINE_TYPES;
	private final Set<UnitType> SERGEANT_TYPES;
	
	// Wargear Descriptions
	private final WargearDescription BOLTER;
	private final WargearDescription PLASMA_GUN;
	private final WargearDescription FLAMER;
	private final WargearDescription MELTA_GUN;
	private final WargearDescription POWER_ARMOR;
	private final Set<WargearDescription> TACMARINE_GEAR;
	
	// Options and OptionGroups
	private final OptionGroup SPECIAL_WEAPONS_GROUP;
	private final List<OptionGroup> OPTION_GROUPS;
	private final OptionChoice PLASMA_CHOICE;
	private final OptionChoice MELTA_CHOICE;
	private final OptionChoice FLAMER_CHOICE;
	
	//Effects
	private final Effect REPLACE_BOLTER_W_PLASMA;
	private final Effect REPLACE_BOLTER_W_MELTA;
	private final Effect REPLACE_BOLTER_W_FLAMER;
	
	// Requirements
	private final Requirement MUST_HAVE_BOLTER;
	private final Requirement EXCLUSIVE_OF_FLAMER_MELTA;
	private final Requirement EXCLUSIVE_OF_FLAMER_PLASMA;
	private final Requirement EXCLUSIVE_OF_MELTA_PLASMA;
	
	// Constructor
	public UnitExampleTest() {
		
		TACMARINE_TYPES = Set.of(INFANTRY);
		SERGEANT_TYPES = Set.of(INFANTRY,CHARACTER);
		
		MARINE_STATS = StatLineFactory.get(MARINE_NAME,4, 4, 4,4, 1, 4, 1, 8, 3);
		MARINE_SRG_STATS = StatLineFactory.get(MARINE_SRG_NAME,4, 4, 4,4, 1, 4, 1, 8, 3);
		
		BOLTER 	    = WargearDescription.get(BOLTER_NAME,BOLTER_TYPE);
		PLASMA_GUN  = WargearDescription.get(PLASMA_GUN_NAME,PLASMA_GUN_TYPE, PLASMA_GUN_POINTS);
		FLAMER      = WargearDescription.get(FLAMER_NAME,FLAMER_TYPE, FLAMER_POINTS);
		MELTA_GUN   = WargearDescription.get(MELTA_NAME, MELTA_TYPE, MELTA_POINTS);
		POWER_ARMOR = WargearDescription.get(ARMOR_NAME, ARMOR_TYPE);
		TACMARINE_GEAR = Set.of(BOLTER,POWER_ARMOR);
	
		// Option Choices
		PLASMA_CHOICE = new OptionChoice(
							PLASMA_GUN_NAME,
							PLASMA_GUN_POINTS
							);
		MELTA_CHOICE  = new OptionChoice(
							MELTA_NAME,
							MELTA_POINTS
							);
		FLAMER_CHOICE = new OptionChoice(
							FLAMER_NAME,
							FLAMER_POINTS);
		
		// Effects
		REPLACE_BOLTER_W_PLASMA = EffectFactory.replaceWargear(BOLTER, PLASMA_GUN);
		REPLACE_BOLTER_W_MELTA  = EffectFactory.replaceWargear(BOLTER, MELTA_GUN);
		REPLACE_BOLTER_W_FLAMER = EffectFactory.replaceWargear(BOLTER, FLAMER);
		
		// Requirements
		MUST_HAVE_BOLTER = ReqFactory.mustHaveGear(BOLTER);
		EXCLUSIVE_OF_FLAMER_MELTA  = ReqFactory.mutualExclusion(List.of(
										MELTA_CHOICE,
										FLAMER_CHOICE
										)); //exclusiveOfFlamerMeltaReq
		EXCLUSIVE_OF_FLAMER_PLASMA = ReqFactory.mutualExclusion(List.of(
										PLASMA_CHOICE,
										FLAMER_CHOICE
										));
		EXCLUSIVE_OF_MELTA_PLASMA  = ReqFactory.mutualExclusion(List.of(
										PLASMA_CHOICE,
										MELTA_CHOICE
										));
		// Assign Requirements and Effects to Option Choices
		PLASMA_CHOICE.setRequirements(List.of(
										MUST_HAVE_BOLTER,
										EXCLUSIVE_OF_FLAMER_MELTA));
		PLASMA_CHOICE.setEffects(List.of(REPLACE_BOLTER_W_PLASMA));
		
		MELTA_CHOICE.setRequirements(List.of(
										MUST_HAVE_BOLTER,
										EXCLUSIVE_OF_FLAMER_PLASMA));
		MELTA_CHOICE.setEffects(List.of(REPLACE_BOLTER_W_MELTA));
		
		FLAMER_CHOICE.setRequirements(List.of(
										MUST_HAVE_BOLTER,
										EXCLUSIVE_OF_MELTA_PLASMA));
		FLAMER_CHOICE.setEffects(List.of(REPLACE_BOLTER_W_FLAMER));
		
		// Assemble the Choices into an Option Group
		SPECIAL_WEAPONS_GROUP = new OptionGroup(
									"Special weapons",
									List.of(PLASMA_CHOICE,
											MELTA_CHOICE,
											FLAMER_CHOICE),
									null,
									0,
									1
									);
		
		// Assign the Special Weapons Group to the list of Option Groups
		OPTION_GROUPS = new ArrayList<>(List.of(SPECIAL_WEAPONS_GROUP));
	}
	
	// MAIN
	public static void main(String[] args) {
		UnitExampleTest test = new UnitExampleTest();
		
		UnitDescription tacticalSquadDesc = test.assembleTacticalSquadDescription();
		UnitInstance    tacSquad = new UnitInstance(tacticalSquadDesc);
		// Display the Tactical Squad's information
		test.printUnitInfo(tacSquad);
		test.printModelInfo(tacSquad);
		test.printUnitOptionGroups(tacSquad);
		// Select and option - Plasma Gun
		test.selectPlasmaGun(tacSquad);
		test.printModelInfo(tacSquad);
	}
	
	public void selectPlasmaGun(UnitInstance unit) {
		ModelInstance model = unit.getModels().get(0);
		RequirementResult result = model.addSelection(PLASMA_CHOICE);
		if (result.isValid()) {
			System.out.println("\n--- Plasma Gun selected ---\n");
		} else {
			System.out.println("\n" + result.getMessage() + "\n");
		}
	}
	
	// Construct ModelDescriptions as prototypes
	
	public ModelDescription createSpaceMarine() {
		return new ModelDescription(
					MARINE_NAME,
					MARINE_POINTS,
					MARINE_STATS,
					TACMARINE_TYPES,
					TACMARINE_GEAR
					);
	}
	
	public ModelDescription createSpaceMarineSrg() {
		return new ModelDescription(
					MARINE_SRG_NAME,
					MARINE_POINTS,
					MARINE_SRG_STATS,
					SERGEANT_TYPES,
					TACMARINE_GEAR
					);
	}
	
	// Construct the minimum Tactical Squad from prototypes
	public UnitDescription assembleTacticalSquadDescription() {
		
		ModelDescription marine   = createSpaceMarine();
		ModelDescription sergeant = createSpaceMarineSrg();
		List<ModelDescription> models = new ArrayList<>();
		
		// Add Marines
		for (int i = 0 ; i < COUNT_MARINES ; i++) {
			models.add(marine);
		}
		// Add Sergeant
		for (int i = 0 ; i < COUNT_SRG ; i++) {
			models.add(sergeant);
		}
		
		return new UnitDescription(
					SQUAD_NAME,
					TAC_SQUAD_MIN,
					TAC_SQUAD_MAX,
					TROOPS,
					OPTION_GROUPS,
					models
					);
	}
	
	// Print out the info for a UnitInstance
	public void printUnitInfo(UnitInstance tacSquad) {
		System.out.println(tacSquad.getName());
		System.out.printf("Unit contains %d models\n",tacSquad.getCurrentSize(), MARINE_NAME);
		System.out.println("This unit has the following types: ");
		// Print out all types
		for (UnitType t : tacSquad.getTypes()) {
			System.out.println(t);
		}
		System.out.println("");
	}
	
	// Print out info for each modelInstance in a given UnitInstance
	public void printModelInfo(UnitInstance tacSquad) {
		// Print out each model and its stats
		for (ModelInstance model : tacSquad.getModels()) {
			System.out.println(model.getName());
			System.out.println(model.getStats().statsToString());
			// Print out wargear for each marine to really show everything is in there
			System.out.println("Equipped with:");	
			for (WargearInstance gear : model.getGear()) {
				System.out.println(gear.getName());
			}
			System.out.println("");
		}
	}
	
	public void printUnitOptionGroups(UnitInstance tacSquad) {
		System.out.println("Option Groups:\n");
		for (OptionGroup og : tacSquad.getOptions()) {
			System.out.println(og.getName() + ":");
			for (OptionChoice c : og.getChoices()) {
				System.out.println(" - Choice: " + c.getName());
			}
		}
	}

}
