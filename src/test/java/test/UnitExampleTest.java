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
import units.options.OptionGroup;

public class UnitExampleTest {

	// Test container class for unit loading
	// Not actual unit tests
	
	private static final String SQUAD_NAME = "Tactical Squad";
	private static final String MARINE_NAME = "Tactical Marine";
	private static final String MARINE_SRG_NAME = "Marine Sergeant";
	private static final String BOLTER_NAME = "Bolter";
	private static final String ARMOR_NAME = "Power Armor";
	private static final String PLASMA_GUN_NAME = "Plasma gun";
	
	private static final WargearType BOLTER_TYPE = WargearType.TWO_HANDED;
	private static final WargearType PLASMA_GUN_TYPE = WargearType.TWO_HANDED;
	private static final WargearType ARMOR_TYPE = WargearType.GEAR;
	private static final UnitRole TROOPS = UnitRole.TROOPS;
	
	private static final int MARINE_POINTS = 15;
	private static final int PLASMA_GUN_POINTS = 10;
	private static final int TAC_SQUAD_MIN = 5;
	private static final int TAC_SQUAD_MAX = 10;
	private static final int MIN_MARINES = 4;
	private static final int MAX_MARINES = 9;
	private static final int MIN_SRG = 1;
	private static final int MAX_SRG = 1;
	private static final UnitType INFANTRY = UnitType.INFANTRY;
	private static final UnitType CHARACTER = UnitType.CHARACTER;
	
	private final StatLine MARINE_STATS = StatLineFactory.get(MARINE_NAME,4, 4, 4,4, 1, 4, 1, 8, 3);
	private final Set<UnitType> TACMARINE_TYPES = new HashSet<>(Set.of(INFANTRY));
	private final Set<UnitType> SERGEANT_TYPES = new HashSet<>(Set.of(INFANTRY,CHARACTER));
	private final Set<UnitType> TAC_SQUAD_TYPES = new HashSet<>();
	
	private final WargearDescription BOLTER = new WargearDescription(BOLTER_NAME,BOLTER_TYPE);
	private final WargearDescription PLASMA_GUN = new WargearDescription(PLASMA_GUN_NAME,PLASMA_GUN_TYPE);
	private final WargearDescription POWER_ARMOR = new WargearDescription(ARMOR_NAME, ARMOR_TYPE);
	private final Set<WargearDescription> TACMARINE_GEAR = new HashSet<>(Set.of(BOLTER,POWER_ARMOR));
	
	private final List<OptionGroup> OPTION_GROUP = new ArrayList<>();
	
	// Constructor
	public UnitExampleTest() {}
	
	// MAIN
	public static void main(String[] args) {
		UnitExampleTest test = new UnitExampleTest();
		UnitDescription tacticalSquadDesc = test.assembleTacticalSquad();
		UnitInstance tacSquad = new UnitInstance(tacticalSquadDesc);

		System.out.println(tacSquad.getName());
		System.out.printf("Unit contains %d %ss\n",tacSquad.getCurrentSize(), MARINE_NAME);
		for (ModelInstance model : tacSquad.getModels()) {
			System.out.println(model.getName());
			System.out.println(model.getStats().statsToString());
			// Print out wargear for each marine to really show everything is in there
		}
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
					MARINE_STATS,
					SERGEANT_TYPES,
					TACMARINE_GEAR
					);
	}
	
	public UnitDescription assembleTacticalSquad() {
		
		List<ModelDescription> models = new ArrayList<>();
		
		// Add 4 Marines
		for (int i = 0 ; i < MIN_MARINES ; i++) {
			models.add(createSpaceMarine());
		}
		// Add 1 Sergeant
		models.add(createSpaceMarineSrg());
		
		return new UnitDescription(
					SQUAD_NAME,
					TAC_SQUAD_MIN,
					TAC_SQUAD_MAX,
					TROOPS,
					TAC_SQUAD_TYPES,
					OPTION_GROUP,
					models
					);
	}

}
