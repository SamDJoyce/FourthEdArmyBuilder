package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import units.ModelFactory;
import units.UnitFactory;
import units.UnitRole;
import units.UnitType;
import units.WargearFactory;
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
	
	private static final String SQUAD_NAME 		 = "Tactical Squad";
	private static final String MARINE_NAME 	 = "Tactical Marine";
	private static final String MARINE_SGT_NAME  = "Marine Sergeant";
	private static final String VETERAN_SGT_NAME = "Veterant Sergeant";
	private static final String BOLTER_NAME 	 = "Bolter";
	private static final String ARMOR_NAME 		 = "Power Armor";
	private static final String PLASMA_GUN_NAME  = "Plasma gun";
	private static final String FLAMER_NAME 	 = "Flamer";
	private static final String MELTA_NAME 		 = "Melta gun";
	private static final String CRUX_NAME 		 = "Crux Terminatus";
	private static final String SPECIAL_WEAPONS  = "Special Weapons";
	
	private static final WargearType BOLTER_TYPE 	 = WargearType.TWO_HANDED;
	private static final WargearType PLASMA_GUN_TYPE = WargearType.TWO_HANDED;
	private static final WargearType FLAMER_TYPE 	 = WargearType.TWO_HANDED;
	private static final WargearType MELTA_TYPE 	 = WargearType.TWO_HANDED;
	private static final WargearType GEAR 			 = WargearType.GEAR;
	private static final UnitRole    TROOPS 		 = UnitRole.TROOPS;
	
	private static final int MARINE_POINTS 		= 15;
	private static final int PLASMA_GUN_POINTS 	= 10;
	private static final int MELTA_POINTS 		= 10;
	private static final int FLAMER_POINTS 		= 6;
	private static final int CRUX_POINTS 		= 15;
	private static final int TAC_SQUAD_MIN 		= 5;
	private static final int TAC_SQUAD_MAX 		= 10;
	private static final int COUNT_MARINES 		= 4;
	private static final int COUNT_SRG 			= 1;
	private static final UnitType INFANTRY  = UnitType.INFANTRY;
	private static final UnitType CHARACTER = UnitType.CHARACTER;
	
	// Unit Stats and types
	private final StatLine MARINE_STATS;
	private final StatLine MARINE_SGT_STATS;
	private final Set<UnitType> TACMARINE_TYPES;
	private final Set<UnitType> SERGEANT_TYPES;
	
	// Wargear Descriptions
	private final WargearDescription BOLTER;
	private final WargearDescription PLASMA_GUN;
	private final WargearDescription FLAMER;
	private final WargearDescription MELTA_GUN;
	private final WargearDescription POWER_ARMOR;
	private final WargearDescription CRUX_TERMINATUS;
	private final Set<WargearDescription> TACMARINE_GEAR;
	
	// Options and OptionGroups
	private final OptionGroup SPECIAL_WEAPONS_GROUP;
	private final OptionGroup CRUX_TERMINATUS_GROUP;
	private final List<OptionGroup> OPTION_GROUPS;
	private final OptionChoice PLASMA_CHOICE;
	private final OptionChoice MELTA_CHOICE;
	private final OptionChoice FLAMER_CHOICE;
	private final OptionChoice CRUX_CHOICE;
	
	//Effects
	private final Effect REPLACE_BOLTER_W_PLASMA;
	private final Effect REPLACE_BOLTER_W_MELTA;
	private final Effect REPLACE_BOLTER_W_FLAMER;
	private final Effect INCREASE_LEADERSHIP;
	private final Effect INCREASE_ATTACKS;
	private final Effect ADD_CRUX;
	private final Effect CHANGE_TO_VET_SGT;
	
	// Requirements
	private final Requirement MUST_HAVE_BOLTER;
	private final Requirement EXCLUSIVE_OF_FLAMER_MELTA;
	private final Requirement EXCLUSIVE_OF_FLAMER_PLASMA;
	private final Requirement EXCLUSIVE_OF_MELTA_PLASMA;
	private final Requirement CHARACTER_ONLY;
	
	// Constructor
	public UnitExampleTest() {
		
		TACMARINE_TYPES = Set.of(INFANTRY);
		SERGEANT_TYPES  = Set.of(INFANTRY,CHARACTER);
		
		MARINE_STATS     = StatLineFactory.forInfantry(MARINE_NAME,4, 4, 4,4, 1, 4, 1, 8, 3);
		MARINE_SGT_STATS = StatLineFactory.forInfantry(MARINE_SGT_NAME,4, 4, 4,4, 1, 4, 1, 8, 3);
		
		BOLTER 	    = WargearFactory.getDescription(BOLTER_NAME,BOLTER_TYPE);
		PLASMA_GUN  = WargearFactory.getDescription(PLASMA_GUN_NAME,PLASMA_GUN_TYPE, PLASMA_GUN_POINTS);
		FLAMER      = WargearFactory.getDescription(FLAMER_NAME,FLAMER_TYPE, FLAMER_POINTS);
		MELTA_GUN   = WargearFactory.getDescription(MELTA_NAME, MELTA_TYPE, MELTA_POINTS);
		POWER_ARMOR = WargearFactory.getDescription(ARMOR_NAME, GEAR);
		CRUX_TERMINATUS = WargearFactory.getDescription(CRUX_NAME, GEAR);
		TACMARINE_GEAR  = Set.of(BOLTER,POWER_ARMOR);
	
		// Option Choices
		PLASMA_CHOICE = OptionChoice.get(
							PLASMA_GUN_NAME,
							PLASMA_GUN_POINTS);
		MELTA_CHOICE  = OptionChoice.get(
							MELTA_NAME,
							MELTA_POINTS);
		FLAMER_CHOICE = OptionChoice.get(
							FLAMER_NAME,
							FLAMER_POINTS);
		CRUX_CHOICE   = OptionChoice.get(
							CRUX_NAME,
							CRUX_POINTS);
		
		// Effects
		REPLACE_BOLTER_W_PLASMA = EffectFactory.replaceWargear("REPLACE_BOLTER_W_PLASMA",BOLTER, PLASMA_GUN);
		REPLACE_BOLTER_W_MELTA  = EffectFactory.replaceWargear("REPLACE_BOLTER_W_MELTA",BOLTER, MELTA_GUN);
		REPLACE_BOLTER_W_FLAMER = EffectFactory.replaceWargear("REPLACE_BOLTER_W_FLAMER",BOLTER, FLAMER);
		INCREASE_LEADERSHIP     = EffectFactory.modifyStat("INCREASE_LEADERSHIP","ld", 1);
		INCREASE_ATTACKS		= EffectFactory.modifyStat("INCREASE_ATTACKS","a", 1);
		ADD_CRUX				= EffectFactory.addWargear("ADD_CRUX",CRUX_TERMINATUS);
		CHANGE_TO_VET_SGT       = EffectFactory.changeModelName("CHANGE_TO_VET_SGT",VETERAN_SGT_NAME);
		
		// Requirements
		MUST_HAVE_BOLTER = ReqFactory.mustHaveGear("",BOLTER);
		EXCLUSIVE_OF_FLAMER_MELTA  = ReqFactory.mutualExclusion("",List.of(
										MELTA_CHOICE,
										FLAMER_CHOICE
										)); //exclusiveOfFlamerMeltaReq
		EXCLUSIVE_OF_FLAMER_PLASMA = ReqFactory.mutualExclusion("",List.of(
										PLASMA_CHOICE,
										FLAMER_CHOICE
										));
		EXCLUSIVE_OF_MELTA_PLASMA  = ReqFactory.mutualExclusion("",List.of(
										PLASMA_CHOICE,
										MELTA_CHOICE
										));
		CHARACTER_ONLY			   = ReqFactory.charactersOnly("");
		
		// Assign Requirements and Effects to Option Choices
			//Plasma
		PLASMA_CHOICE.setRequirements(List.of(
									MUST_HAVE_BOLTER,
									EXCLUSIVE_OF_FLAMER_MELTA));
		PLASMA_CHOICE.setEffects(List.of(REPLACE_BOLTER_W_PLASMA));
			//Melta
		MELTA_CHOICE.setRequirements(List.of(
									MUST_HAVE_BOLTER,
									EXCLUSIVE_OF_FLAMER_PLASMA));
		MELTA_CHOICE.setEffects(List.of(REPLACE_BOLTER_W_MELTA));
			// Flamer
		FLAMER_CHOICE.setRequirements(List.of(
									MUST_HAVE_BOLTER,
									EXCLUSIVE_OF_MELTA_PLASMA));
		FLAMER_CHOICE.setEffects(List.of(REPLACE_BOLTER_W_FLAMER));
			// Crux
		CRUX_CHOICE.setEffects(List.of(
									INCREASE_LEADERSHIP,
									INCREASE_ATTACKS,
									ADD_CRUX,
									CHANGE_TO_VET_SGT));
		CRUX_CHOICE.setRequirements(List.of(CHARACTER_ONLY));
		
		// Assemble the Choices into the Special Weapons Option Group
		SPECIAL_WEAPONS_GROUP = OptionGroup.get(
									SPECIAL_WEAPONS,
									List.of(PLASMA_CHOICE,
											MELTA_CHOICE,
											FLAMER_CHOICE),
									0,
									1
									);
		
		// Assemble the Crux Terminatus option group
		CRUX_TERMINATUS_GROUP = OptionGroup.get(
									CRUX_NAME,
									List.of(CRUX_CHOICE),
									0,
									1);
		
		// Assign the Special Weapons Group and the
		// Crux Terminatus group to the list of Option Groups
		OPTION_GROUPS = List.of(SPECIAL_WEAPONS_GROUP, 
								CRUX_TERMINATUS_GROUP);
	}
	
	// MAIN
	public static void main(String[] args) {
		UnitExampleTest test = new UnitExampleTest();
		
		UnitDescription tacticalSquadDesc = test.assembleTacticalSquadDescription();
		UnitInstance    tacSquad = UnitFactory.getInstance(tacticalSquadDesc);
		// Display the Tactical Squad's information
		test.printUnitInfo(tacSquad);
		test.printModelInfo(tacSquad);
		test.printUnitOptionGroups(tacSquad);
		// Select and option - Plasma Gun
		test.selectPlasmaGun(tacSquad);
		test.printModelInfo(tacSquad);
		ModelInstance sgt = test.getSergeant(tacSquad);
		test.selectCrux(sgt);
		test.printModelInfo(sgt);
		test.removeCrux(sgt);
		test.printModelInfo(sgt);
		
	}
	
	// Construct ModelDescriptions as prototypes
	
	public ModelDescription createSpaceMarine() {
		return ModelFactory.getDescription(
					MARINE_NAME, 
					MARINE_POINTS, 
					MARINE_STATS, 
					TACMARINE_TYPES, 
					TACMARINE_GEAR);
	}
	
	public ModelDescription createSpaceMarineSrg() {
		return ModelFactory.getDescription(
					MARINE_SGT_NAME,
					MARINE_POINTS,
					MARINE_SGT_STATS,
					SERGEANT_TYPES,
					TACMARINE_GEAR);
	}
	
	// Construct the minimum Tactical Squad from prototypes
	public UnitDescription assembleTacticalSquadDescription() {
		
		// Create Model Descriptions
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
				System.out.println("- " + gear.getName());
			}
			System.out.println("");
		}
	}
	
	public void printModelInfo(ModelInstance model) {
		System.out.println(model.getName());
		System.out.println(model.getStats().statsToString());
		// Print out wargear for each marine to really show everything is in there
		System.out.println("Equipped with:");	
		for (WargearInstance gear : model.getGear()) {
			System.out.println("- " + gear.getName());
		}
		System.out.println("");
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
	
	public void selectPlasmaGun(UnitInstance unit) {
		ModelInstance model = unit.getModels().get(0);
		RequirementResult result = model.addSelection(PLASMA_CHOICE);
		if (result.isValid()) {
			System.out.println("\n--- Plasma Gun selected ---\n");
		} else {
			System.out.println("\n" + result.getMessage() + "\n");
		}
	}
	
	public void selectCrux(ModelInstance model) {
		RequirementResult result = model.addSelection(CRUX_CHOICE);
		if (result.isValid()) {
			System.out.println("\n--- Crux Terminatus selected ---\n");
		} else {
			System.out.println("\n" + result.getMessage() + "\n");
		}
	}
	
	public void removeCrux(ModelInstance model) {
		model.removeSelection(CRUX_CHOICE);
		System.out.println("\n--- Crux Terminatus removed ---\n");
//		if (result.isValid()) {
//			System.out.println("\n--- Crux Terminatus selected ---\n");
//		} else {
//			System.out.println("\n" + result.getMessage() + "\n");
//		}
	}
	
	public ModelInstance getSergeant(UnitInstance unit) {
		for (ModelInstance model : unit.getModels()) {
			if (MARINE_SGT_NAME.equalsIgnoreCase(model.getName())) {
				return model;
			}
		}
		return null;
	}

}
