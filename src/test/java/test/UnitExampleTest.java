package test;

import java.util.ArrayList;
import java.util.HashSet;
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
import units.options.effects.ReplaceWargearEffect;
import units.options.requirements.MustHaveGearReq;
import units.options.requirements.Requirement;

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
	private static final UnitRole TROOPS = UnitRole.TROOPS;
	
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
	
	private final StatLine MARINE_STATS = StatLineFactory.get(MARINE_NAME,4, 4, 4,4, 1, 4, 1, 8, 3);
	private final StatLine MARINE_SRG_STATS = StatLineFactory.get(MARINE_SRG_NAME,4, 4, 4,4, 1, 4, 1, 8, 3);
	private final Set<UnitType> TACMARINE_TYPES = Set.of(INFANTRY);
	private final Set<UnitType> SERGEANT_TYPES = Set.of(INFANTRY,CHARACTER);
	
	private final WargearDescription BOLTER = WargearDescription.get(BOLTER_NAME,BOLTER_TYPE);
	private final WargearDescription PLASMA_GUN = WargearDescription.get(PLASMA_GUN_NAME,PLASMA_GUN_TYPE, PLASMA_GUN_POINTS);
	private final WargearDescription FLAMER = WargearDescription.get(FLAMER_NAME,FLAMER_TYPE, FLAMER_POINTS);
	private final WargearDescription MELTA_GUN = WargearDescription.get(MELTA_NAME, MELTA_TYPE, MELTA_POINTS);
	private final WargearDescription POWER_ARMOR = WargearDescription.get(ARMOR_NAME, ARMOR_TYPE);
	private final Set<WargearDescription> TACMARINE_GEAR = Set.of(BOLTER,POWER_ARMOR);
	
	private final List<OptionGroup> OPTION_GROUPS = new ArrayList<>();
	
	// Constructor
	public UnitExampleTest() {}
	
	// MAIN
	public static void main(String[] args) {
		UnitExampleTest test = new UnitExampleTest();
		
		UnitDescription tacticalSquadDesc = test.assembleTacticalSquad();
		UnitInstance tacSquad = new UnitInstance(tacticalSquadDesc);

		test.printUnitInfo(tacSquad);
		test.printModelInfo(tacSquad);
		test.printUnitOptionGroups(tacSquad);
	}
	
	// Methods
	
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
	
	// Construct the minimum Tactical Squad
	public UnitDescription assembleTacticalSquad() {
		
		List<ModelDescription> models = new ArrayList<>();
		ModelDescription marine = createSpaceMarine();
		ModelDescription sergeant = createSpaceMarineSrg();
		
		// Add Marines
		for (int i = 0 ; i < COUNT_MARINES ; i++) {
			models.add(marine);
		}
		// Add Sergeant
		for (int i = 0 ; i < COUNT_SRG ; i++) {
			models.add(sergeant);
		}
		
		OPTION_GROUPS.add(createSpecialWeaponGroup());
		
		return new UnitDescription(
					SQUAD_NAME,
					TAC_SQUAD_MIN,
					TAC_SQUAD_MAX,
					TROOPS,
					OPTION_GROUPS,
					models
					);
	}
	
	public Effect replaceBolterWithPlasmaEffect() {
		return new ReplaceWargearEffect(BOLTER,PLASMA_GUN);
	}
	
	public Requirement replaceBolterWithPlasmaReq() {
		return new MustHaveGearReq(BOLTER);
	}
	
	public OptionChoice createPlasmaOptionChoice() {
		return new OptionChoice(
					PLASMA_GUN_NAME,
					PLASMA_GUN_POINTS,
					List.of(replaceBolterWithPlasmaReq()),
					List.of(replaceBolterWithPlasmaEffect())
					);
	}
	
	public OptionGroup createSpecialWeaponGroup() {
		return new OptionGroup(
					"Special weapons",
					List.of(createPlasmaOptionChoice()),
					null,
					0,
					1
					);
	}
	
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
			System.out.println(og.getName());
			for (OptionChoice c : og.getChoices()) {
				System.out.println("Choice:" + c.getName());
			}
		}
	}

}
